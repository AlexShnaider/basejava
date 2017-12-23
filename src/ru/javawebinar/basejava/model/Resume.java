package ru.javawebinar.basejava.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * com.urise.webapp.model.Resume class
 */
public class Resume implements Comparable<Resume> {
    private final String uuid;
    private final String fullName;
    private final Map<String,String> contacts = new HashMap<>();
    private final Map<SectionTitle,Section> sections = new HashMap<>();

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid mustn't be null");
        Objects.requireNonNull(fullName, "fullName mustn't be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public void addContact(String name, String address) {
        contacts.put(name, address);
    }

    public void removeContact(String name) {
        contacts.remove(name);
    }

    public void updateContact(String name, String address) {
        contacts.replace(name, address);
    }

    public void getContact(String name) {
        contacts.get(name);
    }

    public void addSection(SectionTitle name, Section section) {
        sections.put(name, section);
    }

    public void removeSection(Section name) {
        sections.remove(name);
    }

    public void updateSection(SectionTitle name, Section section) {
        sections.replace(name, section);
    }

    public void getSection(Section name) {
        sections.get(name);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return uuid + '(' + fullName + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid) && fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    @Override
    public int compareTo(Resume o) {
        int compFullName = fullName.compareTo(o.getFullName());
        return compFullName != 0 ? compFullName : uuid.compareTo(o.getUuid());
    }
}
