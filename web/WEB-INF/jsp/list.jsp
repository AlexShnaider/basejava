<%@ page import="ru.javawebinar.basejava.model.Resume" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.basejava.model.ContactType" %><%--
  Created by IntelliJ IDEA.
  User: Sancho
  Date: 18.02.2018
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>List of All resumes</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Name</th>
            <th>Email</th>
        </tr>
        <%
            for (Resume resume : (List<Resume>) request.getAttribute("resumes")) {
        %>
        <tr>
            <td>
                <a href="resume?uuid= <%=resume.getUuid()%> "> <%=resume.getFullName()%> </a>
            </td>
            <td><%=resume.getContact(ContactType.MAIL)%></td>
        </tr>
        <% } %>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
