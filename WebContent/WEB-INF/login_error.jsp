<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Excavation</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/styles.css"
	type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/custom.css"
	type="text/css" rel="stylesheet">
</head>

<body>

	<jsp:include page="fragment/navbar.jspf" />

	<div class="alert alert-danger" role="alert">Dane niepoprawne. Spróbuj ponownie.</div>
	
	<div class="container">
		<div class="col-sm-6 col-md-4 col-md-offset-4">
			<form class="form-signin" action="j_security_check" method="post">
				<h2 class="form-signin-heading">Zaloguj się</h2>
				<input type="text" class="form-control" name="j_username"
					placeholder="nazwa użytkownika" required autofocus> <input
					type="password" class="form-control" name="j_password"
					placeholder="hasło" required>
				<button type="submit" class="btn btn-block btn-default">Zaloguj</button>
				<div class="checkbox">
					<label><input type="checkbox"> Pamiętaj mnie</label>
				</div>
				<p>
					Nie masz konta?<a href="register"> Zarejestruj się</a>
			</form>
		</div>
	</div>
	
	<jsp:include page="fragment/footer.jspf" />

	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="resources/js/bootstrap.js"></script>
</body>
</html>