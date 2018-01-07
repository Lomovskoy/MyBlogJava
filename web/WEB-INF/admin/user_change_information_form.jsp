<%-- 
    Document   : user_change_information_form
    Created on : 20.12.2017, 21:56:07
    Author     : imxo
--%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file = "../layouts/header.jsp"%>

<br>
<h4 class="text-center text-dark">Измените информацию о себе</h4>
<hr>
<p class="text-center">${info}</p>
<div class="container">
    <div class="row">
        <form class="col-6 mt-4 ml-3 p-4 border border-secondary rounded" action="?page=changeinformation" method="POST">
            <div class="form-group">
                <label for="exampleInputLogin">Ведите логин если хотите изменить</label>
                <input type="text" class="form-control" name="login" placeholder="Enter login">
                <small id="emailHelp" class="form-text text-muted">Ваш логин должен быть уникальным.</small>
            </div>
            <div class="form-group">
                <label for="exampleInputLogin">Ведите email если хотите изменить</label>
                <input type="email" class="form-control" name="email" placeholder="Enter email">
                <small id="emailHelp" class="form-text text-muted">Ваша почта должна быть уникальной.</small>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Ведите пароль если хотите изменить</label>
                <input type="password" class="form-control" name="password1" placeholder="Enter password">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Повторите пароль если хотите изменить</label>
                <input type="password" class="form-control" name="password2" placeholder="Enter password">
                <small id="emailHelp" class="form-text text-muted">Пароли должны совпадать.</small>
            </div>
            <div class="row m-0 mt-2">
                <button type="submit" class="btn btn-primary ml-auto">Изменить</button>
            </div>
        </form>
        <form class="col-4 mt-4 p-4 border border-secondary rounded ml-auto mr-3" action="changeimage" method="POST" enctype="multipart/form-data">
            <div class="form-group">
                <label for="exampleFormControlFile1">Example file input</label>
                <input type="file" class="form-control-file" name="image">
            </div>
            <div class="row m-0 mt-2">
                <button type="submit" class="btn btn-primary ml-auto">Изменить</button>
            </div>
            <img class="img-thumbnail mr-2 mt-4 w-15 p-1" src="imageServlet/${user.image}" alt="фото">
        </form>
    </div>
</div>
</div>
<%@include file = "../layouts/footer.jsp" %>
