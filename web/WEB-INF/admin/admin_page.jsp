<%-- 
    Document   : admin_page
    Created on : Nov 15, 2017, 2:21:09 PM
    Author     : kirill
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file = "../layouts/header.jsp" %>

<div class="container ">
    <div class="row m-0 mt-2">
        <a class="btn btn-primary ml-auto" href="?page=addpage">Новый пост</a>
    </div>
</div>

<div class="container mt-4"">

    <table class="table table-striped">
        <thead >
            <tr>
                <th scope="col">№ Статьи</th>
                <th scope="col">Заголовок</th>
                <th scope="col">Автор</th>
                <th class="text-center" scope="col">Дата</th>
                <th scope="col" colspan="2">Действие</th>
            </tr>
        </thead>
        
        <tbody>
            <c:forEach var="article" items="${articles}">
            <tr>
                <th scope="row">${article.id}</th>
                <td>${article.caption}</td>
                <td>${article.author.login}</td>
                <td class="text-center">${article.publicdate}</td>
                <td><a href="?page=updatearticleform&id=${article.id}"class="fa fa-pencil-square-o"></a></td>
                <td><a href="?page=dellarticle&id=${article.id}" class="fa fa-close"></a></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>

</div>

<%@include file = "../layouts/footer.jsp" %>
