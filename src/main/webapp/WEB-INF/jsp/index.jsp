<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<c:if test="${loggedName != 'niezalogowany'}"><h4>Cześć ${loggedName}<h4/><br/>
    <h3><a href="/logged/mycourses">Twoje przedmioty</a></h3><br/>
    <h3><a href="/logout">Wyloguj się</a></h3><br/>
</c:if>



<c:if test="${loggedName == 'niezalogowany'}">
    <h3><a href="/login">Zaloguj się</a> </h3> <br/>
    <h3><a href="/register">Zarejestruj się</a></h3>
</c:if>