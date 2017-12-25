package ru.javawebinar.basejava.model;

import java.util.Date;

public class Organization {
    private String name;
    private Date startDate;
    private Date finishDate;
    private String textTitle;
    private String text;

    public Organization(String name, Date startDate, Date finishDate, String textTitle, String text) {
        this.name = name;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.textTitle = textTitle;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public String getTextTitle() {
        return textTitle;
    }

    public String getText() {
        return text;
    }
}
