<%-- 
    Document   : one_article
    Created on : Nov 22, 2017, 12:57:09 PM
    Author     : pupil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file = "layouts/header.jsp" %>

<div class="container">
    <div class="card mt-4" >
        <div class="card-body">
            <a class="card-title h4 text-info" href="?page=showOneArticle&id=${article.id}" >${article.caption}</a>
            <p class="card-text text-truncate tab-article-text" >${article.content}</p>
            <div class="row">
                <span class="card-link text-success ml-3">
                    Дата публикации: ${article.publicdate}
                </span>
                <span class="card-link text-info ml-auto mr-4">
                    Автор: ${article.author.login}
                </span>
            </div>
        </div>
    </div>
</div>
                    
<%@include file = "layouts/footer.jsp" %>
