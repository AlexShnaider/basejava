package ru.javawebinar.basejava.storage.Serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamStrategy implements SerializationStrategy {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            dos.writeInt(resume.getContacts().size());
            for (Map.Entry<ContactType, String> contact : resume.getContacts().entrySet()) {
                dos.writeUTF(contact.getKey().name());
                dos.writeUTF(contact.getValue());
            }
            dos.writeInt(resume.getSections().size());
            for (Map.Entry<SectionType, Section> section : resume.getSections().entrySet()) {
                SectionType sectionType = section.getKey();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        TextSection textSection = (TextSection) section.getValue();
                        dos.writeUTF(textSection.getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ListSection listSection = (ListSection) section.getValue();
                        dos.writeInt(listSection.getLines().size());
                        for (String line : listSection.getLines()) {
                            dos.writeUTF(line);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        OrganizationSection organizationSection = (OrganizationSection) section.getValue();
                        dos.writeInt(organizationSection.getOrganizations().size());
                        for (Organization organization : organizationSection.getOrganizations()) {
                            Link link = organization.getOrganization();
                            dos.writeUTF(link.getName());
                            dos.writeUTF(link.getUrl());
                            dos.writeInt(organization.getPositions().size());
                            for (Organization.Position position : organization.getPositions()) {
                                dos.writeUTF(position.getStartDate().toString());
                                dos.writeUTF(position.getFinishDate().toString());
                                dos.writeUTF(position.getTextTitle());
                                dos.writeUTF(position.getText());
                            }
                        }
                        break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            Resume resume = new Resume(dis.readUTF(), dis.readUTF());
            int contactsAmount = dis.readInt();
            for (int i = 0; i < contactsAmount; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            int sectionsAmount = dis.readInt();
            for (int i = 0; i < sectionsAmount; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        int numberLines = dis.readInt();
                        ListSection listSection = new ListSection();
                        for (int j = 0; j < numberLines; j++) {
                            listSection.addLine(dis.readUTF());
                        }
                        resume.addSection(sectionType, listSection);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        int numberOrganizations = dis.readInt();
                        OrganizationSection organizationSection = new OrganizationSection();
                        for (int j = 0; j < numberOrganizations; j++) {
                            Link link = new Link(dis.readUTF(), dis.readUTF());
                            int positionsNumber = dis.readInt();
                            List<Organization.Position> positions = new ArrayList<>();
                            for (int k = 0; k < positionsNumber; k++) {
                                LocalDate startDate = LocalDate.parse(dis.readUTF());
                                LocalDate finishDate = LocalDate.parse(dis.readUTF());
                                String textTitle = dis.readUTF();
                                String text = dis.readUTF();
                                positions.add(new Organization.Position(startDate, finishDate, textTitle, text));
                            }
                            organizationSection.addOrganization(new Organization(link, positions));
                        }
                        resume.addSection(sectionType, organizationSection);
                        break;
                }
            }
            return resume;
        }
    }
}
