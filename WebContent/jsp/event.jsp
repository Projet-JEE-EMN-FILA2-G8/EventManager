<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Event</title>

<link href="<c:url value="../css/bootstrap.css"/>" rel="stylesheet">


</head>
<body>
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
			<a class="navbar-brand" href="#">EvntMgr</a>
		</div>

		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">Evenement <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">Créer</a></li>
						<li class="divider"></li>
						<li><a href="#">Mes évenements</a></li>
					</ul>
				</li>
			</ul>

			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Rechercher un evenement">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>

			<ul class="nav navbar-nav navbar-right">
				<li><p class="navbar-text navbar-right">
						Connecté en tant que <a href="#" class="navbar-link">lgu</a>
					</p>
				</li>
				&nbsp;
				<li><button type="button" class="btn btn-default navbar-btn navbar-right">Déconnexion</button></li>
				&nbsp;
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	</nav>

	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">Evenement</h3>
		</div>

		<div class="panel-body">
			<p>Description</p>
		</div>

		<!-- List group -->
		<ul class="list-group">
			<li class="list-group-item">Adresse</li>
			<li class="list-group-item">Date début</li>
			<li class="list-group-item">Date fin</li>
			<li class="list-group-item">Autre</li>
			<li class="list-group-item">S'inscrire
				<button>inscription</button>
			</li>
		</ul>
	</div>
	<!-- /Panel -->

</body>
</html>