package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Exceptions.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStrategy implements DirectoryStrategy<File> {

    private File directory;
    private final SerializationStrategy strategy;

    protected FileStrategy(File directory, SerializationStrategy strategy) {
        checkDirectory(directory);
        this.directory = directory;
        this.strategy = strategy;
    }

    @Override
    public List<Resume> getAllAsList() {
        checkDirectory(directory);
        List<Resume> answer = new ArrayList<>();
        for (File file : directory.listFiles()) {
            try {
                answer.add(strategy.doRead(new BufferedInputStream(new FileInputStream(file))));
            } catch (IOException e) {
                throw new StorageException("IO Exception", file.getName(), e);
            }
        }
        return answer;
    }

    @Override
    public void doSave(Resume r, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("IO Exception", file.getName(), e);
        }
        doUpdate(r, file);
    }

    @Override
    public void doUpdate(Resume r, File file) {
        try {
            strategy.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO Exception", file.getName(), e);
        }
    }

    @Override
    public Resume doGet(File file) {
        try {
            return strategy.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO Exception", file.getName(), e);
        }
    }

    @Override
    public void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("file" + file.getName() + "couldn't be deleted", file.getName());
        }
    }

    @Override
    public boolean isExist(File file) {
        return file.exists();
    }

    @Override
    public File getSearchKey(String uuid) {
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
}
