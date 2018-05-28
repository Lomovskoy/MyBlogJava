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
    <c:if test="${article.getActive() == true}">
        <div class="container">
            <div class="card mt-4" >
                <div class="card-body">
                    <a class="card-title h4 text-info" href="?page=showOneArticle&id=${article.id}" >${article.caption}</a>
                    <div class="card-text tab-article-text index" >${fn:substring(article.content,0,350)} ...</div>
                    <hr class="mt-1">
                    <div class="row">
                        <button type="button" 
                                id="like" 
                                class="btn btn-outline-primary btn-sm fa fa-heart-o like-button-art ml-3" 
                                article="${article.id}" 
                                postavlen-art="${not empty user ? (article.LikedByUser(user) ? "1" : "0") : "2"}" 
                                count="${article.LikesCount()}">
                        </button>
                        <span class="card-link text-success ml-3" style="border: #0069d9">
                            Дата публикации: ${dateFormat.format(article.publicdate)}
                        </span>
                        <span class="card-link text-info ml-auto mr-4">
                            Автор: ${article.author.login}  <img src="imageServlet/${article.author.image}" alt="Photo" class="rounded-circle" style="height: 30px">
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</c:forEach>
<div class="container">
    <nav class="mt-3 mx-auto">
        <ul class="pagination">
            <c:if test="${pages != null}">
                <li class="page-item<c:if test="${curpage - 1 < 1}"> disabled</c:if>"><a class="page-link" href="?page=index&pagination=${curpage - 1}"><<</a></li>
                    <c:forEach var="page" items="${pages}">
                    <li class="page-item<c:if test="${page == curpage}"> active</c:if>"><a class="page-link" href="?page=index&pagination=${page}">${page}</a></li>
                    </c:forEach>
                <li class="page-item<c:if test="${curpage == pagecount}"> disabled</c:if>"><a class="page-link" href="?page=index&pagination=${curpage+1}">>></a></li>
            </c:if>
        </ul>
    </nav>
</div>
<%@include file = "layouts/footer.jsp" %>
