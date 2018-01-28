package ru.javawebinar.basejava.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapUuidStorageTest.class,
        MapResumeStorageTest.class,
        FileStorageObjectStreamSerializationTest.class,
        PathStorageObjectStreamSerializationTest.class,
        FileStorageXmlStreamSerializationTest.class,
        PathStorageXmlStreamSerializationTest.class,
        FileStorageJsonStreamSerializationTest.class,
        PathStorageJsonStreamSerializationTest.class,
        FileStorageDataStreamSerializationTest.class,
        PathStorageDataStreamSerializationTest.class,
        SqlStorageTest.class
})
public class AllTests {
}
