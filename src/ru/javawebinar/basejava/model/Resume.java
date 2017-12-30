package ru.javawebinar.basejava.model;

import java.util.*;

/**
 * com.urise.webapp.model.Resume class
 */
public class Resume implements Comparable<Resume> {
    private final String uuid;
    private final String fullName;
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid mustn't be null");
        Objects.requireNonNull(fullName, "fullName mustn't be null");
        this.uuid = uuid;
        this.fullName = fullName;
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

        Section<TextSection> position = sections.get(SectionType.OBJECTIVE);
        if (position != null) {
            answer.append(SectionType.OBJECTIVE.getTitle()).append(":").append(System.lineSeparator())
                    .append(position.getContent()).append(System.lineSeparator());
        }

        Section<TextSection> qualities = sections.get(SectionType.PERSONAL);
        if (qualities != null) {
            answer.append(SectionType.PERSONAL.getTitle()).append(":").append(System.lineSeparator())
                    .append(qualities.getContent()).append(System.lineSeparator());
        }

        Section<ListSection> achievements = sections.get(SectionType.ACHIEVEMENT);
        if (achievements != null) {
            answer.append(SectionType.ACHIEVEMENT.getTitle()).append(":").append(System.lineSeparator());
            for (String achievement : (List<String>) achievements.getContent()) {
                answer.append(achievement).append(System.lineSeparator());
            }
        }

        Section<ListSection> qualifications = sections.get(SectionType.QUALIFICATIONS);
        if (qualifications != null) {
            answer.append(SectionType.QUALIFICATIONS.getTitle()).append(":").append(System.lineSeparator());
            for (String qualification : (List<String>) qualifications.getContent()) {
                answer.append(qualification).append(System.lineSeparator());
            }
        }


        Section<OrganizationSection> experiences = sections.get(SectionType.EXPERIENCE);
        if (experiences != null) {
            answer.append(SectionType.EXPERIENCE.getTitle()).append(":").append(System.lineSeparator());
            for (Organization experience : (List<Organization>) experiences.getContent()) {
                answer.append(experience.getOrganization().getName()).append(System.lineSeparator())
                        .append(experience.getStartDate().toString()).append(" - ")
                        .append(experience.getFinishDate().toString()).append("   ")
                        .append(experience.getTextTitle()).append(System.lineSeparator())
                        .append(experience.getText()).append(System.lineSeparator());
            }
        }

        Section<OrganizationSection> education = sections.get(SectionType.EDUCATION);
        if (education != null) {
            answer.append(SectionType.EDUCATION.getTitle()).append(":").append(System.lineSeparator());
            for (Organization educ : (List<Organization>) education.getContent()) {
                answer.append(educ.getOrganization().getName()).append(System.lineSeparator())
                        .append(educ.getStartDate()).append(" - ")
                        .append(educ.getFinishDate()).append("   ")
                        .append(educ.getTextTitle()).append(System.lineSeparator())
                        .append(educ.getText()).append(System.lineSeparator());
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
