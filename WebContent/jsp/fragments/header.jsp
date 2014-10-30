<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="./libs.jsp"%>

    
<nav class="navbar navbar-inverse navbar-static-top" role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value="/" />">EventManager</a>
		</div>
		<c:choose>
		<c:when test="${empty user}">
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="<c:if test="${not empty requestScope.isLoginPage}">active</c:if>">
						<a href="<c:url value="/Login"/>">Login</a>
					</li>
        			<li class="<c:if test="${not empty requestScope.isSubscribePage}">active</c:if>">
        				<a href="<c:url value="/Subscribe"/>">Subscribe</a>
        			</li>
        		</ul>
        	</div>
		</c:when>
		<c:otherwise>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">Evènements<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="<c:url value="/CreateEvent"/>">Créer</a></li>
							<li class="divider"></li>
							<li><a href="<c:url value="/MyEvents"/>">Mes évenements</a></li>
						</ul>
					</li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">${sessionScope.user.email} <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="<c:url value="/Logout"/>">Déconnexion</a></li>
							<li class="divider"></li>
							<li><a href="<c:url value="/MyAccount"/>">Mon compte</a></li>
						</ul>
					</li>
				</ul>
			</div>	<!-- /.navbar-collapse -->
		</c:otherwise>
		</c:choose>	<!-- /.container-fluid -->
	</div>
</nav>
