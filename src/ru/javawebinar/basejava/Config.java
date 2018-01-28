package ru.javawebinar.basejava;

import java.io.*;
import java.util.Properties;

public class Config {
    private static final File PROPERTIES = new File("config/resumes.properties");
    private static final Config INSTANCE = new Config();

    private Properties properties = new Properties();
    private File storageDirectory;
    private String dbUrl;
    private String dbName;
    private String dbPassword;

    public static Config getInstance() {
        return INSTANCE;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public File getStorageDirectory() {
        return storageDirectory;
    }

    private Config() {
        try (InputStream is = new FileInputStream(PROPERTIES)) {
            properties.load(is);
            storageDirectory = new File(properties.getProperty("storage.dir"));
            dbUrl = new String(properties.getProperty("db.url"));
            dbName = new String(properties.getProperty("db.name"));
            dbPassword = new String(properties.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file" + PROPERTIES.getAbsolutePath());
        }
    }
}
