package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class MapStorageTest extends AbstractArrayStorageTest {

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Test
    public void getAll() throws Exception {
        Resume[] answer = storage.getAll();
        Arrays.sort(answer);
        Assert.assertTrue(Arrays.binarySearch(answer, RESUME1) >= 0);
        Assert.assertTrue(Arrays.binarySearch(answer, RESUME2) >= 0);
        Assert.assertTrue(Arrays.binarySearch(answer, RESUME3) >= 0);
    }
}