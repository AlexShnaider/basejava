package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.Serializer.ObjectStreamStrategy;

public class PathStorageObjectStreamSerializationTest extends AbstractStorageTest {
    public PathStorageObjectStreamSerializationTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new ObjectStreamStrategy()));
    }
}
