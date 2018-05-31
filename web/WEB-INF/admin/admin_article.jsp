<%-- 
    Document   : admin_article
    Created on : Jan 15, 2018, 10:02:04 AM
    Author     : pupil
--%>

<%-- 
    Document   : admin_page
    Created on : Nov 15, 2017, 2:21:09 PM
    Author     : kirill
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file = "../layouts/header.jsp" %>
<c:if test='${user.getRole().getRoles() != "ADMIN" && user.getRole().getRoles() != "EDITOR" }'>
    <h5 class="text-center">Вам здесь не рады</h5>
</c:if>

<c:if test='${user.getRole().getRoles().equals("ADMIN") || user.getRole().getRoles().equals("EDITOR") }'>
    <div class="container ">
        <div class="row m-0 mt-2">
            <c:if test='${user.getRole().getRoles().equals("ADMIN")}'>
                <a class="btn btn-primary mr-4" href="?page=usermanagement">Управление пользователями</a> 
            </c:if>
            <a class="btn btn-primary mr-4" href="?page=addfile">Добавить файл</a>
            <a class="btn btn-primary mr-4" href="?page=addpage">Новый пост</a>
            <a class="btn btn-primary mr-4" href="?page=viewarticles">Статьи</a>
            <a class="btn btn-primary mr-4" href="?page=changeinformationform">Личные данные</a>
        </div>
    </div>
    <h4 class="text-center mt-4">${info}</h4>
    <div class="container mt-4">

        <table class="table table-striped">
            <thead >
                <tr>
                    <th scope="col">№ Статьи</th>
                    <th scope="col">Активна</th>
                    <th scope="col">Заголовок</th>
                    <th scope="col">Автор</th>
                    <th class="text-center" scope="col">Дата</th>
                    <th scope="col" colspan="2">Действие</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="article" items="${articles}">
                    <tr>
                        <c:if test='${user.getRole().getRoles().equals("ADMIN")}'>
                            <th scope="row">${article.id}</th>
                            <td>${article.active}</td>
                            <td>${article.caption}</td>
                            <td>${article.author.login}</td>
                            <td class="text-center">${article.publicdate}</td>
                            <td><a href="?page=updatearticleform&id=${article.id}"class="fa fa-pencil-square-o"></a></td>
                            <td><a href="?page=dellarticle&id=${article.id}" class="fa fa-close"></a></td>
                        </c:if>
                        <c:if test='${user.getRole().getRoles().equals("EDITOR")}'>
                            <c:if test='${user.getRole().getRoles().equals(article.author.getRole().getRoles())}'>
                                <th scope="row">${article.id}</th>
                                <td>${article.active}</td>
                                <td>${article.caption}</td>
                                <td>${article.author.login}</td>
                                <td class="text-center">${article.publicdate}</td>
                                <td><a href="?page=updatearticleform&id=${article.id}"class="fa fa-pencil-square-o"></a></td>
                                <td><a href="?page=dellarticle&id=${article.id}" class="fa fa-close"></a></td>
                            </c:if>
                        </c:if> 
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
    <c:if test='${!user.getRole().getRoles().equals("ADMIN") || !user.getRole().getRoles().equals("EDITOR")}'>
    <h5 class="text-center">Вам здесь не рады</h5>
</c:if>
<%@include file = "../layouts/footer.jsp" %>
