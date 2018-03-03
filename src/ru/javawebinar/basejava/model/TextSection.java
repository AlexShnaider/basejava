package ru.javawebinar.basejava.model;

import java.util.Objects;

public class TextSection extends Section {

    public static final TextSection EMPTY = new TextSection("");

    private String text;

    public TextSection() {

    }

    public TextSection(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "text='" + text + '\'' +
                "} " + super.toString();
    }
}
