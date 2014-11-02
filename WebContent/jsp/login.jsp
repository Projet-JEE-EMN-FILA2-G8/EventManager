<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="./fragments/libs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="./fragments/head.jsp"%>
<title>Connection</title>
</head>
<body>
	<%@ include file="./fragments/header.jsp"%>
	<div class="container">
		<h1 class="page-header">Connexion</h1>
		<h5>
			Vous n'avez pas d'identifiants ? <a href="Subscribe">Cr&eacute;ez un compte.</a>
		</h5>
		<div class="row">
			<form class="form-horizontal" role="form" action="" method="post">
				<div class="form-group">
			    	<label for="email" class="col-sm-2 control-label">Email:</label>
			    	<div class="col-sm-3">
			    		<input type="email" class="form-control" id="email" name="email" required="required" placeholder="Email">
			    	</div>
				</div>
				<div class="form-group">
					<label for="pwd" class="col-sm-2 control-label">Mot de passe:</label>
					<div class="col-sm-3">
						<input type="password" class="form-control" id="pwd" name="pwd" required="required" placeholder="Mot de passe">
				    </div>
				</div>
				<c:if test="${requestScope.badLoginOrPassword}">
					<div class="col-sm-offset-2 col-sm-10">
      					<font color=red>
							L'identifiant ou le mot de passe est incorrect.
						</font>
    				</div>
				</c:if>
				<c:if test="${requestScope.errorOccured}">
					<div class="col-sm-offset-2 col-sm-10">
      					<font color=red>
							Une erreur est survenue.<br>
							<i>Vous-pouvez recommencer en entrant des paramètres valides.</i>
						</font>
    				</div>
				</c:if>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-2">
						<button class="btn btn-primary btn-lg" type="submit" name="Login">Se connecter</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- /container -->
</body>
</html>




