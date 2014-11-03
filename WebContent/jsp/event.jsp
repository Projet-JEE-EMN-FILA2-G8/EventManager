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
	
		<c:choose>
		<c:when test="${eventError}">
			<div class="">
     			<font color=red>
					Une erreur est survenue.
				</font>
    		</div>
		</c:when>
		<c:when test="${hiddenOrUnknownEvent}">
			<div class="">
     			<font color=red>
					Cet évènement n'existe pas ou n'a pas encore été publié.
				</font>
    		</div>
		</c:when>
		<c:otherwise>
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
					<li class="list-group-item"><b>DEBUT</b><br>${event.datedebToString}</li>
					<li class="list-group-item"><b>FIN</b><br>${event.datefinToString}</li>
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
							<i>Vous pouvez recommencer en entrant des paramètres valides.</i>
						</font>
					</c:if>
			
					<div class="panel-body">
						<p>Inscrivez-vous à cet évènement !</p>
					</div>
					
					<form class="form-horizontal" role="form" action="" method="post">
						<div class="form-group">
							<label for="nom" class="col-sm-1 control-label">Nom:</label>
					    	<div class="col-sm-3">
					      		<input type="text" class="form-control" id="nom" name="nom" maxlength="24" required="required" placeholder="Nom">
					    	</div>
						</div>
						<div class="form-group">
							<label for="prenom" class="col-sm-1 control-label">Prénom:</label>
					    	<div class="col-sm-3">
					      		<input type="text" class="form-control" id="prenom" name="prenom" maxlength="24" placeholder="Prénom">
					    	</div>
						</div>
						<div class="form-group">
							<label for="email" class="col-sm-1 control-label">Email:</label>
					    	<div class="col-sm-3">
					      		<input type="email" class="form-control" id="email" name="email" value="${user.email}" maxlength="30" required="required"  placeholder="Email">
					    	</div>
						</div>
						<div class="form-group">
							<label for="societe" class="col-sm-1 control-label">Société:</label>
					    	<div class="col-sm-3">
					      		<input type="text" class="form-control" id="societe" name="societe" maxlength="24" placeholder="Société">
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
			<c:if test="${not empty requestScope.participants}">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Liste des participants</h3>
					</div>
			
					<div class="panel-body">
						<h4>${fn:length(requestScope.participants)} participant(s).</h4>
					</div>
			
					<c:if test="${fn:length(requestScope.participants) > 0}">
						<!-- List group -->				
						<ul class="list-group">
							<li class="list-group-item">
								<table class="table">
									<thead>
								        <tr>
								        	<th>Nom</th>
									    	<th>Prénom</th>
									        <th>Société</th>
									        <th>Email</th>
							     	 </thead>
									<tbody>
										<c:forEach var="participant" items="${requestScope.participants}">
											<tr>
												<td>${participant.nom}</td>
												<td>${participant.prenom}</td>
												<td>${participant.societe}</td>
												<td>${participant.email}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</li>
						</ul>
					</c:if>
				</div>
				<!-- /Panel -->
			</c:if>
		</c:otherwise>
		</c:choose>
	</div>
	<!-- /container -->
</body>
</html>