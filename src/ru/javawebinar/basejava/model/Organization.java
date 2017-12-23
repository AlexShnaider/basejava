package ru.javawebinar.basejava.model;

public class Organization {
    private String name;
    private String startDate;
    private String finishDate;
    private String textTitle;
    private String text;

    public Organization(String name, String startDate, String finishDate, String textTitle, String text) {
        this.name = name;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.textTitle = textTitle;
        this.text = text;
    }
}
