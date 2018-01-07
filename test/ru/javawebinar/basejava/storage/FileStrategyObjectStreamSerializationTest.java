package ru.javawebinar.basejava.storage;

import java.io.File;

public class FileStrategyObjectStreamSerializationTest extends AbstractStorageTest {
    public FileStrategyObjectStreamSerializationTest() {
        super(new DirectoryStorage<File>(new FileStrategy(STORAGE_DIR, new ObjectStreamStrategy())));
    }
}
