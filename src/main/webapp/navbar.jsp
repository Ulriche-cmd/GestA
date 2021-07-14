<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Evenements</title>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
		</head>

        <body>
            <header>
                <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
				  <div class="container-fluid">
					  	<a class="navbar-brand" href="#">
					      <img src="./logo.png alt=" width="30" height="24" class="d-inline-block align-text-top">
					      GestA
					    </a>
					    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDarkDropdown" aria-controls="navbarNavDarkDropdown" aria-expanded="false" aria-label="Toggle navigation">
					      <span class="navbar-toggler-icon"></span>
					    </button>
					    <div class="nav-item dropdown">
						  <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
				            Lien de navigation vers 
				          </a>
						  <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
						    <li><a class="dropdown-item active" href="<%=request.getContextPath()%>/list_demand">Demandes</a></li>
						    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/list-member">Membres</a></li>
						    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/list_event">Evenements</a></li>
						    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/list_cotisations">Cotisations</a></li>
						    <li><hr class="dropdown-divider"></li>
						    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/list-logOut">Déconnexion</a></li>
						  </ul>
						</div>
				  </div>
				</nav>
            </header>
        </body>
     </html>