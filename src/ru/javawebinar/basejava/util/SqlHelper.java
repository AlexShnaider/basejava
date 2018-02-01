package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.Config;
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
}
