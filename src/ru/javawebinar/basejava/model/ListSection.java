package ru.javawebinar.basejava.model;

import java.util.List;

public class ListSection extends Section<List<String>> {
    public ListSection(List<String> lines) {
        super(lines);
    }
}
