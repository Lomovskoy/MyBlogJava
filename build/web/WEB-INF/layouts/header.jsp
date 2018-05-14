<%-- 
    Document   : header
    Created on : Nov 10, 2017, 9:18:54 AM
    Author     : Kirill
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Необходимые Мета-теги всегда на первом месте -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>My Blog Lomovskoy</title>
        <link rel="shortcut icon" href="template/icon/java.png" type="image/png">
        <!-- Bootstrap CSS -->
        <link href="template/css/bootstrap.min.css" rel="stylesheet">
        <link href="template/css/bootstrap-reboot.min.css" rel="stylesheet">
        <link href="template/css/font-awesome.min.css" rel="stylesheet">
        <link href="template/css/mystyle.css" rel="stylesheet">
        <c:if test="${not empty redirect}">
            <%--<% response.sendRedirect(${redirect}); %>--%>
            <script>
                window.location.href = '${redirect}';
            </script>
        </c:if>
    </head>
    <body>
        <header class="mt-2">
            <div class="container">
                <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
                    <img src="template/icon/photo.png" alt="Photo" class="rounded-circle" style="height: 50px">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarColor02">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item active">
                                <a class="nav-link" href='?page=index'>My Java Blog Lomovskoy</a>
                            </li>
                        </ul>

                        <c:if test="${empty user}">
                            <a class="btn btn-outline-light my-2 my-sm-0" href="?page=regform">Регистрация</a>
                            <a class="btn btn-outline-light my-2 my-sm-0 ml-1" href="?page=adminPanel">Вход</a>
                        </c:if>
                        <c:if test="${not empty user}">
                            <ul class="navbar-nav">
                                <li class="nav-item active">
                                    <a class="nav-link" href='#'>Приветствую: ${user.getLogin()} <img src="imageServlet/${user.image}" alt="Photo" class="rounded-circle" style="height: 30px"></a>

                                </li>
                            </ul>
                            <c:if test='${user.getRole().getRoles().equals("ADMIN")}'>
                                <a class="btn btn-outline-light my-2 my-sm-0" href="?page=login">Кабинет</a>
                                <a class="btn btn-outline-light my-2 my-sm-0 ml-1" href="?page=checkout">Выход</a>
                            </c:if>
                            <c:if test='${user.getRole().getRoles().equals("EDITOR")}'>
                                <a class="btn btn-outline-light my-2 my-sm-0" href="?page=login">Кабинет</a>
                                <a class="btn btn-outline-light my-2 my-sm-0 ml-1" href="?page=checkout">Выход</a>
                            </c:if>
                            <c:if test='${user.getRole().getRoles().equals("USER")}'>
                                <a class="btn btn-outline-light my-2 my-sm-0" href="?page=changeinformationform">Личные данные</a>
                                <a class="btn btn-outline-light my-2 my-sm-0 ml-1" href="?page=checkout">Выход</a>
                            </c:if>       
                        </c:if> 
                    </div>
                </nav>
            </div>
        </header>
        <div class="container">
            <div class="row m-0 mt-1">
                <c:if test="${resultSearch != null}">

                        Найденно результатов: ${resultSearch}

                </c:if> 
                <c:if test="${resultSearch == null}">

                        ...

                </c:if> 
                    <form class="form-inline my-2 my-sm-0 input-group-sm ml-auto" action="?page=search" method="POST">
                        <input class="form-control mr-sm-2 ml-auto" type="search" name="search" placeholder="Поиск" aria-label="Search">
                        <button class="btn btn-primary my-2 my-sm-0 btn-sm ml-auto" type="submit">Искать</button>
                    </form>
            </div>
        </div>
