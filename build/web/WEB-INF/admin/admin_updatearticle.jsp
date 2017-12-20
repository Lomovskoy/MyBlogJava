<%-- 
    Document   : admin_addarticle
    Created on : Nov 17, 2017, 12:37:35 PM
    Author     : pupil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file = "../layouts/header.jsp"%>

<div class="container mt-4">
    <h5 class="text-center">Добавить статью</h5>
    <div class="border border-secondary rounded p-4">
        <form action="?page=updatearticle&id=${article.id}" method="POST">
            <div class="form-group">
                <label for="exampleFormControlInput1">Заголовок статьи</label>
                <input type="text" name="caption" class="form-control" id="exampleFormControlInput1" value="${article.caption}">
            </div>
            <div class="form-group">
                <label for="exampleFormControlTextarea1">Текст статьи</label>
                <textarea class="form-control" name="content" id="exampleFormControlTextarea1" rows="20">${article.content}</textarea>
            </div>
            <div class="row m-0 mt-2">
                <button type="submit" class="btn btn-primary ml-auto">Изменить</button>
            </div>
            <select class="form-control form-control-lg col-3" name="active">
                <option value="1">Опубликовать</option>
                <option value="0">Скрыть</option>
            </select>
        </form>

        <div class="container">
            <h5 class="text-center">Добавить картинку</h5>

            <p class="text-center">Тег для вставки</p>
            <br>
            <input class="form-control" id="qwe2">

            <div class="row">
                <div class="form-group col-4">
                    <label for="inputState">Имя картинкиы</label>
                    <select id="inputState" class="form-control" onchange="document.getElementById('qwe2').value = '<img class=&quot;img-thumbnail mr-2 float-left w-15 p-1&quot; src=&quot;fileServlet/' + this.value + '&quot;>';">
                        <c:forEach var="image" items="${images}" varStatus="status">
                            <option value="${image.getName()}">${image.getName()}</option>  
                        </c:forEach>
                    </select>
                </div>
                <div class="col-6">
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">№</th>
                                <th scope="col">image</th>
                                <th scope="col">Name</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="image" items="${images}" varStatus="status">
                                <tr>
                                    <th scope="row">${status.index+1}</th>
                                    <td><img class="img-fluid " style="width: 55px"src="fileServlet/${image.getName()}"></td>
                                    <td>${image.getName()}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file = "../layouts/footer.jsp" %>