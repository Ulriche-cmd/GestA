<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Enregister une cotisation</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>
			<header>
                <jsp:include page="navbar.jsp"></jsp:include>
            </header>
            <br><br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
<%--                         <c:if test="${demande != null}"> --%>
<!--                             <form action="update" method="post"> -->
<%--                         </c:if> --%>
<%--                         <c:if test="${demande == null}"> --%>
<!--                             <form action="insert" method="post"> -->
<%--                         </c:if> --%>

						<form action="<%=request.getContextPath()%>/insert_cotisation" method="post">

	                        <caption>
	                            <h2>
	                                <c:if test="${demande != null}">
	                                    Edit Demande
	                                </c:if>
	                                <c:if test="${demande == null}">
	                                   Enregistrer une cotisation
	                                </c:if>
	                            </h2>
	                        </caption>
	
	                        <c:if test="${demande != null}">
	                            <input type="hidden" name="id" value="<c:out value='${demande.id}' />" />
	                        </c:if>
	                        
	                        <fieldset class="form-group">
	                        	<label>Membre</label>
		                        <select class="form-control" name="membre" required="required" aria-label="Disabled select example">
		                        	<option selected>Selectionnez le membre</option>
		                        	<c:forEach var="membre" items="${listMember}">
								  		<option value="${membre.id}">${membre.prenom} ${membre.nom}</option>
								  	</c:forEach>
								</select>
							</fieldset>
	                        
	                        <fieldset class="form-group">
	                            <label>Montant cotisation</label> <input type="text" value="<c:out value='${demande.montant}' />" class="form-control" name="montant" required="required">
	                        </fieldset>
	                        
	                         <fieldset class="form-group">
	                         	<label>Evenement</label>
		                        <select class="form-control" name="evenement" required="required" aria-label="Disabled select example">
		                        	<option selected>Selectionnez l'evenement en question</option>
		                        	<c:forEach var="event" items="${listEvent}">
								  		<option value="${event.id}">${event.intitule} - ${event.date_debut}</option>
								  	</c:forEach>
								</select>
							</fieldset>
	                        
	                        <fieldset class="form-group">
	                            <label>Date cotisation</label> <input type="date" value="<c:out value='${demande.date}' />" class="form-control" name="date" required="required">
	                        </fieldset>
	                        <button type="submit" class="btn btn-success">Enregistrer</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>