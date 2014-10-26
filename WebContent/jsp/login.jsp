<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Connection</title>
<link href="<c:url value="/ext/bootstrap/css/bootstrap.css"/>"
	rel="stylesheet">
<link rel="icon" href="../../favicon.ico">

<!-- Custom styles for this template -->
<link href="signin.css" rel="stylesheet">


<!-- 
/ext/bootstrap/css/bootstrap.css
/ext/bootstrap/css/bootstrap-responsive.css
/ext/bootstrap/js/bootstrap.js
/ext/bootstrap/img/glyphicons-halflings.png (referenced from the css files)
/ext/bootstrap/img/glyphicons-halflings-white.png (referenced from the css files)
 -->

</head>
<body style="background: #FFFFFF">
	<div class="container" style="background: #FFFFFF">
		<h1 class="page-header">Connection</h1>
		<h5>
			Vous n'avez pas d'identifiants ? <a href="./creation.jsp">Cr&eacute;ez un compte.</a>
		</h5>
		<div class="row">
			<form class="form-horizontal" role="form" target="./event.jsp">

				<div class="form-group">
					<label for="identifiant" class="col-sm-1 control-label">Identifiant:</label>
					<div class="col-sm-11">
						<input type="text" class="form-control" name="identifiant"><br />
					</div>
				</div>

				<div class="form-group">
					<label for="password" class="col-sm-1 control-label">Mot de
						passe:</label>
					<div class="col-sm-11">
						<input type="password" class="form-control" name="password"><br />
					</div>
				</div>

				<div class="form-group">
					<div class="col-12">
						<button class="btn btn-primary btn-lg" type="submit">Se connecter</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- /container -->
</body>
</html>






