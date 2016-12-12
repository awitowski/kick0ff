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
	

	<div class="container">
		<div class="col-sm-10 col-md-8 col-md-offset-2">
			<form class="form-signin" action="update" method="post">
				<h2 class="form-signin-heading">Zaktualizuj znalezisko</h2>
				<input type="text" class="form-control" name="newName"
					placeholder="nowa nazwa znaleziska" > <input
					type="url" class="form-control" name="newUrl"
					placeholder="nowy adres strony" > 
				<textarea class="form-control" name="newDescription"
					placeholder="nowy opis" rows="5" ></textarea>
				<button type="submit" class="btn btn-block btn-default">Zaktualizuj</button>
			</form>
		</div>
	</div>

	<jsp:include page="fragment/footer.jspf" />

	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="resources/js/bootstrap.js"></script>
</body>
</html>