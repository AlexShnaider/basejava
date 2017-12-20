package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.Exceptions.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public class AbstractArrayStorageTest extends AbstractStorageTest {
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveStorageOverflow() throws Exception {
        try {
            for (int i = 4; i <= AbstractArrayStorage.MAX_VALUE; i++) {
                storage.save(new Resume("uuid" + i));
                Assert.assertEquals(i, storage.size());
            }
        } catch (StorageException e) {
            Assert.fail("StorageException was thrown too early");
        }
        storage.save(new Resume("new_uuid"));
    }

/*    @Test
    public void getAllSorted() throws Exception {
        List<Resume> resumes = Arrays.asList(RESUME1, RESUME2, RESUME3);
        Assert.assertArrayEquals(resumes, storage.getAllSorted());
    }*/
}