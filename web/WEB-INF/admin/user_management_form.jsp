<%-- 
    Document   : user_management_form
    Created on : Nov 27, 2017, 9:44:11 AM
    Author     : pupil
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file = "../layouts/header.jsp" %>
<c:if test='${user.getRole().getRoles().equals("ADMIN")}'>
<div class="container mt-4">
    <h5 class="text-center">Для блокировки администратора, переведите его в разряд пользователей</h5>
    <hr>
    <table class="table table-striped">
        <thead >
            <tr>
                <th scope="col">Id </th>
                <th scope="col">Активен</th>
                <th scope="col">Логин</th>
                <th scope="col">Аватар</th>
                <th class="text-center" scope="col">Роль</th>
                <th scope="col" colspan="2">Действие</th>
            </tr>
        </thead>
        
        <tbody>
            <c:forEach var="user" items="${users}">
            <tr>
                <th scope="row">${user.id}</th>
                <td>${user.active}</td>
                <td>${user.login}</td>
                <td><img src="imageServlet/${user.image}" alt="Photo" class="rounded-circle" style="height: 25px"></td>
                <td class="text-center">${user.role.roles}</td>
                <td><a href="?page=updateuser&id=${user.id}"class="fa fa-pencil-square-o"></a></td>
                <td><a href="?page=delluser&id=${user.id}" class="fa fa-close"></a></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</c:if>
<c:if test='${user.getRole().getRoles() != "ADMIN"}'>
    <h5 class="text-center">Вам здесь не рады</h5>
</c:if>
<%@include file = "../layouts/footer.jsp" %>