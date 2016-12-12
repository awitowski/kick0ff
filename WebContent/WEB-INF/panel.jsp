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
		<div class="col-sm-6 col-md-4 col-md-offset-4">
			<h3>
				Witaj
				<c:out value="${sessionScope.user.username}" />
			</h3>
			<form class="form-signin" action="panel" method="post">
				<h2 class="form-signin-heading">Uaktualnij profil</h2>
				<input class="form-control" id="disabledInput" type="text" placeholder="<c:out value="${sessionScope.user.username}" />" disabled>
				<input type="email" class="form-control" name="newEmail"
					placeholder="podaj nowy adres email"> <input
					type="password" class="form-control" name="newPassword"
					placeholder="podaj nowe hasło">
				<button type="submit" class="btn btn-block btn-default">Uaktualnij</button>
			</form>
		</div>
	
		<div class="col-sm-6 col-md-4 col-md-offset-4">
			<form class="form-signin" action="panel" method="post">

				<div class="radio">
					<label> <input type="radio" name="optionsRadios"
						id="optionsRadios1" value="deactivate" checked> Dezaktywuj
						konto
					</label>
				</div>
				<div class="radio">
					<label> <input type="radio" name="optionsRadios"
						id="optionsRadios2" value="activate"> Aktywuj konto
					</label>
				</div>

				<button type="submit" class="btn btn-block btn-default">Wykonaj</button>
			</form>
		</div>
	</div>
	<div class="container"><h2 class="form-signin-heading">Twoje znaleziska</h2></div>
	<c:choose>
	<c:when test="${not empty requestScope.discoveriesByUsername }">
		<c:forEach var="discovery" items="${requestScope.discoveriesByUsername }">
			<div class="container">
				<div class="row bs-callout bs-callout-info">
					<div class="col col-md-11 col-sm-10">
						<h3 class="centered">
							<a href="<c:out value="${discovery.url }" />"><c:out
									value="${discovery.name }" /></a>
						</h3>
						<h6>
							<small>Dodane przez: <c:out
									value="${discovery.user.username }" />, Dnia: <c:out
									value="${discovery.timestamp }" /></small>
						</h6>
						<p>
							<c:out value="${discovery.description }" />
						</p>
						<a href="<c:out value="${discovery.url }" />"
							class="btn btn-success btn-xm"> <span
							class="glyphicon glyphicon-globe" aria-hidden="true"></span> Przejdź
							do strony
						</a>
						<a href="${pageContext.request.contextPath }/update?discovery_id=${discovery.id}" class="btn btn-info btn-xm" role="button">Zaktualizuj</a>
						<c:choose>
						<c:when test="${discovery.active eq true }">
							<a href="${pageContext.request.contextPath }/deactivateOrActivate?discovery_id=${discovery.id}&status=deactivate" class="btn btn-danger btn-xm" role="button">Usuń</a>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath }/deactivateOrActivate?discovery_id=${discovery.id}&status=activate" class="btn btn-warning btn-xm" role="button">Przywróć</a>
						</c:otherwise>
						</c:choose>
						
					</div>
				</div>
			</div>
		</c:forEach>
	</c:when>
		<c:otherwise>
		<div class="container">
			<div class="alert alert-warning">
  <strong>Brak znalezisk! </strong> Kliknij <a href="add">tutaj</a> aby dodać nowe znalezisko</div>
  </div>
		</c:otherwise>
		</c:choose>
	<jsp:include page="fragment/footer.jspf" />

	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="resources/js/bootstrap.js"></script>

</body>
</html>