<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>gestA - listMembre</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>
        <style>
        	.contain{
        		padding:100px 100px;
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
                    <div class="container">
	                    <h3 class="text-center">Liste des Membres</h3>
	                    <hr>
	                    <br>
	                    <div class="text-left">
	                        <a href="<%=request.getContextPath()%>/add-member" class="btn btn-success">Ajouter un Membre</a>
	                    </div>
	                    <br>
	                    <table class="table table-bordered">
	                        <thead>
	                            <tr>
	                                <th>Nom</th>
	                                <th>Prenom</th>
	                                <th>Date_naissance</th>
	                                <th>Adresse</th>
	                                <th>Telephone</th>
	                                <th>Email</th>
	                                <th>CNI</th>                                
	                                <th>Role</th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                            <!--   for (Todo todo: todos) {  -->
	                            <c:forEach var="member" items="${listMember}">
		                                <tr>
		                                    <td>
		                                        <c:out value="${member.nom}" />
		                                    </td>
		                                    <td>
		                                        <c:out value="${member.prenom}" />
		                                    </td>
		                                    <td>
		                                        <c:out value="${member.date_naissance}" />
		                                    </td>
		                                    <td>
		                                        <c:out value="${member.adresse}" />
		                                    </td>
		                                    <td>
		                                        <c:out value="${member.telephone}" />
		                                    </td>
		                                    <td>
		                                        <c:out value="${member.email}" />
		                                    </td>
		                                    <td>
		                                        <c:out value="${member.cni}" />
		                                    </td>
		                                    <td>
		                                        <c:out value="${member.role}" />
		                                    </td>
		                                </tr>
	                            </c:forEach>
	                            <!-- } -->
	                        </tbody>
	
	                    </table>
	            	</div>
                </div>
            </div>
        </body>

        </html>