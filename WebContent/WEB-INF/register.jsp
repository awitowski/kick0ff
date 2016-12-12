<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	
	<c:if test="${not empty requestScope.duplicate_user }">
	<div class="alert alert-danger" role="alert"><strong>Uwaga! </strong>Użytkownik już istnieje w bazie. Wprowadź inne dane.</div>
		</c:if>

	<div class="container">
		<div class="col-sm-6 col-md-4 col-md-offset-4">
			<form class="form-signin" action="register" method="post">
				<h2 class="form-signin-heading">Zarejestruj się</h2>
				<input type="email" class="form-control" name="inputEmail"
					placeholder="email" required autofocus> <input type="text"
					class="form-control" name="inputUsername"
					placeholder="nazwa użytkownika" required autofocus> <input
					type="password" class="form-control" name="inputPassword"
					placeholder="hasło" required>
				<button type="submit" class="btn btn-block btn-default">Zarejestruj</button>

				<p>
					Masz już konto?<a href="login"> Zaloguj się</a>
			</form>
		</div>
	</div>

	<jsp:include page="fragment/footer.jspf" />

	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="resources/js/bootstrap.js"></script>
</body>
</html>