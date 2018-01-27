<%-- 
    Document   : user_update_comment
    Created on : 26.01.2018, 22:50:50
    Author     : imxo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file = "../layouts/header.jsp" %>


<div class="container col-6">
    <form class="mb-2" action="?page=updatecomment&articleid=${articleId}&commentid=${changeComments.getId()}" method="POST">
        <div class="form-group">
            <label for="exampleInputEmail1">Ваш Комментарий</label>
            <textarea type="text" class="form-control" name="comment">${changeComments.getComment()}</textarea>
            <small class="form-text text-muted">Вы можете изменить свой комментарий</small>
            <small class="form-text text-info">${info}</small>
        </div>
        <div class="row ">
            <button class="btn btn-primary ml-auto mr-3">Изменить</button>
        </div>
    </form>
</div>


<%@include file = "../layouts/footer.jsp" %>