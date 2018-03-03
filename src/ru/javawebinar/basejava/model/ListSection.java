package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ListSection extends Section {

    public static final ListSection EMPTY = new ListSection(Collections.singletonList(""));

    private final List<String> lines;

    public ListSection() {
        lines = new ArrayList<>();
    }

    public ListSection(List<String> lines) {
        this.lines = lines;
    }

    public List<String> getLines() {
        return lines;
    }

    public void addLine(String line) {
        if (line != null && !lines.contains(line)) {
            lines.add(line);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(lines, that.lines);
    }

    @Override
    public int hashCode() {

        return Objects.hash(lines);
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "lines=" + lines +
                "} " + super.toString();
    }
}
