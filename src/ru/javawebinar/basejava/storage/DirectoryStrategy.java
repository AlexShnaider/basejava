package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.List;

public interface DirectoryStrategy<T> {

    List<Resume> getAllAsList();

    void doSave(Resume r, T dir);

    void doUpdate(Resume r, T dir);

    Resume doGet(T dir);

    void doDelete(T dir);

    boolean isExist(T dir);

    T getSearchKey(String uuid);

    void clear();

    int size();
}
