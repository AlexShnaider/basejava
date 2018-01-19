package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.Serializer.JsonStreamStrategy;

public class PathStorageJsonStreamSerializationTest extends AbstractStorageTest {
    public PathStorageJsonStreamSerializationTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamStrategy()));
    }
}
