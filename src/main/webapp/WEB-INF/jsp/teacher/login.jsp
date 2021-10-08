<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<h1>Login</h1>
<form action="/login" method="post">
    Imię: <input name="name" type="text"><br/>
    Nazwisko <input name="lastName" type="text"><br/>
    Hasło <input name="pass" type="password"><br/>
    <input type="submit" value="Zaloguj">
</form>