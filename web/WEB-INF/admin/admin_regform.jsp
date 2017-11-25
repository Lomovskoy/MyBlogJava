<%-- 
    Document   : admin_regform
    Created on : Nov 23, 2017, 12:46:52 PM
    Author     : pupil
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file = "../layouts/header.jsp"%>

<div class="container ">
    <br>
    <h4 class="text-center text-dark">Панель регистрации</h4>
    <hr>
    <p class="text-center">${info}</p>
    <form class="col-4 mt-4 p-4 border border-secondary rounded container" action="?page=registration" method="POST">

        <div class="form-group">
            <label for="exampleInputEmail1">Веш логин на сайте</label>
            <input type="text" class="form-control" name="login" placeholder="Enter login">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Введите пароль для сайта</label>
            <input type="password" class="form-control" name="password1" placeholder="Enter password">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Повторите пароль для сайта</label>
            <input type="password" class="form-control" name="password2" placeholder="Enter password">
        </div>
        <div class="row m-0 mt-2">
            <button type="submit" class="btn btn-primary ml-auto">Вход</button>
        </div>
    </form>
</div>

<%@include file = "../layouts/footer.jsp" %>
