package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class MainResume {
    public static void main(String[] args) {
        TextSection personalSection = new TextSection("quality1, quality2, quality3");
        List<String> achievements = Arrays.asList("achievement1", "achievement2", "achievement3");
        ListSection achievementSection = new ListSection(achievements);
        Resume resume = new Resume("Alex");
        LocalDate date = LocalDate.of(2007, 12, 1);
        String organizationTitle = "organizationTitle";
        String organizationText = "organizationText";
        List<Organization.Position> positions = Arrays.asList(new Organization.Position(date, date, organizationTitle, organizationText));
        Organization organization1 = new Organization("company1", null, positions);
        Organization organization2 = new Organization("company2", null, positions);
        List<Organization> organizations = Arrays.asList(organization1, organization2);
        OrganizationSection experienceSection = new OrganizationSection(organizations);
        resume.addSection(SectionType.PERSONAL, personalSection);
        resume.addSection(SectionType.ACHIEVEMENT, achievementSection);
        resume.addSection(SectionType.EXPERIENCE, experienceSection);
        System.out.println(resume.toString());
        System.out.println(resume.getClass().getSimpleName());
    }
}
