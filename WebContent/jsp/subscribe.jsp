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

<title>Cr&eacute;er un compte</title>
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
		<h1 class="page-header">Ouvrir un compte</h1>
		<h5>
			Vous avez d&eacute;j&agrave; un compte ? <a href="./login.jsp">Connectez-vous.</a>
		</h5>
		<div class="row">
			<form class="form-horizontal" role="form">

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
					<label for="confirm" class="col-sm-3 control-label">Confimer:</label>
					<div class="col-sm-11">
						<input type="password" class="form-control" name="confirm"><br />
					</div>
				</div>

				<div class="form-group">
					<div class="">
						<label class="checkbox">
							<input type="checkbox" value="cgu" class="form-control">
							J'accepte les <a href="">conditions g&eacute;n&eacute;rales d'utilisation</a>
						</label>
						<button class="btn btn-lg btn-primary" type="submit">Ouvrir	un compte</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- /container -->
</body>
</html>






