import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume r) {
        storage[getAll().length] = r;
    }

    Resume get(String uuid) {
        for (Resume resume : getAll()) {
            if (resume.toString().equals(uuid)) {
                return resume;
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < getAll().length; i++) {
            if (storage[i].toString().equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, storage.length - i - 1);
                storage[storage.length - 1] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int amountOfResumes = 0;
        while (storage[amountOfResumes] != null) {
            amountOfResumes++;
        }
        Resume[] notNullResumes = new Resume[amountOfResumes];
        System.arraycopy(storage, 0, notNullResumes, 0, amountOfResumes);
        return notNullResumes;
    }

    int size() {
        return getAll().length;
    }
}
