<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="./fragments/libs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="./fragments/head.jsp"%>
<link href="<c:url value="/css/bootstrap-datetimepicker.min.css"/>" rel="stylesheet" media="screen">
<script type="text/javascript" src="<c:url value="/js/bootstrap-datetimepicker.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/locales/bootstrap-datetimepicker.fr.js"/>"></script>

<script type="text/javascript">
  $(document).ready(function() {
	  $('.datetime').datetimepicker({
		  todayBtn:"true",
		  format:"dd M. yyyy hh:ii", 
		  autoclose:"true",
		  pickerPosition:"bottom-left",
		  startView:"year",
		  minView:"hour",
		  language:"fr"
	  });
  });
</script>

<title>Event</title>
</head>
<body>
	<%@ include file="./fragments/header.jsp"%>
	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">		     	
		     		<c:if test="${not empty event.id}">Modifier un évènement</c:if>
		     		<c:if test="${empty event.id}">Créer un évènement</c:if>
				</h3>
			</div>
			<div class="panel-body">
				<p>
					<c:if test="${not empty event.id}">Editez ici votre évènement</c:if>
		     		<c:if test="${empty event.id}">Créez ici votre nouvel évènement</c:if>
				</p>
			</div>
			<form class="form-horizontal" role="form" action="" method="post">
				<div class="form-group">
					<label for="nom" class="col-sm-2 control-label">NOM</label>
			    	<div class="col-sm-6">
			      		<input type="text" class="form-control" id="nom" name="nom" placeholder="Nom" maxlength="20" value="${event.nom}">
			    	</div>
				</div>
				<div class="form-group">
					<label for="description" class="col-sm-2 control-label">DESCRIPTION</label>
					<div class="col-sm-6">
			      		<input type="text" class="form-control" id="description" name="description" placeholder="Description" maxlength="50" value="${event.description}">
			    	</div>
				</div>
				<div class="form-group">
					<label for="adresse" class="col-sm-2 control-label">ADRESSE</label>
					<div class="col-sm-6">
			      		<input type="text" class="form-control" id="adresse" name="adresse" placeholder="Adresse" maxlength="20" value="${event.adresse}">
			    	</div>
				</div>
				<div class="form-group">
					<label for="datedeb" class="col-sm-2 control-label">DEBUT</label>
					<div class="col-sm-6">
			      		<input class="form-control datetime" id="datedeb" name="datedeb" placeholder="Date de début" value="${event.datedebToString}">
			    	</div>
				</div>
				<div class="form-group">
					<label for="datefin" class="col-sm-2 control-label">FIN</label>
					<div class="col-sm-6">
			      		<input class="form-control datetime" id="datefin" name="datefin" placeholder="Date de fin" value="${event.datefinToString}">
			    	</div>
				</div>
				<div class="form-group">
			    	<div class="col-sm-offset-2 col-sm-6">
			    		<a class="btn btn-default" href="<c:url value="/MyEvents"/>">Retour</a>
				     	<button type="submit" class="btn btn-primary">
				     		<c:if test="${not empty event.id}">Enregistrer</c:if>
				     		<c:if test="${empty event.id}">Créer</c:if>
				     	</button>
				    </div>
				</div>
			</form>
		</div>
		<!-- /Panel -->
	</div>
	<!-- /container -->
</body>
</html>