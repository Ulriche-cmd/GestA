<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>gestA - listCotisation </title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>
			<header>
                <jsp:include page="navbar.jsp"></jsp:include>
            </header>
            <br>
            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">Listes des Cotisations</h3>
                    <hr>
                    <div class="text-left">
                    <br>
                        <a href="<%=request.getContextPath()%>/new_cotisation" class="btn btn-success"> Enregistrer une nouvelle cotisation </a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Montant</th>
                                <th>Date</th>
                                <th>Evenement</th>
                                <th>Membre</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="cotisation" items="${listCotisation}">

                                <tr>
                                    <td>
                                        <c:out value="${cotisation.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${cotisation.montant}" />
                                    </td>
                                    <td>
                                        <c:out value="${cotisation.date_cotisation}" />
                                    </td>
                                    <td>
                                        <c:out value="${cotisation.id_evenement}" />
                                    </td>
                                    <td>
                                        <c:out value="${cotisation.id_membre}" />
                                    </td>
                                 </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>