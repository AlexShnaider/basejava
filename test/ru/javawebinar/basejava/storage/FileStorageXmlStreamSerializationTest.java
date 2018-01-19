package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.Serializer.XmlStreamStrategy;

public class FileStorageXmlStreamSerializationTest extends AbstractStorageTest {
    public FileStorageXmlStreamSerializationTest() {
        super(new FileStorage(STORAGE_DIR, new XmlStreamStrategy()));
    }
}
