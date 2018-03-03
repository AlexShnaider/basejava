<%--
  Created by IntelliJ IDEA.
  User: Sancho
  Date: 20.02.2018
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Resume ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.ContactType, java.lang.String>"/>
            <img src="img/${contactEntry.getKey()}.png"> ${contactEntry.value}<br/>
        </c:forEach>
    </p>

    <c:forEach var="type" items="${resume.sections}">
        <dl>
            <dt>${type.key.title}:</dt>
            <c:set var="section" value="${resume.getSection(type.key)}"/>
            <c:choose>
                <c:when test="${section.getClass().getSimpleName() == 'TextSection'}">
                    <dd>${section.getText()}</dd>
                    <br/>
                </c:when>

                <c:when test="${section.getClass().getSimpleName() == 'ListSection'}">
                    <c:forEach var="line" items="${section.getLines()}">
                        <dd>${line}</dd>
                        <br/>
                    </c:forEach>
                </c:when>

                <c:when test="${section.getClass().getSimpleName() == 'OrganizationSection'}">
                    <c:forEach var="organization" items="${section.getOrganizations()}">
                        <dd><a href="http://${organization.getOrganization().getUrl()}">
                                ${organization.getOrganization().getName()}</a></dd>
                        <br/>
                        <c:forEach var="position" items="${organization.getPositions()}">
                            <dd>${position.getStartDate()} - ${position.getFinishDate()}</dd>
                            <dd>${position.getTextTitle()}</dd>
                            <dd>${position.getText()}</dd>
                            <br/>
                        </c:forEach>
                    </c:forEach>
                </c:when>
            </c:choose>
        </dl>
    </c:forEach>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
