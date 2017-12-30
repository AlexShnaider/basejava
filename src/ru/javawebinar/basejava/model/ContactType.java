package ru.javawebinar.basejava.model;

public enum ContactType {
    PHOHE("Phone"),
    MAIL("Mail"),
    SKYPE("Skype"),
    LINKEDIN("LinkedIn"),
    GITHUB("Github"),
    STACKOWERFLOW("StackOverFlow"),
    HOME_PAGE("Home page");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
