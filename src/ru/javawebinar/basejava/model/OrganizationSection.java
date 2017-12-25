package ru.javawebinar.basejava.model;

import java.util.List;

public class OrganizationSection extends Section<List<Organization>> {
    public OrganizationSection(List<Organization> organizations) {
        super(organizations);
    }
}
