import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    void save(Resume r) {
        if (isResumeExist(r.uuid)) {
            System.out.println("The resume is already exist in the ArrayStorage");
        } else if (size < storage.length) {
            storage[size++] = r;
        } else {
            System.out.println("ArrayStorage overflow");
        }
    }

    void update(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(r.uuid)) {
                storage[i] = r;
                return;
            }
        }
        noResumeMessage();
    }

    Resume get(String uuid) {
        for (Resume resume : getAll()) {
            if (resume.uuid.equals(uuid)) {
                return resume;
            }
        }
        noResumeMessage();
        return null;
    }

    void delete(String uuid) {
        if (!isResumeExist(uuid)) {
            noResumeMessage();
        } else if (size == 1) {
            storage[--size] = null;
        } else {
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    storage[i] = storage[--size];
                    storage[size] = null;
                }
            }
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }

    private boolean isResumeExist(String uuid) {
        for (Resume resume : getAll()) {
            if (resume.uuid.equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    private void noResumeMessage() {
        System.out.println("There is no such resume in the ArrayStorage");
    }
}
