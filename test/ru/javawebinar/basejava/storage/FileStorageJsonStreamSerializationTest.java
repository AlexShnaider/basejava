package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.Serializer.JsonStreamStrategy;

public class FileStorageJsonStreamSerializationTest extends AbstractStorageTest {
    public FileStorageJsonStreamSerializationTest() {
        super(new FileStorage(STORAGE_DIR, new JsonStreamStrategy()));
    }
}
