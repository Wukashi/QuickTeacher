<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ include file="headsection.jsp" %>
<section id="hero" class="hero d-flex align-items-center">

    <div class="container">

        <form class="padding-small text-center" action="/login" method="post">
            <h1 class="text-color-darker">Logowanie</h1>
            <div class="form-group">
                <input type="text" class="form-control" id="name" name="name"
                       placeholder="Podaj swoje imię">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="lastName" name="lastName"
                       placeholder="Podaj swoje nazwisko">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="pass" name="pass"
                       placeholder="podaj hasło">
            </div>
            <button class="btn btn-color rounded-0" type="submit">Zaloguj</button>
        </form><br/><br/>
        <h4 class="text-color-darker">Jesteś niezarejestrowany?</h4><br/>
        <i class="bi bi-chevron-right"></i> <a href="/register">Zarejestruj się</a>
    </div>

</section>
<%@ include file="footer.jsp"%>
