package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Exceptions.ExistStorageException;
import ru.javawebinar.basejava.Exceptions.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.basejava.util.SqlHelper.sqlExecute;

public class SqlStorage implements Storage {

    @Override
    public void clear() {
        sqlExecute("DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public void save(Resume r) {
        sqlExecute("INSERT INTO resume (uuid, full_name) VALUES (?,?)", (ps) -> {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            try {
                ps.execute();
            } catch (SQLException e) {
                throw new ExistStorageException(r.getUuid());
            }
            return null;
        });
    }

    @Override
    public void update(Resume r) {
        sqlExecute("UPDATE resume SET full_name = ? WHERE uuid = ?", (ps) -> {
            ps.setString(1, r.getFullName());
            ps.setString(2, r.getUuid());
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(r.getUuid());
            }
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlExecute("SELECT * FROM resume r WHERE r.uuid=?", (ps) -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });
    }

    @Override
    public void delete(String uuid) {
        sqlExecute("DELETE FROM resume WHERE uuid = ?", (ps) -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlExecute("SELECT * FROM resume ORDER BY uuid, full_name", (ps) -> {
            ResultSet rs = ps.executeQuery();
            List<Resume> answer = new ArrayList<>();
            while (rs.next()) {
                answer.add(new Resume(rs.getString(1).trim(), rs.getString(2)));
            }
            return answer;
        });
    }

    @Override
    public int size() {
        return sqlExecute("SELECT COUNT(*) size FROM resume", (ps) -> {
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("size");
        });
    }
}
