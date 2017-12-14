package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractStorage implements Storage {
    protected static final int MAX_VALUE = 10000;

    protected final Resume[] storage = new Resume[MAX_VALUE];
    protected int size;

    @Override
    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    @Override
    public void save(Resume r) {
        if (getIndex(r.getUuid()) > -1) {
            System.out.println("The resume is already exist in the ArrayStorage");
        } else if (size == MAX_VALUE) {
            System.out.println("ArrayStorage overflow");
        } else {
            add(r);
        }
    }

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index == -1) {
            noResumeMessage();
        } else {
            storage[index] = r;
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            noResumeMessage();
            return null;
        } else {
            return storage[index];
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            noResumeMessage();
        } else {
            remove(index);
        }

    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void add(Resume r);

    protected abstract void remove(int index);

    protected void noResumeMessage() {
        System.out.println("There is no such resume in the ArrayStorage");
    }
}
