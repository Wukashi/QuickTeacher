<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ include file="headsection.jsp" %>
<section id="hero" class="hero d-flex align-items-center">

    <div class="container">

        <form:form class="padding-small text-center" method="post" modelAttribute="teacher">
            <h1 class="text-color-darker">Rejestracja</h1>
            <div class="form-group">
                <form:input class="form-control" path="firstName" placeholder="Podaj swoje imię"/>
                <form:errors path="firstName"/> <br />
            </div>
            <div class="form-group">
                <form:input class="form-control" path="lastName" placeholder="Podaj swoje nazwisko"/>
                <form:errors path="lastName"/> <br />
            </div>
            <form:input class="form-control" path="pass" type="password" placeholder="Podaj hasło"/>
            <form:errors path="pass"/> <br />
            <button class="btn btn-color rounded-0" type="submit">Zarejestruj</button>
        </form:form>
    </div>

</section>
<%@ include file="footer.jsp"%>

