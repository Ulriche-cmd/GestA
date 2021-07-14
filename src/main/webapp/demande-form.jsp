<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
        	
            <title>Connexion admin</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        	<style>
		      body{
		      background-color: #DADBDC;
		      font-family: sans-serif;;
		      }
		     
		      
		    </style>
        </head>

        <body>
			<br><br><br>
			<div class="row">
			
			<div class="column"><img src="<c:url value="/image/img1.PNG"/>"/></div>
            <div class="column col-md-4">
                <div class="card">
                    <div class="card-body">
                        <form action="<%=request.getContextPath()%>/"  method="post">
                            
                            <h2> Connectez-vous</h2>
                            
	                        <fieldset class="form-group">
	                            <label>LOGIN:</label> <input type="text" class="form-control" name="login" required="required">
	                        </fieldset>
	                        
	                        <fieldset class="form-group">
	                            <label>PASSWORD:</label> <input type="password"  class="form-control" name="mdp" required="required">
	                        </fieldset>
	                        
	                        <center><button type="submit" value="submit" class="btn btn-success">SE CONNECTER</button></center>
            
	                       	<c:if test="${error!= null}">
	                       	<br><br>
	                        <h5><c:out value="${error}"/></h5>
	                       	</c:if>
	                        <br>
                        </form>
                        <form action="">
                        <h6>vous etes nouveau?</h6>
                        <h6>veuillez vous incrire en cliquant sur ce bouton</h6>
                        <center><button type="submit" class="btn btn-success">INSCRIVEZ VOUS</button></center>
                        </form>
                        
                    </div>
                </div>
            </div>
            </div>
        </body>

        </html>
