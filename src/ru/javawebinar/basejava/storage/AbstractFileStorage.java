package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Exceptions.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    private File directory;

    protected AbstractFileStorage(File directory) {
        checkDirectory(directory);
        this.directory = directory;
    }

    @Override
    protected List<Resume> getAllAsList() {
        checkDirectory(directory);
        List<Resume> answer = new ArrayList<>();
        for (File file : directory.listFiles()) {
            try {
                answer.add(doRead(file));
            } catch (IOException e) {
                throw new StorageException("IO Exception", file.getName(), e);
            }
        }
        return answer;
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO Exception", file.getName(), e);
        }
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO Exception", file.getName(), e);
        }
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("IO Exception", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("file" + file.getName() + "couldn't be deleted", file.getName());
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    public void clear() {
        checkDirectory(directory);
        for (File file : directory.listFiles()) {
            doDelete(file);
        }
    }

    @Override
    public int size() {
        checkDirectory(directory);
        return directory.listFiles().length;
    }

    private void checkDirectory(File directory) {
        Objects.requireNonNull(directory, "directory mustn't be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not readable/writable");
        }
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;
}
