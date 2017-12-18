<%-- 
    Document   : index
    Created on : Nov 10, 2017, 8:46:47 AM
    Author     : Kirill
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file = "layouts/header.jsp" %>

    <c:forEach var="article" items="${articles}">
        <div class="container">
            <div class="card mt-4" >
                <div class="card-body">
                    <c:if test="${empty user}">
                        <span class="card-title h4 text-info">${article.caption}</span>
                        <small class="text-danger ml-auto">Для просмотра полной статьи зарегистрируйтесь</small>
                    </c:if>
                    <c:if test="${not empty user}">
                        <a class="card-title h4 text-info" href="?page=showOneArticle&id=${article.id}" >${article.caption}</a>
                    </c:if>
                    <div class="card-text tab-article-text index" >${fn:substring(article.content,0,250)} ...</div>

                    <hr class="mt-1">
                    <div class="row">
                    <span class="card-link text-success ml-3">
                        Дата публикации: ${dateFormat.format(article.publicdate)}
                    </span>
                    <span class="card-link text-info ml-auto mr-4">
                        Автор: ${article.author.login}
                    </span>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
<%@include file = "layouts/footer.jsp" %>
