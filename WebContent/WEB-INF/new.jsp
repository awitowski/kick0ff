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
	
	<c:if test = "${sessionScope.user.active eq false}">
		<div class="alert alert-danger" role="alert"><strong>Uwaga! </strong>Nie możesz dodawać nowych treści, jeżeli Twoje konto jest nieaktywne. Przejdź do Twojego Konta i wybierz opcję aktywuj. </div>
	</c:if>

		<c:if test="${not empty requestScope.duplicate_url }">
	<div class="alert alert-danger" role="alert"><strong>Uwaga! </strong>Podany adres URL już istnieje. Wprowadź inny.</div>
		</c:if>
		
	<c:if test="${not empty requestScope.integrity }">
		<div class="alert alert-danger" role="alert"><strong>Uwaga! </strong>Wprowadziłeś zbyt dużą liczbę znaków. Ogranicz do 100 dla nazwy i 250 dla opisu</div>
	</c:if>
	
	

	<div class="container">
		<div class="col-sm-10 col-md-8 col-md-offset-2">
			<form class="form-signin" action="add" method="post">
				<h2 class="form-signin-heading">Dodaj nowe znalezisko</h2>
				<input type="text" class="form-control" name="inputName"
					placeholder="nazwa znaleziska (max. 100 znaków)" required autofocus> <input
					type="url" class="form-control" name="inputUrl"
					placeholder="adres strony" required autofocus> 
				<textarea class="form-control" name="inputDescription"
					placeholder="opis (max. 250 znaków)" rows="5" required autofocus></textarea>
				<button type="submit" class="btn btn-block btn-default">Dodaj</button>
			</form>
		</div>
	</div>

	<jsp:include page="fragment/footer.jspf" />

	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="resources/js/bootstrap.js"></script>
</body>
</html>