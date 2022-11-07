<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <title>User Dashboard</title>
    </head>
    <body>
        <div class="container">
            <div class="col">
                <h1>Manage User</h1>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Email</th>
                                <th>Active</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Role</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                            <c:forEach var="user" items="${users}">
                                <tr>
                                    <th><c:out value="${user.getEmail()}"/></th>
                                    <th>
                                        <c:choose>
                                            <c:when test="${user.getActive() == 1}">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" value="1" checked disabled>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" value="1" disabled>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </th>
                                    <th><c:out value="${user.getFirstName()}"/></th>
                                    <th><c:out value="${user.getLastName()}"/></th>
                                    <th><c:out value="${roleService.get(user.getRole())}"/></th>
                                    <th><a href="user?action=edit&user=${user.getEmail()}"><button type="button" class="btn btn-success">Edit</button></a></th>
                                    <th><a href="user?action=delete&user=${user.getEmail()}"><button type="button" class="btn btn-danger">Delete</button></a></th>
                                </tr>
                            </c:forEach>
                        </thead>
                    </table>
                    <a href="user?action=add"><button class="btn btn-primary">Add new user</button></a>
            </div>
            <c:if test="${editEnable != null}">
                <div class="col">
                    <h1>Edit user</h1>
                    <form action="user" method="post">
                        <input type="hidden" name="action" value="update">
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input readonly="readonly" type="text" name="email" class="form-control" id="email" value="${editUser.getEmail()}">
                        </div>
                        <div class="mb-3">
                            <label for="firstName" class="form-label">First name</label>
                            <input type="text" name="firstName" class="form-control" id="firstName" value="${editUser.getFirstName()}">
                        </div>
                        <div class="mb-3">
                            <label for="lastName" class="form-label">Last name</label>
                            <input type="text" name="lastName" class="form-control" id="lastName" value="${editUser.getLastName()}">
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="text" name="password" class="form-control" id="password" value="${editUser.getPassword()}">
                        </div>
                        <div class="mb-3">
                            <label for="role"></label>
                            <select name="role" id="role" class="form-select">
                                <option selected>Current: ${roleService.get(editUser.getRole())}</option>
                                <c:forEach var="role" items="${roles}">
                                    <option>${role.getName()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="active" class="form-check">Active</label>
                            <c:choose>
                                <c:when test="${editUser.getActive() == 1}">
                                    <div class="form-check">
                                        <input id="active" name="active" class="form-check-input" type="checkbox" value="1" checked>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="form-check">
                                        <input id="active" name="active" class="form-check-input" type="checkbox" value="1">
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <button type="submit" class="btn btn-secondary">Save</button>       
                        <a href="user?action=cancel" class="btn btn-primary bg-light text-dark">Cancel</a>
                    </form>
                </div>
            </c:if>
        </div>
        <c:if test="${addEnable != null}">
            <div class="container">
                <h1>Add new User</h1>
                <form action="user" method="POST">
                    <input type="hidden" name="action" value="addUser">
                    <div class="form-group col-md">
                        <label for="inputEmail">Email</label>
                        <input required= type="text" name="inputEmail" class="form-control" id="inputEmail" placeholder="Email">
                    </div>
                    <div class="form-group col-md">
                        <label for="inputPassword">Password</label>
                        <input required min=6 max=24 type="text" name="inputPassword" class="form-control" id="inputPassword" placeholder="Password">
                    </div>
                    <div class="form-group col-md">
                        <label for="inputFirstName">First Name</label>
                        <input required min=2 max=24 type="text" name="inputFirstName" class="form-control" id="inputFirstName" placeholder="First Name">
                    </div>
                    <div class="form-group col-md">
                        <label for="inputLastName">Last Name</label>
                        <input required min=2 max=24 type="text" name="inputLastName" class="form-control" id="inputLastName" placeholder="Last Name">
                    </div>
                    <div class="form-group col-md">
                        <label for="inputRole">Role</label>
                        <select id="inputRole" name="inputRole" class="form-control">
                            <option selected>Select a role...</option>
                            <c:forEach var="role" items="${roles}">
                                <option>${role.getName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-md">
                        <label for="inputActive">Active</label>
                        <input id="inputActive" name="inputActive" class="form-check-input" type="checkbox" value="1" checked>
                    </div>
                    <button type="submit" class="btn btn-primary">Save</button> 
                </form>
            </div>
        </c:if>


    </body>
</html>
