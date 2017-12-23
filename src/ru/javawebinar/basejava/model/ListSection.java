package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends Section<List<String>> {
    //private List<String> lines = new ArrayList<>();
    public ListSection(String title, List<String> lines) {
        super(title, lines);
    }
}
