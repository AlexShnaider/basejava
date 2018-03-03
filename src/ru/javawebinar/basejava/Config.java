package ru.javawebinar.basejava;

import ru.javawebinar.basejava.storage.SqlStorage;
import ru.javawebinar.basejava.storage.Storage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    //private static final File PROPERTIES = new File(getHomeDir(), "config/resumes.properties");
    private static final String PROPERTIES = "/resumes.properties";
    private static final Config INSTANCE = new Config();

    private final File storageDirectory;
    private final Storage sqlStorage;

    private Config() {
        try (InputStream is = Config.class.getResourceAsStream(PROPERTIES)) {
            //try (InputStream is = new FileInputStream(PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(is);
            storageDirectory = new File(properties.getProperty("storage.dir"));
            sqlStorage = new SqlStorage(properties.getProperty("db.url")
                    , properties.getProperty("db.name")
                    , properties.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file" + PROPERTIES/*.getAbsolutePath()*/);
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

    private static File getHomeDir() {
        String prop = System.getProperty("homeDir");
        File homeDir = new File(prop == null ? "." : prop);
        if (!homeDir.isDirectory()) {
            throw new IllegalStateException(homeDir + " is not directory");
        }
        return homeDir;
    }
}
