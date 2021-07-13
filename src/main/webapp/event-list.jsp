<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Evenements</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>
        <body>
            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: green">
                    <div>
                        <a href="<%=request.getContextPath()%>/list_event" class="navbar-brand"> GestA </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list_event" class="nav-link active">Evenements</a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
                <div class="container">
                    <h3 class="text-center">Liste d'Evenements</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/new_event" class="btn btn-success">Ajouter Evenement</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Intitule</th>
                                <th>Description</th>
                                <th>Date Debut</th>
                                <th>Date Fin</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="event" items="${listEvent}">
                                <tr>
                                    <td>
                                        <c:out value="${event.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${event.intitule}" />
                                    </td>
                                    <td>
                                        <c:out value="${event.description}" />
                                    </td>
                                    <td>
                                        <c:out value="${event.date_debut}" />
                                    </td>
                                    <td>
                                        <c:out value="${event.date_fin}" />
                                    </td>
                                    <td><a href="edit_event?id=<c:out value='${event.id}' />">Modifier</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete_event?id=<c:out value='${event.id}' />">Supprimer</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>