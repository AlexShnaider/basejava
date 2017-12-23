package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class OrganizationSection extends Section<List<Organization>> {
    //private List<Organization> organizations = new ArrayList<>();

    public OrganizationSection(String title, List<Organization> organizations) {
        super(title, organizations);
    }
}
