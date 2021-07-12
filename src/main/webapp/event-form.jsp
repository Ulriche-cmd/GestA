<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Ajouter Evenement</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: green">
                    <div>
                        <a href="<%=request.getContextPath()%>/list_event"  class="navbar-brand"> GestA </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list_event" class="nav-link active">Evenements</a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${event != null}">
                            <form action="update_event" method="post">
                        </c:if>
                        <c:if test="${event == null}">
                            <form action="insert_event" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${event != null}">
                                    Modifier Evenement
                                </c:if>
                                <c:if test="${event == null}">
                                    Ajouter Evenement
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${event != null}">
                            <input type="hidden" name="id" value="<c:out value='${event.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Intitule</label> <input type="text" value="<c:out value='${event.intitule}' />" class="form-control" name="intitule" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Description</label> <input type="text" value="<c:out value='${event.description}' />" class="form-control" name="description" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Date Debut</label> <input type="date" value="<c:out value='${event.date_debut}' />" class="form-control" name="date_debut" required="required">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Date Fin</label> <input type="date" value="<c:out value='${event.date_fin}' />" class="form-control" name="date_fin" required="required">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Enregistrer</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>