<%-- 
    Document   : admin_panel
    Created on : Nov 15, 2017, 10:33:17 AM
    Author     : kirill
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file = "../layouts/header.jsp" %>
<div class="container ">
    <br>
    <h4 class="text-center text-dark">Вход в панель администратора</h4>
    <hr>
    <p class="text-center">${info}</p>
    <form class="col-4 mt-4 p-4 border border-secondary rounded container" action="?page=login" method="POST">
       
        <div class="form-group">
            <label for="exampleInputEmail1">Login</label>
            <input type="text" class="form-control" name="login" placeholder="Enter login">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" class="form-control" name="password" placeholder="Enter hassword">
        </div>
        <button type="submit" class="btn btn-primary">Вход</button>
    </form>
</div>
<%@include file = "../layouts/footer.jsp" %>

