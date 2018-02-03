package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.util.LocalDateXmlAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private Link organization;
    private final List<Position> positions;

    public Organization() {
        positions = new ArrayList<>();
    }

    public Organization(String name, String url, List<Position> positions) {
        this.organization = new Link(name, url);
        this.positions = positions;
    }

    public Organization(Link organization, List<Position> positions) {
        this.organization = organization;
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

        return organization.equals(that.organization) && positions.equals(that.positions);
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

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Position implements Serializable {

        @XmlJavaTypeAdapter(LocalDateXmlAdapter.class)
        private LocalDate startDate;

        @XmlJavaTypeAdapter(LocalDateXmlAdapter.class)
        private LocalDate finishDate;

        private String textTitle;
        private String text;

        public Position() {
        }

        public Position(LocalDate startDate, LocalDate finishDate, String textTitle, String text) {
            Objects.requireNonNull(startDate, "startDate mustn't be null");
            Objects.requireNonNull(finishDate, "finishDate mustn't be null");
            Objects.requireNonNull(textTitle, "textTitle mustn't be null");
            this.startDate = startDate;
            this.finishDate = finishDate;
            this.textTitle = textTitle;
            this.text = Objects.isNull(text) ? "" : text;
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
            return Objects.equals(startDate, position.startDate) &&
                    Objects.equals(finishDate, position.finishDate) &&
                    Objects.equals(textTitle, position.textTitle) &&
                    Objects.equals(text, position.text);
        }

        @Override
        public int hashCode() {

            return Objects.hash(startDate, finishDate, textTitle, text);
        }
    }
}
