package ru.javawebinar.basejava.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlPreparedStatementProcessor<T> {
    T process(PreparedStatement preparedStatement) throws SQLException;
}
