package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Strategy.ObjectStreamStrategy;

import java.nio.file.Path;

public class PathStorageObjectStreamSerializationStrategyTest extends AbstractStorageTest {
    public PathStorageObjectStreamSerializationStrategyTest() {
        super(new PathStorage(STORAGE_DIR.toString(),new ObjectStreamStrategy()));
    }
}
