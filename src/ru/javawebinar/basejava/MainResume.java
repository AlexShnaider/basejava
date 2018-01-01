package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.*;

public class MainResume {
    public static void main(String[] args) {
        TextSection personalSection = new TextSection("quality1, quality2, quality3");
        List<String> achievements = Arrays.asList("achievement1", "achievement2", "achievement3");
        ListSection achievementSection = new ListSection(achievements);
        Resume resume = new Resume("Alex");
        List<LocalDate> date = Arrays.asList(LocalDate.of(2007, 12, 1));
        List<String> organizationTitle = Arrays.asList("organizationTitle");
        List<String> organizationText = Arrays.asList("organizationText");
        Organization organization1 = new Organization("company1", null, date, date, organizationTitle, organizationText);
        Organization organization2 = new Organization("company2", null, date, date, organizationTitle, organizationText);
        List<Organization> organizations = Arrays.asList(organization1, organization2);
        OrganizationSection experienceSection = new OrganizationSection(organizations);
        resume.addSection(SectionType.PERSONAL, personalSection);
        resume.addSection(SectionType.ACHIEVEMENT, achievementSection);
        resume.addSection(SectionType.EXPERIENCE, experienceSection);
        System.out.println(resume.toString());
    }
}
