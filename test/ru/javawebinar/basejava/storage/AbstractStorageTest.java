package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Exceptions.ExistStorageException;
import ru.javawebinar.basejava.Exceptions.NotExistStorageException;
import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {
    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String UUID_4 = "uuid4";
    protected static final String NAME_1 = "Alexay";
    protected static final String NAME_2 = "Boris";
    protected static final String NAME_3 = "Viktor";
    protected static final String NAME_4 = "Grigory";
    protected static final Resume RESUME1;
    protected static final Resume RESUME2;
    protected static final Resume RESUME3;
    protected static final Resume RESUME4;

    protected final Storage storage;

    static {
        RESUME1 = new Resume(UUID_1, NAME_1);
        fillResume(RESUME1);
        RESUME2 = new Resume(UUID_2, NAME_2);
        fillResume(RESUME2);
        RESUME3 = new Resume(UUID_3, NAME_3);
        fillResume(RESUME3);
        RESUME4 = new Resume(UUID_4, NAME_4);
        fillResume(RESUME4);
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static void fillResume(Resume resume) {
        resume.addContact(ContactType.PHONE, "985-123-45-67");
        resume.addContact(ContactType.MAIL, "Some.email@gmail.com");
        resume.addContact(ContactType.SKYPE, "SomeSkypeId");
        TextSection personalSection = new TextSection("quality1, quality2, quality3");
        resume.addSection(SectionType.PERSONAL, personalSection);
        ListSection achievementSection = new ListSection(Arrays.asList("achievement1", "achievement2", "achievement3"));
        resume.addSection(SectionType.ACHIEVEMENT, achievementSection);
        List<LocalDate> date = Arrays.asList(LocalDate.of(2007, 12, 1));
        List<String> organizationTitle = Arrays.asList("organizationTitle");
        List<String> organizationText = Arrays.asList("organizationText");
        Organization organization1 = new Organization("company1", null, date, date, organizationTitle, organizationText);
        Organization organization2 = new Organization("company2", null, date, date, organizationTitle, organizationText);
        OrganizationSection experienceSection = new OrganizationSection(Arrays.asList(organization1, organization2));
        resume.addSection(SectionType.EXPERIENCE, experienceSection);
        Organization university1 = new Organization("university1", null, date, date, organizationTitle, null);
        Organization university2 = new Organization("university2", null, date, date, organizationTitle, null);
        OrganizationSection educationSection = new OrganizationSection(Arrays.asList(university1, university2));
        resume.addSection(SectionType.EDUCATION, educationSection);
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
    }

    @Test
    public void clear() throws Exception {
        Assert.assertEquals(3, storage.size());
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() throws Exception {
        Assert.assertEquals(3, storage.size());
        storage.save(RESUME4);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(RESUME4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExistedUuid() throws Exception {
        Assert.assertEquals(3, storage.size());
        storage.save(RESUME1);
    }

    @Test
    public void update() throws Exception {
        Assert.assertEquals(3, storage.size());
        storage.update(RESUME1);
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistedUuid() throws Exception {
        Assert.assertEquals(3, storage.size());
        storage.update(RESUME4);
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(3, storage.size());
        Assert.assertEquals(RESUME1, storage.get(UUID_1));
        Assert.assertEquals(RESUME2, storage.get(UUID_2));
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistedUuid() throws Exception {
        storage.get(UUID_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        Assert.assertEquals(3, storage.size());
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        storage.delete(UUID_2);
        Assert.assertEquals(1, storage.size());
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistedUuid() throws Exception {
        storage.delete(UUID_4);
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
        storage.save(RESUME4);
        Assert.assertEquals(4, storage.size());
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> answer = storage.getAllSorted();
        Assert.assertTrue(answer.get(0).equals(RESUME1));
        Assert.assertTrue(answer.get(1).equals(RESUME2));
        Assert.assertTrue(answer.get(2).equals(RESUME3));
    }
}