package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<Resume, String> storage = new HashMap<>();

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.put(r, r.getUuid());
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.replace(r, r.getUuid());
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        for (Resume resume : storage.keySet()) {
            if (resume.getUuid().equals(uuid)) {
                return resume;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllAsList() {
        return new ArrayList<>(storage.keySet());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
