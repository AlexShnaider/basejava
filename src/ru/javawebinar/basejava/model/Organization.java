package ru.javawebinar.basejava.model;

import java.util.List;

public class Organization {
    private final Link organization;
    private final List<Position> positions;

    public Organization(String name, String url, List<Position> positions) {
        this.organization = new Link(name, url);
        this.positions = positions;
    }

    public Link getOrganization() {
        return organization;
    }

    public List<Position> getPositions() {
        return positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!organization.equals(that.organization)) return false;
        return positions.equals(that.positions);
    }

    @Override
    public int hashCode() {
        int result = organization.hashCode();
        result = 31 * result + positions.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "organization=" + organization +
                ", positions=" + positions +
                '}';
    }
}
