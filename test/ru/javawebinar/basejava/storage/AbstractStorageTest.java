package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Exceptions.ExistStorageException;
import ru.javawebinar.basejava.Exceptions.NotExistStorageException;
import ru.javawebinar.basejava.Exceptions.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final Resume resume1 = new Resume(UUID_1);
    private static final Resume resume2 = new Resume(UUID_2);
    private static final Resume resume3 = new Resume(UUID_3);
    private static final Resume resume4 = new Resume(UUID_4);

    private final Storage storage;

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void clear() throws Exception {
        Assert.assertEquals(3, storage.size());
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() throws Exception {
        Assert.assertEquals(3, storage.size());
        storage.save(resume4);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(resume4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExistedUuid() throws Exception {
        Assert.assertEquals(3, storage.size());
        storage.save(resume1);
    }

    @Test
    public void saveStorageOverflow() throws Exception {
        try {
            for (int i = 4; i <= 10000; i++) {
                storage.save(new Resume("uuid" + i));
                Assert.assertEquals(i, storage.size());
            }
        } catch (StorageException e) {
            Assert.fail("StorageException was thrown too early");
        }
        try {
            storage.save(new Resume("new_uuid"));
            Assert.fail("StorageException wasn't thrown");
        } catch (StorageException e) {
            Assert.assertEquals("Storage overflow", e.getMessage());
        }
    }

    @Test
    public void update() throws Exception {
        Assert.assertEquals(3, storage.size());
        storage.update(resume1);
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistedUuid() throws Exception {
        Assert.assertEquals(3, storage.size());
        storage.update(resume4);
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(3, storage.size());
        Assert.assertEquals(resume1, storage.get(UUID_1));
        Assert.assertEquals(resume2, storage.get(UUID_2));
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistedUuid() throws Exception {
        storage.get(UUID_4);
    }

    @Test
    public void delete() throws Exception {
        Assert.assertEquals(3, storage.size());
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        storage.delete(UUID_2);
        Assert.assertEquals(1, storage.size());
        try {
            storage.get(UUID_2);
        } catch (NotExistStorageException e) {
        }
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistedUuid() throws Exception {
        storage.delete(UUID_4);
    }

    @Test
    public void getAll() throws Exception {
        Resume[] resumes = {resume1, resume2, resume3};
        Assert.assertArrayEquals(resumes, storage.getAll());
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
        storage.save(resume4);
        Assert.assertEquals(4, storage.size());
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }
}