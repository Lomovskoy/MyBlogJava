<%-- 
    Document   : admin_addarticle
    Created on : Nov 17, 2017, 12:37:35 PM
    Author     : pupil
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file = "../layouts/header.jsp" %>

<div class="container mt-4">
    <h5 class="text-center">Добавить статью</h5>
    <div class="border border-secondary rounded p-4">
        <form action="?page=addarticle" method="POST">
            <div class="form-group">
                <label for="exampleFormControlInput1">Заголовок статьи</label>
                <input type="text" name="caption" class="form-control" id="exampleFormControlInput1">
            </div>
            <div class="form-group">
                <label for="exampleFormControlTextarea1">Текст статьи</label>
                <textarea class="form-control" name="content" id="exampleFormControlTextarea1" rows="20"></textarea>
            </div>
            <div class="row m-0 mt-2">
                <button type="submit" class="btn btn-primary ml-auto">Опубликовать</button>
            </div>
        </form>
    </div>
</div>

<%@include file = "../layouts/footer.jsp" %>