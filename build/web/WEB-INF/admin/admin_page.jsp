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
</c:if>

<%@include file = "../layouts/footer.jsp" %>
