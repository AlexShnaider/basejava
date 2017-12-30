package ru.javawebinar.basejava.model;

public enum SectionType {
    PERSONAL("Personal qualities"),
    OBJECTIVE("Position"),
    ACHIEVEMENT("Achievements"),
    QUALIFICATIONS("Qualifications"),
    EXPERIENCE("Work experience"),
    EDUCATION("Education");
    final String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
