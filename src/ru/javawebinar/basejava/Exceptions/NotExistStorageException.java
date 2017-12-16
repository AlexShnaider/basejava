package ru.javawebinar.basejava.Exceptions;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Resume \"" + uuid + "\" isn't exist in the Storage", uuid);
    }
}
