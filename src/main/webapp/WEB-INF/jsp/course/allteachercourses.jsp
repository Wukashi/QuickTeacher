<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
Wybierz przedmiot z którego prowadzisz zajęcia:<br/>
<c:if test="${teacherCourses.size() == 0}">
    Nie prowadzisz żadnych zajęć<br/>
</c:if>
<c:if test="${teacherCourses.size() != 0}">
    <c:forEach var = "course" items="teacherCourses">
        <c:out value = "${teacherCourses.name}"/><br/>
    </c:forEach>
</c:if>
<br/>
<br/>
<a href="/logged/avilablecourses">Dodaj kursy, które chcesz prowadzić</a>


