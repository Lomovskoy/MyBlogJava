<%-- 
    Document   : user_update_form
    Created on : Nov 29, 2017, 9:04:30 AM
    Author     : pupil
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file = "../layouts/header.jsp"%>


<div class="container mt-4">
    <h5 class="text-center">Добавить статью</h5>
    <div class="border border-secondary rounded p-4 form-group">
        <form action="?page=updateuser&id=${user.id}" method="POST">
            <div class="container mt-4">
                <table class="table table-striped">
                    <thead >
                        <tr>
                            <th class="text-center" scope="col">Id </th>
                            <th class="text-center" scope="col">Активен</th>
                            <th class="text-center" scope="col">Логин</th>
                            <th class="text-center" scope="col">Роль</th>
                            <th class="text-center" scope="col">Изменить</th>
                        </tr>
                    </thead>

                    <tbody class="m-0">
                        <tr>
                            <th>${user.id}</th>
                            <td class="pb-0">
                                <div class="form-group">
                                    <select id="inputState" class="form-control" name="active">
                                        <option selected>true</option>
                                        <option>false</option>
                                    </select>
                                </div>
                            </td>
                            <td class="pb-0">
                                <input type="text" class="form-control" name="login" value="${user.login}">
                            </td>
                            <td class="pb-0">
                                <div class="form-group">
                                    <select id="inputState" class="form-control" name="role">
                                        <option selected value="1">ADMIN</option>
                                        <option value="2">USER</option>
                                    </select>
                                </div>
                            </td>
                            <td class="text-center pb-0 pt-3"><button type="submit" class="btn btn-link fa fa-pencil-square-o"></button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </form>
    </div>

    <%@include file = "../layouts/footer.jsp" %>
