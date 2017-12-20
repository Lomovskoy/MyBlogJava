<%-- 
    Document   : one_article
    Created on : Nov 22, 2017, 12:57:09 PM
    Author     : pupil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file = "../layouts/header.jsp" %>

<div class="container">
    <div class="card mt-4 mb-2" >
        <div class="card-body">
            <a class="card-title h4 text-info" href="?page=showOneArticle&id=${article.id}" >${article.caption}</a>
            <div class="card-text tab-article-text onepost" >${article.content}</div>
            <div class="row">
                <span class="card-link text-success ml-3">
                    Дата публикации: <%--${dateFormat.format(article.publicdate)}--%>
                    <fmt:formatDate value="${article.publicdate}" pattern="dd.MM.yyyy HH:mm:ss"/>
                </span>
                <span class="card-link text-info ml-auto mr-4">
                    Автор: ${article.author.login}
                </span>
            </div>
        </div>
    </div>
    <hr>
    <div class="ml-auto col-9">
        <form class="mb-2" action="?page=addcomment&articleid=${article.id}" method="POST">
            <div class="form-group">
                <label for="exampleInputEmail1">Комментарий</label>
                <textarea type="text" class="form-control" name="comment">${changeComments.comment}</textarea>
                <small class="form-text text-muted">Здесь вы можете оставить свой комментарий</small>
                <small class="form-text text-info">${info}</small>
            </div>
            <div class="row ">
                <button class="btn btn-primary ml-auto mr-3">Отправить</button>
            </div>
        </form>
    </div>
    <div>
        <c:forEach var="comment" items="${comments}">
            <c:if test="${user.id == comment.author.id}">
                <div class="alert alert-primary col-7 ml-auto <c:if test="${article.author.id == comment.author.id}"> alert-success </c:if> pb-1" role="alert">
                </c:if>
                <c:if test="${user.id != comment.author.id}">
                    <div class="alert alert-primary col-7 mr-auto <c:if test="${article.author.id == comment.author.id}"> alert-success </c:if> pb-1" role="alert">
                    </c:if>  
                    <c:if test="${article.author.id == comment.author.id}"> 
                        <p class="text-center m-0 p-0">Ответ автора</p>
                        <hr class="m-0 p-0">
                    </c:if>
                    <c:if test='${comment.author.getRole().getRoles().equals("ADMIN")}'>
                        <span class="badge badge-pill badge-danger mb-0">admin</span>
                    </c:if>
                    <c:if test='${comment.author.getRole().getRoles().equals("EDITOR")}'>
                        <span class="badge badge-pill badge-primary mb-0">editor</span>
                    </c:if>
                    ${comment.comment}
                    <div class="row">
                        <span class="card-link text-success ml-3">
                            Дата публикации: ${dateFormat.format(comment.publicdate)}
                        </span>

                        <span class="card-link text-info ml-auto mr-4">
                            Автор: ${comment.author.login}
                        </span>
                        <c:if test='${user.getRole().getRoles().equals("ADMIN")}'>
                            <c:if test='${user.id == comment.author.id }'>
                                <a class="fa fa-check m-0 mt-3" href="?page=updatecomment&commentid=${comment.id}&articleid=${article.id}"></a> 
                                <a class="fa fa-times m-0 mt-3" href="?page=dellcomment&commentid=${comment.id}&articleid=${article.id}"></a>
                            </c:if>
                            <c:if test='${comment.author.getRole().getRoles() != ("ADMIN")}'>
                                <a class="fa fa-times m-0 mt-3" href="?page=dellcomment&commentid=${comment.id}&articleid=${article.id}"></a> 
                            </c:if>    
                        </c:if>

                        <c:if test='${user.getRole().getRoles() != ("ADMIN") && user.getRole().getRoles() != ("EDITOR") && user.id == comment.author.id }'>
                            <a class="fa fa-check m-0 mt-3" href="?page=updatecomment&commentid=${comment.id}&articleid=${article.id}"></a>
                            <a class="fa fa-times m-0 mt-3" href="?page=dellcomment&commentid=${comment.id}&articleid=${article.id}"></a>
                        </c:if>

                        <c:if test='${user.getRole().getRoles().equals("EDITOR")}'>
                            <c:if test='${user.id == comment.author.id }'>
                                <a class="fa fa-check m-0 mt-3" href="?page=updatecomment&commentid=${comment.id}&articleid=${article.id}"></a> 
                                <a class="fa fa-times m-0 mt-3" href="?page=dellcomment&commentid=${comment.id}&articleid=${article.id}"></a>
                            </c:if>
                            <c:if test='${comment.author.getRole().getRoles() != ("ADMIN") && comment.author.getRole().getRoles() != ("EDITOR")}'>
                                <a class="fa fa-times m-0 mt-3" href="?page=dellcomment&commentid=${comment.id}&articleid=${article.id}"></a> 
                            </c:if> 
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <%@include file = "../layouts/footer.jsp" %>
