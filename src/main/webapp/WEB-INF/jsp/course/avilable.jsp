<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<c:if test="${avilablecourses.size() != 0}">
    Dodaj kursy, które będziesz prowadzić:<br/>
    <c:forEach var = "course" items="${avilablecourses}">
        <c:out value = "${course.name}"/><a href="/logged/addcourse/${course.id}">:Będę prowadzić ten kurs</a> <br/>
    </c:forEach>
</c:if>
<c:if test="${avilablecourses.size() == 0}">
    Brak kursów do dodania
</c:if>
===========================<br/>
<a href="/logged/addcourse">Dodaj przedmiot</a>
