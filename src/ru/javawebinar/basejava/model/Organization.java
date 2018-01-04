package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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

    public static class Position {
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
    }
}
