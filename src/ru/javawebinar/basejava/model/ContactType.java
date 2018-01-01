package ru.javawebinar.basejava.model;

public enum ContactType {
    PHONE("Phone"),
    MAIL("Mail"),
    SKYPE("Skype"),
    LINKEDIN("LinkedIn"),
    GITHUB("Github"),
    STACKOVERFLOW("StackOverFlow"),
    HOME_PAGE("Home page");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
