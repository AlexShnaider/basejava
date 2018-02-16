package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static ru.javawebinar.basejava.model.SectionType.*;

public class ResumeServlet extends HttpServlet {
    private final Storage storage = Config.getInstance().getSqlStorage();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Resume> resumes = storage.getAllSorted();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String htmlResponse = "" +
                "<html>\n" +
                  "<head>\n" +
                    "<title>Таблица</title>\n" +
                  "</head>\n" +
                  "<body>\n" +
                    "<table border=\"2\">\n" +
                      "<tr>\n" +
                        "<td>Uuid</td>\n" +
                        "<td>Full name</td>\n" +
                        "<td>Contacts</td>\n" +
                        "<td>Personal qualities</td>\n" +
                        "<td>Position</td>\n" +
                        "<td>Achievements</td>\n" +
                        "<td>Qualifications</td>\n" +
                      "</tr>\n";
        String name = request.getParameter("name");
        if (name != null) {
            Resume resume = findResume(resumes, name);
            if (resume == null) {
                response.getWriter().write("There is no " + name + " in the data base");
            } else {
                htmlResponse = writeHtmlResume(resume, htmlResponse);
            }
        } else {
            for (Resume resume : resumes) {
                htmlResponse = writeHtmlResume(resume, htmlResponse);
            }
        }
        htmlResponse += "</table>" + "</body>" + "</html>";
        response.getWriter().write(htmlResponse);
    }

    private String writeHtmlResume(Resume resume, String htmlResponse) {
        htmlResponse += "<tr>"
                + "<td>" + resume.getUuid() + "</td>"
                + "<td>" + resume.getFullName() + "</td>"
                + "<td>";
        for (Map.Entry<ContactType, String> contact : resume.getContacts().entrySet()) {
            htmlResponse += contact.getKey().name() + " : " + contact.getValue() + "<br /br>";
        }
        htmlResponse += "</td>";
        Map<SectionType, Section> sections = resume.getSections();
        htmlResponse = writeHtmlSection(sections.get(PERSONAL), htmlResponse);
        htmlResponse = writeHtmlSection(sections.get(OBJECTIVE), htmlResponse);
        htmlResponse = writeHtmlSection(sections.get(ACHIEVEMENT), htmlResponse);
        return htmlResponse = writeHtmlSection(sections.get(QUALIFICATIONS), htmlResponse) + "</tr>";
    }

    private String writeHtmlSection(Section section, String htmlResponse) {
        if (section == null) {
            return htmlResponse += "<td>" + "</td>";
        }
        if (section instanceof TextSection) {
            TextSection textSection = (TextSection) section;
            htmlResponse += "<td>" + textSection.getText() + "</td>";
        }
        if (section instanceof ListSection) {
            ListSection listSection = (ListSection) section;
            htmlResponse += "<td>";
            for (String line : listSection.getLines()) {
                htmlResponse += line + "<br /br>";
            }
            htmlResponse += "</td>";
        }
        return htmlResponse;
    }

    private Resume findResume(List<Resume> resumes, String fullName) {
        for (Resume resume : resumes) {
            if (resume.getFullName().equals(fullName)) {
                return resume;
            }
        }
        return null;
    }
}
