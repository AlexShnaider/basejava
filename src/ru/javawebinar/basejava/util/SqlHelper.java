package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.Exceptions.ExistStorageException;
import ru.javawebinar.basejava.Exceptions.NotExistStorageException;
import ru.javawebinar.basejava.Exceptions.StorageException;
import ru.javawebinar.basejava.sql.ConnectionFactory;

import java.sql.*;

public class SqlHelper {
    public static final ConnectionFactory CONNECTION_FACTORY = () -> DriverManager.getConnection(
            Config.getInstance().getDbUrl()
            , Config.getInstance().getDbName()
            , Config.getInstance().getDbPassword());

    public static <T> T sqlExecute(String sqlRequest, SqlPreparedStatementProcessing<T> someCode) {
        try (Connection connection = CONNECTION_FACTORY.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlRequest)) {
            return someCode.process(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public static void throwExceptionIfNotExist(String uuid) {
        sqlExecute("SELECT * FROM resume WHERE uuid = ?", (ps) -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    public static ResultSet throwExceptionIfExist(String uuid) {
        return sqlExecute("SELECT * FROM resume WHERE uuid = ?", (ps) -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                throw new ExistStorageException(uuid);
            }
            return rs;
        });
    }
}
