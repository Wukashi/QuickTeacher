<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<H1>Zarejestruj się</H1>
<form:form method="post" modelAttribute="teacher">

    <form:hidden path="id"/>

    Imię: <form:input path="firstName" /> <br />
    <form:errors path="firstName"/> <br />

    Nazwisko: <form:input path="lastName"/> <br />
    <form:errors path="lastName"/> <br />

    Hasło: <form:input path="pass" type="password"/> <br />
    <form:errors path="pass"/> <br />

    <input type="submit" value="Rejestruj">

</form:form>