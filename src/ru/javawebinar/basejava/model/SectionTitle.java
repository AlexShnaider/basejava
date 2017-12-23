package ru.javawebinar.basejava.model;

public enum SectionTitle {
    PERSONAL("Личные качества"),
    OBJECTIVE("Позиция"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");
    String title;

    SectionTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
