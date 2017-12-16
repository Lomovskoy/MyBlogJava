<%-- 
    Document   : user_management_form
    Created on : Nov 27, 2017, 9:44:11 AM
    Author     : pupil
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file = "../layouts/header.jsp" %>

<div class="container mt-4">

    <table class="table table-striped">
        <thead >
            <tr>
                <th scope="col">Id </th>
                <th scope="col">Активен</th>
                <th scope="col">Логин</th>
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
                <td class="text-center">${user.role.roles}</td>
                <td><a href="?page=updateuser&id=${user.id}"class="fa fa-pencil-square-o"></a></td>
                <td><a href="?page=delluser&id=${user.id}" class="fa fa-close"></a></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>

</div>



<%@include file = "../layouts/footer.jsp" %>