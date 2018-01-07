package ru.javawebinar.basejava.storage;

import java.nio.file.Path;

public class PathStrategyObjectStreamSerializationTest extends AbstractStorageTest {
    public PathStrategyObjectStreamSerializationTest() {
        super(new DirectoryStorage<Path>(new PathStrategy(STORAGE_DIR.toString(), new ObjectStreamStrategy())));
    }
}
