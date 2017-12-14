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
        for (int i = 0; i < size; i++) {
            if (storage[i].compareTo(r) > 0) {
                System.arraycopy(storage, i, storage, i + 1, size - i);
                storage[i] = r;
                size++;
                return;
            }
        }
        storage[size++] = r;
    }

    @Override
    protected void remove(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index);
        size--;
    }
}
