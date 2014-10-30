<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="./fragments/libs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="./fragments/head.jsp"%>
<title>Event</title>
</head>
<body>
	
	<%@ include file="./fragments/header.jsp"%>
	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">${event.nom}</h3>
			</div>
	
			<div class="panel-body">
				<p>${event.description}</p>
			</div>
	
			<!-- List group -->
			<ul class="list-group">
				<li class="list-group-item"><b>ADRESSE</b><br>${event.adresse}</li>
				<li class="list-group-item"><b>DEBUT</b><br>${event.datedeb}</li>
				<li class="list-group-item"><b>FIN</b><br>${event.datefin}</li>
				<li class="list-group-item">
					<b>LIEN</b><br> 
					<a href="${eventurl}" id="link${event.id}">${eventurl}</a> <!-- | <button class="btn btn-primary" onclick="copy('link${even.id}');">Copier</button> -->
				</li>
			</ul>
		</div>
		<!-- /Panel -->
		
		<div class="panel panel-default">
	
			<div class="panel-heading">
				<h3 class="panel-title">S'incrire à l'évènement</h3>
			</div>
			
			<c:choose>
			<c:when test="${registered}">
				<div class="panel-body">
					<h4><i>Votre participation a bien été enregistrée.</i></h4>
				</div>
			</c:when>
			<c:when test="${alreadyRegistered}">
				<div class="panel-body">
					<h4><i>Vous êtes déjà inscrit à cet évènement !</i></h4>
				</div>
			</c:when>
			<c:when test="${participateToEvent[eventId]}">
			 	<div class="panel-body">
					<h4><i>Vous participez à cet évènement.</i></h4>
				</div>
			</c:when>
			<c:otherwise>
				<c:if test="${errorOccured}">
					<font color=red>
						Une erreur est survenue lors de l'inscription.<br>
						<i>Vous-pouvez recommencer en entrant des paramètres valides.</i>
					</font>
				</c:if>
		
				<div class="panel-body">
					<p>Inscrivez-vous à cet évènement !</p>
				</div>
				
				<form class="form-horizontal" role="form" action="" method="post">
					<div class="form-group">
						<label for="nom" class="col-sm-1 control-label">Nom:</label>
				    	<div class="col-sm-3">
				      		<input type="text" class="form-control" id="nom" name="nom" placeholder="Nom">
				    	</div>
					</div>
					<div class="form-group">
						<label for="prenom" class="col-sm-1 control-label">Prénom:</label>
				    	<div class="col-sm-3">
				      		<input type="text" class="form-control" id="prenom" name="prenom" placeholder="Prénom">
				    	</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-sm-1 control-label">Email:</label>
				    	<div class="col-sm-3">
				      		<input type="email" class="form-control" id="email" name="email" value="${user.email}" placeholder="Email">
				    	</div>
					</div>
					<div class="form-group">
						<label for="societe" class="col-sm-1 control-label">Société:</label>
				    	<div class="col-sm-3">
				      		<input type="text" class="form-control" id="societe" name="societe" placeholder="Société">
				    	</div>
					</div>
					<div class="form-group">
				    	<div class="col-sm-offset-1 col-sm-3">
				      		<button type="submit" class="btn btn-primary">Participer</button>
				    	</div>
					</div>
				</form>
			</c:otherwise>
			</c:choose>
		</div>	
		<!-- /Panel -->
	</div>
	<!-- /container -->
</body>
</html>