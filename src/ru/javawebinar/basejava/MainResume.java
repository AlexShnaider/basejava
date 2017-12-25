package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;
import java.util.*;

public class MainResume {
    public static void main(String[] args) {
        TextSection personalSection = new TextSection("quality1, quality2, quality3");
        List<String> achievements = Arrays.asList("achievement1", "achievement2","achievement3");
        ListSection achievementSection = new ListSection(achievements);
        Resume resume = new Resume("Alex");
        Calendar myCalendar = new GregorianCalendar(2014, 2, 11);
        Date myDate = myCalendar.getTime();
        Organization organization1 = new Organization("company1", myDate, myDate, "jobTitle1", "some text1");
        Organization organization2 = new Organization("company2", myDate, myDate, "jobTitle2", "some text2");
        List<Organization> organizations = Arrays.asList(organization1, organization2);
        OrganizationSection experienceSection = new OrganizationSection(organizations);
        resume.addSection(SectionTitle.PERSONAL,personalSection);
        resume.addSection(SectionTitle.ACHIEVEMENT,achievementSection);
        resume.addSection(SectionTitle.EXPERIENCE,experienceSection);
        System.out.println(resume.toString());
    }
}
