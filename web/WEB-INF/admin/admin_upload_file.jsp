<%-- 
    Document   : admin_upload_file
    Created on : Dec 12, 2017, 10:22:49 AM
    Author     : pupil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file = "../layouts/header.jsp" %>
<c:if test='${user.getRole().getRoles().equals("ADMIN") || user.getRole().getRoles().equals("EDITOR")}'>
<div class="container">
    <form class="col-4 mr-auto mt-4 p-4 border border-secondary rounded container" action="upload" method="POST" enctype="multipart/form-data">
        <div class="form-group">
            <label for="exampleFormControlFile1">выберете файл</label>
            <small id="emailHelp" class="form-text text-muted">Картинка должна быть jpg.</small>
            <input type="file" class="form-control-file" name="file">
        </div>
        <div class="row m-0 mt-2">
            <button type="submit" class="btn btn-primary ml-auto">Загрузить</button>
        </div>
    </form>
    <hr>
    <div class="container">
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">№</th>
                    <th scope="col">image</th>
                    <th scope="col">Name</th>
                    <th scope="col">size</th>
                    <th scope="col">dell</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="image" items="${images}" varStatus="status">
                <tr>
                    <th scope="row">${status.index+1}</th>
                    <td><img class="img-fluid " style="width: 55px"src="fileServlet/${image.getName()}"></td>
                    <td>${image.getName()}</td>
                    <td><fmt:formatNumber type = "number" value="${image.length()/1024}" pattern = "#####.###"/> kb</td>
                    <td><a class="fa fa-times" href="?page=dellImage&name=${image.getName()}" ></a></td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</div>
</c:if>  
<c:if test='${!user.getRole().getRoles().equals("ADMIN") || !user.getRole().getRoles().equals("EDITOR")}'>
    <h5 class="text-center">Вам здесь не рады</h5>
</c:if>

<%@include file = "../layouts/footer.jsp" %>

