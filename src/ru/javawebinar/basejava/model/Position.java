package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Position {
    private final LocalDate startDate;
    private final LocalDate finishDate;
    private final String textTitle;
    private final String text;

    public Position(LocalDate startDate, LocalDate finishDate, String textTitle, String text) {
        Objects.requireNonNull(startDate, "startDate mustn't be null");
        Objects.requireNonNull(finishDate, "finishDate mustn't be null");
        Objects.requireNonNull(textTitle, "textTitle mustn't be null");
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.textTitle = textTitle;
        this.text = text;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public String getTextTitle() {
        return textTitle;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (!startDate.equals(position.startDate)) return false;
        if (!finishDate.equals(position.finishDate)) return false;
        if (!textTitle.equals(position.textTitle)) return false;
        return text != null ? text.equals(position.text) : position.text == null;
    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + finishDate.hashCode();
        result = 31 * result + textTitle.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", textTitle='" + textTitle + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
