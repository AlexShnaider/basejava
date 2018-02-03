package ru.javawebinar.basejava.sql;

import org.postgresql.util.PSQLException;
import ru.javawebinar.basejava.Exceptions.ExistStorageException;
import ru.javawebinar.basejava.Exceptions.StorageException;

import java.sql.SQLException;

public class ExceptionUtil {

    private ExceptionUtil() {
    }

    public static StorageException convertException(SQLException e) {
        if (e instanceof PSQLException && e.getSQLState().equals("23505")) {
            throw new ExistStorageException(null);
        }
        return new StorageException(e);
    }
}
