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
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid,full_name) VALUES (?, ?)")) {
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getFullName());
                ps.execute();
            }
            insertContacts(conn, r);
            return null;
        });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getUuid());
            }
            deleteContacts(conn, r);
            insertContacts(conn, r);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("SELECT * FROM resume r LEFT JOIN contact c ON r.uuid = c.resume_uuid WHERE r.uuid=?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            Resume resume = new Resume(uuid.trim(), rs.getString("full_name"));
            do {
                addContact(rs, resume);
            } while (rs.next());
            return resume;
        });
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

    @Override
    public List<Resume> getAllSorted() {
        Map<String, Resume> resumes = new LinkedHashMap<>();
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume ORDER BY full_name, uuid")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid").trim();
                    resumes.put(uuid, new Resume(uuid, rs.getString("full_name")));
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact ORDER BY resume_uuid")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String contactUuid = rs.getString("resume_uuid").trim();
                    Resume resume = resumes.get(contactUuid);
                    addContact(rs, resume);
                }
            }
            return null;
        });
        return new ArrayList<>(resumes.values());
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(*) size FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("size");
        });
    }

    private void insertContacts(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (type, value, resume_uuid) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> entry : r.getContacts().entrySet()) {
                ps.setString(1, entry.getKey().name());
                ps.setString(2, entry.getValue());
                ps.setString(3, r.getUuid());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteContacts(Connection conn, Resume resume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid = ?")) {
            ps.setString(1, resume.getUuid());
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(resume.getUuid());
            }
        }
    }

    private void addContact(ResultSet rs, Resume resume) throws SQLException {
        String type = rs.getString("type");
        if (type != null) {
            resume.addContact(ContactType.valueOf(type), rs.getString("value"));
        }
    }
}