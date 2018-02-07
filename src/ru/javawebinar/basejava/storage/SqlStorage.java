package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Exceptions.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {

    private final SqlHelper sqlHelper;

    public SqlStorage(String sqlUrl, String sqlUser, String sqlPassword) {
        this.sqlHelper = new SqlHelper(() -> DriverManager.getConnection(sqlUrl, sqlUser, sqlPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getFullName());
                ps.execute();
            }
            try (PreparedStatement ps = conn.prepareStatement("INSERT  INTO contact (type, value, resume_uuid) VALUES (?,?,?)")) {
                for (Map.Entry<ContactType, String> entry : r.getContacts().entrySet()) {
                    ps.setString(1, entry.getKey().name());
                    ps.setString(2, entry.getValue());
                    ps.setString(3, r.getUuid());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            return null;
        });
    }

    @Override
    public void update(Resume r) {
        delete(r.getUuid());
        save(r);
    }

/*    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("SELECT * FROM resume r LEFT JOIN contact c "
                + "ON r.uuid = c.resume_uuid WHERE r.uuid=?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            Resume answer = new Resume(uuid, rs.getString("full_name"));
            do {
                String type = rs.getString("type");
                String value = rs.getString("value");
                if (!(type == null || value == null)) {
                    answer.addContact(ContactType.valueOf(type), value);
                }
            } while (rs.next());
            return answer;
        });
    }*/

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute((Connection conn) -> getResume(conn, uuid));
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    /*@Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("SELECT * FROM resume r LEFT JOIN contact c "
                + "ON r.uuid = c.resume_uuid ORDER BY uuid, full_name", ps -> {
            ResultSet rs = ps.executeQuery();
            List<Resume> answer = new ArrayList<>();
            String currentUuid = null;
            Resume currentResume = null;
            boolean firstResume = true;
            while (rs.next()) {
                String uuid = rs.getString("uuid");
                if (uuid.equals(currentUuid)) {
                    String type = rs.getString("type");
                    String value = rs.getString("value");
                    if (!(type == null || value == null)) {
                        currentResume.addContact(ContactType.valueOf(type), value);
                    }
                } else {
                    if (!firstResume) {
                        answer.add(currentResume);
                    }
                    currentResume = new Resume(uuid.trim(), rs.getString("full_name"));
                    currentUuid = uuid;
                    firstResume = false;
                    String type = rs.getString("type");
                    String value = rs.getString("value");
                    if (!(type == null || value == null)) {
                        currentResume.addContact(ContactType.valueOf(type), value);
                    }
                }
            }
            answer.add(currentResume);
            return answer;
        });
    }*/

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(conn -> {
            List<String> uuids = new ArrayList<>();
            List<Resume> resumes = new ArrayList<>();
            try(PreparedStatement ps = conn.prepareStatement("SELECT uuid FROM resume")){
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    uuids.add(rs.getString("uuid"));
                }
            }
            for (String uuid : uuids) {
                resumes.add(getResume(conn, uuid));
            }
            Collections.sort(resumes);
            return resumes;
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(*) size FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("size");
        });
    }

    private Resume getResume(Connection conn, String uuid) throws SQLException{
        try(PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM resume r LEFT JOIN contact c ON r.uuid = c.resume_uuid WHERE r.uuid=?")) {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            Resume resume = new Resume(uuid.trim(), rs.getString("full_name"));
            do {
                String type = rs.getString("type");
                String value = rs.getString("value");
                if (!(type == null || value == null)) {
                    resume.addContact(ContactType.valueOf(type), value);
                }
            } while (rs.next());
            return resume;
        }
    }
}
