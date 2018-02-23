<%--
  Created by IntelliJ IDEA.
  User: Sancho
  Date: 20.02.2018
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
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
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl class="inline">
            <dt>Name:</dt>
            <dd><input type="text" name="fullName" size=30 value="${resume.fullName}"></dd>
        </dl>

        <h3>Contacts:</h3>
        <c:forEach var="type" items="${ContactType.values()}">
            <dl class="inline">
                <dt>${type.title}:</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>

        <h3>Sections:</h3>
        <c:forEach var="type" items="${SectionType.values()}">
            <dl>
                <dt>${type.title}:</dt>
                <c:choose>

                    <c:when test="${resume.getSection(type).getClass().getSimpleName() == 'TextSection'}">
                        <dd>
                            <input type="text" name="${type.name()}" size=100
                                   value="${resume.getSection(type).getText()}"/>
                        </dd>
                    </c:when>

                    <c:when test="${resume.getSection(type).getClass().getSimpleName() == 'ListSection'}">
                        <dd>
                            <c:forEach var="line" items="${resume.getSection(type).getLines()}">
                                <input type="text" name="${type.name()}" size=100 value="${line}"><br/> <br/>
                            </c:forEach>
                        </dd>
                    </c:when>

                    <%--<c:when test="${type.name() == SectionType.EXPERIENCE || type.name() == SectionType.EDUCATION}">--%>
                    <c:when test="${resume.getSection(type).getClass().getSimpleName() == 'OrganizationSection'}">
                        <c:set var="organizationMarker" value="${0}"/>
                        <c:forEach var="organization" items="${resume.getSection(type).getOrganizations()}">
                            <dd>
                                Company <input type="text" name="${type.name()}"
                                               size=60 value="${organization.getOrganization().getName()}"><br/> <br/>
                                Url <input type="text" name="${type.name()}Url" size=60
                                           value="${organization.getOrganization().getUrl()}"><br/> <br/>

                                <c:forEach var="position" items="${organization.getPositions()}">
                                    From
                                    <input type="text" name="StartDate${type.name()}${organizationMarker}"
                                           size=60 value="${position.getStartDate()}"><br/> <br/>
                                    To
                                    <input type="text"
                                           name="FinishDate${type.name()}${organizationMarker}" size=60
                                           value="${position.getFinishDate()}"><br/> <br/>
                                    Position title
                                    <input type="text"
                                           name="PositionName${type.name()}${organizationMarker}"
                                           size=60
                                           value="${position.getTextTitle()}"><br/> <br/>
                                    Position description
                                    <input type="text"
                                           name="PositionDescription${type.name()}${organizationMarker}"
                                           size=60
                                           value="${position.getText()}"><br/> <br/><br/> <br/>
                                </c:forEach>
                                <c:set var="organizationMarker" value="${organizationMarker + 1}"/>
                            </dd>
                        </c:forEach>
                    </c:when>

                    <c:when test="${resume.getSection(type).getClass().getSimpleName() == null}">
                        <dd class="inline"><input type="text" name="${type.name()}" size=100 value=""></dd>
                    </c:when>

                    <c:otherwise>
                        <c:set var="answer" value="It is not a valid Section"/>
                    </c:otherwise>
                </c:choose>
            </dl>
        </c:forEach>
        <hr>
        <button type="submit">Save</button>
        <button type="button" onclick="window.history.back()">Cancel</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
