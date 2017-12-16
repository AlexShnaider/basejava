package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Exceptions.ExistStorageException;
import ru.javawebinar.basejava.Exceptions.NotExistStorageException;
import ru.javawebinar.basejava.Exceptions.StorageException;
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
        int index = getIndex(r.getUuid());
        if (index > -1) {
            throw new ExistStorageException(r.getUuid());
        } else if (size == MAX_VALUE) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            add(r, index);
            size++;
        }
    }

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage[index] = r;
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return storage[index];
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            remove(index);
            storage[size - 1] = null;
            size--;
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

    protected abstract void add(Resume r, int index);

    protected abstract void remove(int index);
}
