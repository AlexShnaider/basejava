package ru.javawebinar.basejava.model;

import java.util.*;

/**
 * com.urise.webapp.model.Resume class
 */
public class Resume implements Comparable<Resume> {
    private final String uuid;
    private final String fullName;
    private final Map<String,String> contacts = new HashMap<>();
    private final Map<SectionTitle,Section> sections = new EnumMap<>(SectionTitle.class);

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
        StringBuilder answer = new StringBuilder();
        answer.append(fullName).append(System.lineSeparator());

        if (!contacts.isEmpty()) {
            answer.append("Contacts :").append(System.lineSeparator());
            for (Map.Entry entry : contacts.entrySet()) {
                answer.append(entry.getKey()).append(" : ").append(entry.getValue()).append(System.lineSeparator());
            }
        }

        Section<TextSection> position = sections.get(SectionTitle.OBJECTIVE);
        if (position != null) {
            answer.append(SectionTitle.OBJECTIVE.getTitle()).append(":").append(System.lineSeparator())
                    .append(position.getContent()).append(System.lineSeparator());
        }

        Section<TextSection> qualities = sections.get(SectionTitle.PERSONAL);
        if (qualities != null) {
            answer.append(SectionTitle.PERSONAL.getTitle()).append(":").append(System.lineSeparator())
                    .append(qualities.getContent()).append(System.lineSeparator());
        }

        Section<ListSection> achievements = sections.get(SectionTitle.ACHIEVEMENT);
        if (achievements != null) {
            answer.append(SectionTitle.ACHIEVEMENT.getTitle()).append(":").append(System.lineSeparator());
            for (String achievement : (List<String>) achievements.getContent()) {
                answer.append(achievement).append(System.lineSeparator());
            }
        }

        Section<ListSection> qualifications = sections.get(SectionTitle.QUALIFICATIONS);
        if (qualifications != null) {
            answer.append(SectionTitle.QUALIFICATIONS.getTitle()).append(":").append(System.lineSeparator());
            for (String qualification : (List<String>) qualifications.getContent()) {
                answer.append(qualification).append(System.lineSeparator());
            }
        }

        return answer.toString();
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
