package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Strategy.ObjectStreamStrategy;

import java.io.File;

public class FileStorageObjectStreamSerializationStrategyTest extends AbstractStorageTest {
    public FileStorageObjectStreamSerializationStrategyTest() {
        super(new FileStorage(STORAGE_DIR,new ObjectStreamStrategy()));
    }
}
