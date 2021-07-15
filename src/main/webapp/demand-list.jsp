<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>gestA - listDemande</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>
        <style>
        	.conta{
        		width:80%;
        		margin-left:auto;
        		margin-right:auto;
        	}
        </style>
        <body>
            <header>
                <jsp:include page="navbar.jsp"></jsp:include>
            </header>
            <br>
            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
	                <div class="container">
	                	<h3 class="text-center">Liste des demandes</h3>
		                    <hr>
		                    <br>
	                </div>
                    <div class="conta">
	                    <div class="text-left">
	                        <a href="<%=request.getContextPath()%>/add-member" class="btn btn-success">Ajouter un Membre</a>
	                    </div>
	                    <br>
	                    <table class="tab table table-bordered">
	                        <thead>
	                            <tr>
	                                <th>Nom</th>
	                                <th>Prenom</th>
	                                <th>Date_naissance</th>
	                                <th>Adresse</th>
	                                <th>Telephone</th>
	                                <th>Email</th>
	                                <th>CNI</th>                                
	                                <th style="width:400px;">Description</th>                                
	                                <th>Date_demande</th>                                
	                                <th>Actions</th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                            <!--   for (Todo todo: todos) {  -->
	                            <c:forEach var="demand" items="${demandes}">
	                            	<c:if test="${ demand.etat == 0 }">
		                                <tr>
		                                    <td>
		                                        <c:out value="${demand.nom}" />
		                                    </td>
		                                    <td>
		                                        <c:out value="${demand.prenom}" />
		                                    </td>
		                                    <td>
		                                        <c:out value="${demand.date_naissance}" />
		                                    </td>
		                                    <td>
		                                        <c:out value="${demand.adresse}" />
		                                    </td>
		                                    <td>
		                                        <c:out value="${demand.telephone}" />
		                                    </td>
		                                    <td>
		                                        <c:out value="${demand.email}" />
		                                    </td>
		                                    <td>
		                                        <c:out value="${demand.cni}" />
		                                    </td>
		                                    <td>
		                                        <c:out value="${demand.description}" />
		                                    </td>
		                                    <td>
		                                        <c:out value="${demand.date_demande}" />
		                                    </td>
		                                    <td>
		                                    	<a class="text-danger" href="rejet_demand?id=<c:out value='${demand.id}' />">Rejeter</a> &nbsp;&nbsp;&nbsp;&nbsp; 
		                                    	<a class="text-success" href="accept_demand?id=<c:out value='${demand.id}' />">Accepter</a>
		                                    </td>
		                                </tr>
		                        	</c:if>
	                            </c:forEach>
	                            <!-- } -->
	                        </tbody>
	
	                    </table>
	            	</div>
                </div>
        </body>

        </html>