<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Soumission des demandes</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>
            <header>
                <jsp:include page="navbar.jsp"></jsp:include>
            </header>
            <div class="container col-md-5">
            	<br>
	            <c:if test="${success != null}">
	            	<center> 
		            	<div class="alert alert-success" role="alert">
						  <c:out value='${success}' /> 
						</div>
	            	</center>
	            </c:if>
	            <c:if test="${error != null}">
	            	<center> 
		            	<div class="alert alert-success" role="alert">
						  <c:out value='${error}' /> 
						</div>
	            	</center>
	            </c:if>
	            <br>
                <div class="card">
                    <div class="card-body">
                        <c:if test="${demande == null}">
                            <form action="<%=request.getContextPath()%>/insert_Demand" method="post">
                        </c:if>
                        <caption>
                            <h2>
                                <c:if test="${demande != null}">
                                    Edit Demande
                                </c:if>
                                <c:if test="${demande == null}">
                                   Formuler une nouvelle  Demande
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${demande != null}">
                            <input type="hidden" name="id" value="<c:out value='${demande.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Nom du Postulant</label> <input type="text" value="<c:out value='${demande.nom}' />" class="form-control" name="nom" required="required">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Prenom du Postulant</label> <input type="text" value="<c:out value='${demande.prenom}' />" class="form-control" name="prenom" required="required">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Date de naissance</label> <input type="date" value="<c:out value='${demande.date_naissance}' />" class="form-control" name="date_naissance" required="required">
                        </fieldset>	
                        
                        <fieldset class="form-group">
                            <label>Adresse</label> <input type="text" value="<c:out value='${demande.adresse}' />" class="form-control" name="adresse" required="required">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Telephone</label> <input type="text" value="<c:out value='${demande.telephone}' />" class="form-control" name="telephone" required="required">
                        </fieldset>                      

                        <fieldset class="form-group">
                            <label for="email">Email</label> <input type="email" value="<c:out value='${demande.email}' />" class="form-control" name="email" required="required">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>CNI</label> <input type="text" value="<c:out value='${demande.cni}' />" class="form-control" name="cni" required="required">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Parlez nous de vous</label>
                            <textarea  value="<c:out value='${demande.description}' />" class="form-control" name="description" required="required">
							</textarea>
                            
                        </fieldset>
                        <center> <button type="submit" class="btn btn-success">Soumettre</button> <center>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>
