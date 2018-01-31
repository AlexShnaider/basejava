package ru.javawebinar.basejava.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlPreparedStatementProcessing<T> {
    T process(PreparedStatement preparedStatement) throws SQLException;
}
