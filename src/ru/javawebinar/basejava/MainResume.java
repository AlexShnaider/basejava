package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.util.Arrays;
import java.util.List;

public class MainResume {
    public static void main(String[] args) {
        TextSection personalSection = new TextSection(SectionTitle.PERSONAL.getTitle(),"quality1, quality2, quality3");
        List<String> achievements = Arrays.asList("achievement1", "achievement2","achievement3");
        ListSection achievementSection = new ListSection(SectionTitle.ACHIEVEMENT.getTitle(), achievements);
        Resume resume = new Resume("Alex");
        Organization organization1 = new Organization("company1", "startDate1", "finishDate1", "jobTitle1", "some text1");
        Organization organization2 = new Organization("company2", "startDate2", "finishDate2", "jobTitle2", "some text2");
        List<Organization> organizations = Arrays.asList(organization1, organization2);
        OrganizationSection experienceSection = new OrganizationSection(SectionTitle.EXPERIENCE.getTitle(), organizations);
        resume.addSection(SectionTitle.PERSONAL,personalSection);
        resume.addSection(SectionTitle.ACHIEVEMENT,achievementSection);
        resume.addSection(SectionTitle.EXPERIENCE,experienceSection);
        System.out.println(resume.toString());
    }
}
