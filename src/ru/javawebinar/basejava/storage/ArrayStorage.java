package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private static final int MAX_VALUE = 10000;

    private final Resume[] storage = new Resume[MAX_VALUE];
    private int size;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void save(Resume r) {
        if (getIndexOfResume(r.getUuid()) != -1) {
            System.out.println("The resume is already exist in the ArrayStorage");
        } else if (size == MAX_VALUE) {
            System.out.println("ArrayStorage overflow");
        } else {
            storage[size++] = r;
        }
    }

    public void update(Resume r) {
        int index = getIndexOfResume(r.getUuid());
        if (index == -1) {
            noResumeMessage();
        } else {
            storage[index] = r;
        }
    }

    public Resume get(String uuid) {
        int index = getIndexOfResume(uuid);
        if (index == -1) {
            noResumeMessage();
            return null;
        } else {
            return storage[index];
        }
    }

    public void delete(String uuid) {
        int index = getIndexOfResume(uuid);
        if (index == -1) {
            noResumeMessage();
        } else {
            storage[index] = storage[--size];
            storage[size] = null;
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndexOfResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    private void noResumeMessage() {
        System.out.println("There is no such resume in the ArrayStorage");
    }
}
