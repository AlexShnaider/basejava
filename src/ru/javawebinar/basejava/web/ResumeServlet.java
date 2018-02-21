package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {
    private final Storage storage = Config.getInstance().getSqlStorage();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume r = storage.get(uuid);
        r.setFullName(fullName);
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                r.addContact(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }

        for (SectionType type : SectionType.values()) {
            String[] values = request.getParameterValues(type.name());
            switch (type) {
                case PERSONAL:
                case OBJECTIVE:
                    if (values[0] != null && values[0].trim().length() != 0) {
                        r.addSection(type, new TextSection(values[0]));
                    } else {
                        r.getSections().remove(type);
                    }
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    ListSection listSection = (ListSection) r.getSection(type);
                    for (int i = 0, j = 0; i < values.length; i++) {
                        if (values[i] != null && values[i].trim().length() != 0) {
                            listSection.addLine(values[i]);
                        } else {
                            if (listSection != null) {
                                listSection.getLines().remove(i - j++);
                            }
                        }
                    }
                    if (listSection != null && listSection.getLines().size() != 0) {
                        r.addSection(type, listSection);
                    } else {
                        r.getSections().remove(type);
                    }
            }
        }
        storage.update(r);
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                r = storage.get(uuid);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }
}
