package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Objects;


@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Section<T> implements Serializable {
    private T content;

    public Section() {
    }

    public Section(T content) {
        Objects.requireNonNull(content, "content mustn't be null");
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Section<?> section = (Section<?>) o;

        return content.equals(section.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

    @Override
    public String toString() {
        return content.toString();
    }
}
