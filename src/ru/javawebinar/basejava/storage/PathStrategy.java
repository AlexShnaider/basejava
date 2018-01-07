package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Exceptions.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStrategy implements DirectoryStrategy<Path> {

    private Path directory;
    private final SerializationStrategy strategy;

    protected PathStrategy(String directory, SerializationStrategy strategy) {
        this.directory = Paths.get(directory);
        this.strategy = strategy;
        checkDirectory(this.directory);
    }

    private void checkDirectory(Path directory) {
        Objects.requireNonNull(directory, "directory mustn't be null");
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(directory + "is not directory");
        }
        if (!Files.isWritable(directory) || !Files.isReadable(directory)) {
            throw new IllegalArgumentException(directory + "is not readable/writable");
        }
    }

    @Override
    public List<Resume> getAllAsList() {
        try {
            Stream<Path> paths = Files.list(directory);
            List<Resume> answer = new ArrayList<>();
            for (Path path : paths.collect(Collectors.toList())) {
                answer.add(doGet(path));
            }
            return answer;
        } catch (IOException e) {
            throw new StorageException("IO Exception", null, e);
        }
    }

    @Override
    public void doSave(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Path creation error", path.getFileName().toString(), e);
        }
        doUpdate(r, path);
    }

    @Override
    public void doUpdate(Resume r, Path path) {
        try {
            strategy.doWrite(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("IO Exception", path.getFileName().toString(), e);
        }
    }

    @Override
    public Resume doGet(Path path) {
        try {
            return strategy.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("IO Exception", path.getFileName().toString(), e);
        }
    }

    @Override
    public void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error", path.getFileName().toString(), e);
        }
    }

    @Override
    public boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    public Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Clear error", null, e);
        }

    }

    @Override
    public int size() {
        try {
            return (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("Size error", null, e);
        }
    }
}
