package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.Serializer.ObjectStreamStrategy;

public class FileStorageObjectStreamSerializationTest extends AbstractStorageTest {
    public FileStorageObjectStreamSerializationTest() {
        super(new FileStorage(STORAGE_DIR,new ObjectStreamStrategy()));
    }
}
