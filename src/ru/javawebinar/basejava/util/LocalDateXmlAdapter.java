package ru.javawebinar.basejava.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class LocalDateXmlAdapter extends XmlAdapter<String, LocalDate> {
    @Override
    public LocalDate unmarshal(String string) {
        return LocalDate.parse(string);
    }

    @Override
    public String marshal(LocalDate localDate) {
        return localDate.toString();
    }
}
