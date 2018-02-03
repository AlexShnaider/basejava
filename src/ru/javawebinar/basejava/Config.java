package ru.javawebinar.basejava;

import ru.javawebinar.basejava.storage.SqlStorage;
import ru.javawebinar.basejava.storage.Storage;

import java.io.*;
import java.util.Properties;

public class Config {
    private static final File PROPERTIES = new File("config/resumes.properties");
    private static final Config INSTANCE = new Config();

    private final File storageDirectory;
    private final Storage sqlStorage;

    private Config() {
        try (InputStream is = new FileInputStream(PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(is);
            storageDirectory = new File(properties.getProperty("storage.dir"));
            sqlStorage = new SqlStorage(properties.getProperty("db.url")
                    , properties.getProperty("db.name")
                    , properties.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file" + PROPERTIES.getAbsolutePath());
        }
    }

    public static Config getInstance() {
        return INSTANCE;
    }

    public File getStorageDirectory() {
        return storageDirectory;
    }

    public Storage getSqlStorage() {
        return sqlStorage;
    }
}
