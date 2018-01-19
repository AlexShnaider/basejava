package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.Serializer.DataStreamStrategy;

public class FileStorageDataStreamSerializationTest extends AbstractStorageTest {
    public FileStorageDataStreamSerializationTest() {
        super(new FileStorage(STORAGE_DIR, new DataStreamStrategy()));
    }
}
