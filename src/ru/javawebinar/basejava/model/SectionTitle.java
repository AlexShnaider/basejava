package ru.javawebinar.basejava.model;

public enum SectionTitle {
    PERSONAL("Personal qualities"),
    OBJECTIVE("Position"),
    ACHIEVEMENT("Achievements"),
    QUALIFICATIONS("Qualifications"),
    EXPERIENCE("Work experience"),
    EDUCATION("Education");
    String title;

    SectionTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
