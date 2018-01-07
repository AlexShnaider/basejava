package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.List;

public class DirectoryStorage<T> extends AbstractStorage<T> {

    private final DirectoryStrategy strategy;

    public DirectoryStorage(DirectoryStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    protected List<Resume> getAllAsList() {
        return strategy.getAllAsList();
    }

    @Override
    protected void doSave(Resume r, T searchKey) {
        strategy.doSave(r, searchKey);
    }

    @Override
    protected void doUpdate(Resume r, T searchKey) {
        strategy.doUpdate(r, searchKey);
    }

    @Override
    protected Resume doGet(T searchKey) {
        return strategy.doGet(searchKey);
    }

    @Override
    protected void doDelete(T searchKey) {
        strategy.doDelete(searchKey);
    }

    @Override
    protected boolean isExist(T searchKey) {
        return strategy.isExist(searchKey);
    }

    @Override
    protected T getSearchKey(String uuid) {
        return (T) strategy.getSearchKey(uuid);
    }

    @Override
    public void clear() {
        strategy.clear();
    }

    @Override
    public int size() {
        return strategy.size();
    }
}
