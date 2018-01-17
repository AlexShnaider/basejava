package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.Serializer.XmlStreamStrategy;

public class PathStorageXmlStreamSerializationTest extends AbstractStorageTest {
    public PathStorageXmlStreamSerializationTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(),new XmlStreamStrategy()));
    }
}
