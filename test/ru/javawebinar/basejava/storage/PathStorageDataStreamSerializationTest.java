package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.Serializer.DataStreamStrategy;

public class PathStorageDataStreamSerializationTest extends AbstractStorageTest {
    public PathStorageDataStreamSerializationTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamStrategy()));
    }
}
