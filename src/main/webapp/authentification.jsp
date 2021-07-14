<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <head>
            <title>Connexion admin</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        	<style>
        	
		    </style>
        </head>

        <body>
        	<header>
                <jsp:include page="navbar.jsp"></jsp:include>
            </header>
			<br><br><br>
			<div class="container">
				<div class="row">
					<div class="col-sm-6">
					    <img src="image/img1.png" />
					</div>
	<%-- 				<div class="column"><img src="<c:url value=""/>"/></div> --%>
		            <div class="col-md-5">
		                <div class="align-center card">
		                    <div class="card-body">
		                        <form action="<%=request.getContextPath()%>/connexion"  method="post">
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