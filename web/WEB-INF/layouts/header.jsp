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
                        <c:if test="${empty admin}">
                            <a class="btn btn-outline-light my-2 my-sm-0" href="?page=adminPanel">Вход</a>
                        </c:if>
                        <c:if test="${not empty admin}">
                            <a class="btn btn-outline-light my-2 my-sm-0" href="?page=checkout">Выход</a>
                        </c:if> 
                    </div>
                </nav>
            </div>
        </header>