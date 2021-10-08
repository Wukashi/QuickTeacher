<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<H1>Dodaj przedmiot</H1>
<form:form method="post" modelAttribute="course">

    <form:hidden path="id"/>

    Nazwa przedmiotu: <form:input path="name" /> <br />
    <form:errors path="name"/> <br />

    <input type="submit" value="Dodaj">

</form:form>