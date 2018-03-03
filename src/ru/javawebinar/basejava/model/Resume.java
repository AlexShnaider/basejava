package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

/**
 * com.urise.webapp.model.Resume class
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {

    public static final Resume EMPTY = new Resume("");

    static {
        EMPTY.addSection(SectionType.OBJECTIVE, TextSection.EMPTY);
        EMPTY.addSection(SectionType.PERSONAL, TextSection.EMPTY);
        EMPTY.addSection(SectionType.ACHIEVEMENT, ListSection.EMPTY);
        EMPTY.addSection(SectionType.QUALIFICATIONS, ListSection.EMPTY);
        EMPTY.addSection(SectionType.EXPERIENCE, new OrganizationSection(Collections.singletonList(Organization.EMPTY)));
        EMPTY.addSection(SectionType.EDUCATION, new OrganizationSection(Collections.singletonList(Organization.EMPTY)));
    }

    private String uuid;
    private String fullName;
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public Resume() {
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid mustn't be null");
        Objects.requireNonNull(fullName, "fullName mustn't be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public void addContact(ContactType contact, String id) {
        contacts.put(contact, id);
    }

    public void addSection(SectionType name, Section section) {
        sections.put(name, section);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, Section> getSections() {
        return sections;
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public Section getSection(SectionType type) {
        return sections.get(type);
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
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName) &&
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, sections);
    }

    @Override
    public int compareTo(Resume o) {
        int compFullName = fullName.compareTo(o.getFullName());
        return compFullName != 0 ? compFullName : uuid.compareTo(o.getUuid());
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
