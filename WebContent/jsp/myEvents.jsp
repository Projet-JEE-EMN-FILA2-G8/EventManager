<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="./fragments/libs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Mes évènements</title>
<%@ include file="./fragments/head.jsp"%>
</head>
<body>
	<%@ include file="./fragments/header.jsp"%>
	
	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Mes évènemments</h3>
			</div>
			
			<div class="panel-body">
			
				<c:if test="${empty requestScope.myEvents}">
					<h4><i>Vous n'avez aucun évènement.</i></h4>
					<p><a href="<c:url value="/CreateEvent"/>">Créer un évènement ?</a></p>
				</c:if>
			
				<p>${event.description}</p>
			</div>
			<c:if test="${not empty requestScope.myEvents}">
				<!-- List group -->				
				<ul class="list-group">
					<li class="list-group-item">
						<table class="table">
							<thead>
						        <tr>
						        	<th>Nom</th>
							    	<th>Adresse</th>
							        <th>Début</th>
							        <th>Fin</th>
							        <th>Actions</th>
						        </tr>
					     	 </thead>
							<tbody>
								<c:forEach var="event" items="${myEvents}">
									<tr>
										<td>${event.nom}</td>
										<td>${event.adresse}</td>
										<td>${event.datedeb}</td>
										<td>${event.datefin}</td>
										<td>
											<form action="?action=publish&eventId=${event.id}" method="post">
												<a href="<c:url value="/Event/${event.id}"/>" class="btn btn-default">Voir</a>
												<a href="<c:url value="/EditEvent/${event.id}"/>" class="btn btn-default">Editer</a>
												<c:if test="${not event.visible}">
													<button class="btn btn-primary" type="submit">Publier</button>
												</c:if>
												<c:if test="${event.visible}">
													<a href="<c:url value="/Event/${event.id}"/>"><i>Publié</i></a>
												</c:if>
											</form>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</li>
				</ul>
			</c:if>
		</div>
		<!-- /Panel -->
	</div>
</body>
</html>