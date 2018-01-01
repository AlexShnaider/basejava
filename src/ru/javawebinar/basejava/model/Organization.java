package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link organization;
    private final List<LocalDate> startDate;
    private final List<LocalDate> finishDate;
    private final List<String> textTitle;
    private final List<String> text;

    public Organization(String name, String url, List<LocalDate> startDate, List<LocalDate> finishDate
            , List<String> textTitle, List<String> text) {
        Objects.requireNonNull(startDate, "startDate mustn't be null");
        Objects.requireNonNull(finishDate, "finishDate mustn't be null");
        Objects.requireNonNull(textTitle, "textTitle mustn't be null");
        this.organization = new Link(name, url);
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.textTitle = textTitle;
        this.text = text;
    }

    public Link getOrganization() {
        return organization;
    }

    public List<LocalDate> getStartDate() {
        return startDate;
    }

    public List<LocalDate> getFinishDate() {
        return finishDate;
    }

    public List<String> getTextTitle() {
        return textTitle;
    }

    public List<String> getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!organization.equals(that.organization)) return false;
        if (!startDate.equals(that.startDate)) return false;
        if (!finishDate.equals(that.finishDate)) return false;
        if (!textTitle.equals(that.textTitle)) return false;
        return text != null ? text.equals(that.text) : that.text == null;
    }

    @Override
    public int hashCode() {
        int result = organization.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + finishDate.hashCode();
        result = 31 * result + textTitle.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "organization=" + organization +
                ", startDate=" + startDate.toString() +
                ", finishDate=" + finishDate.toString() +
                ", textTitle=" + textTitle.toString() +
                ", text=" + text +
                '}';
    }
}
