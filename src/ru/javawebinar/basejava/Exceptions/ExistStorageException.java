package ru.javawebinar.basejava.Exceptions;

public class ExistStorageException extends StorageException {

    public ExistStorageException(String uuid) {
        super("Resume \"" + uuid + "\" is already exist in the Storage", uuid);
    }
}
