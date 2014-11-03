<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	
<%@ include file="./fragments/libs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="./fragments/head.jsp"%>
<title>Cr&eacute;er un compte</title>
</head>
<body>
<%@ include file="./fragments/header.jsp"%>
	<div class="container">
		<h1 class="page-header">Ouvrir un compte</h1>
		<h5>
			Vous avez d&eacute;j&agrave; un compte ? <a href="Login">Connectez-vous.</a>
		</h5>
		<div class="row">
			<form class="form-horizontal" role="form" action="" method="post">
				<div class="form-group">
			    	<label for="email" class="col-sm-2 control-label">Email:</label>
			    	<div class="col-sm-3">
			    		<input type="email" class="form-control" id="email" name="email" required="required" placeholder="Email" maxlength="30" value="${user.email}">
			    	</div>
				</div>
				<div class="form-group">
					<label for="pwd" class="col-sm-2 control-label">Mot de passe:</label>
					<div class="col-sm-3">
						<input type="password" class="form-control" id="pwd" name="pwd" required="required" placeholder="Mot de passe" value="${user.pwd}">
				    </div>
				</div>
				<div class="form-group">
					<label for="confirm" class="col-sm-2 control-label">Confirmer:</label>
					<div class="col-sm-3">
						<input type="password" class="form-control" id="confirm" name="confirm" required="required" placeholder="Mot de passe (Confimation)">
				    </div>
				</div>
				<c:if test="${requestScope.notMatchingPwd}">
					<div class="col-sm-offset-2 col-sm-10">
      					<font color=red>
							<i>Les mots de passe ne correspondent pas !</i>
						</font>
    				</div>
				</c:if>
				<c:if test="${requestScope.alreadyRegistered}">
					<div class="col-sm-offset-2 col-sm-10">
      					<font color=red>
							<i>Vous avez déjà un compte !</i>
						</font>
    				</div>
				</c:if>
				<c:if test="${requestScope.errorOccured}">
					<div class="col-sm-offset-2 col-sm-10">
      					<font color=red>
							Une erreur est survenue lors de l'inscription.<br>
							<i>Vous pouvez recommencer en entrant des paramètres valides.</i>
						</font>
    				</div>
				</c:if>
				<!--  <div class="form-group">
    				<div class="col-sm-offset-2 col-sm-10">
      					<div class="checkbox">
        					<label>
          						<input type="checkbox" name="cgu">
								J'accepte les <a href="" target="_blank">conditions générales d'utilisation</a>
        					</label>
      					</div>
    				</div>
 				</div>-->

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-2">
						<button class="btn btn-primary btn-lg" type="submit" name="Login">Ouvrir un compte</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- /container -->
</body>
</html>






