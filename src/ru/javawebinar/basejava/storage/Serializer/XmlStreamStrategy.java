package ru.javawebinar.basejava.storage.Serializer;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlStreamStrategy implements SerializationStrategy {

    private final XmlParser xmlParser;

    public XmlStreamStrategy() {
        xmlParser = new XmlParser(Resume.class, Section.class, Link.class, TextSection.class, ListSection.class
                , OrganizationSection.class, Organization.class, Organization.Position.class);
    }

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (Writer writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            xmlParser.marshal(resume, writer);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return (Resume) xmlParser.unmarshal(reader);
        }
    }
}
