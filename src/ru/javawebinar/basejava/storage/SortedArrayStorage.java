package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractStorage {

    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }

    @Override
    protected void add(Resume r) {
        int left = 0;
        int right = size;
        int middle;
        while (left < right) {
            middle = (left + right) / 2;
            if (storage[middle].compareTo(r) > 0) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        System.arraycopy(storage, left, storage, left + 1, size - left);
        storage[left] = r;
        size++;
    }

    @Override
    protected void remove(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index);
        size--;
    }
}
