package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Exceptions.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int MAX_VALUE = 10000;

    protected final Resume[] storage = new Resume[MAX_VALUE];
    protected int size;

    @Override
    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    @Override
    protected void doSave(Resume r, Integer index) {
        if (size == MAX_VALUE) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            add(r, index);
            size++;
        }
    }

    @Override
    protected void doUpdate(Resume r, Integer index) {
        storage[index] = r;
    }

    @Override
    protected Resume doGet(Integer index) {
        return storage[index];
    }

    @Override
    protected void doDelete(Integer index) {
        remove(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    @Override
    protected List<Resume> getAllAsList() {
        Resume[] answer = Arrays.copyOf(storage, size);
        return Arrays.asList(answer);
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void add(Resume r, int index);

    protected abstract void remove(int index);
}
