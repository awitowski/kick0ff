<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
				<div class="row bs-callout bs-callout-default">
					<div class="col col-md-11 col-sm-10">
					<p>Sortuj według: </p>
					<a href="${pageContext.request.contextPath}/home?sort=newest" class="btn btn-default" role="button">Najnowsze</a>
					<a href="${pageContext.request.contextPath}/home?sort=favourite" class="btn btn-default" role="button">Ulubione</a>
					<a href="${pageContext.request.contextPath}/home?sort=alphabetically" class="btn btn-default" role="button">Alfabetycznie</a>
					</div>
				</div>
	</div>

	<c:if test="${not empty requestScope.discoveries }">
		<c:forEach var="discovery" items="${requestScope.discoveries }">
			<c:if test="${discovery.active eq true }" >
			<div class="container">
				<div class="row bs-callout bs-callout-info">
					<div class="col col-md-1 col-sm-2">

						<a
							href="${pageContext.request.contextPath}/vote?discovery_id=${discovery.id}&vote=VOTE_UP"
							class="btn btn-block btn-primary btn-success"><span
							class="glyphicon glyphicon-thumbs-up"></span> </a>
						<div class="well well-sm centered">
							<c:out value="${discovery.upVote - discovery.downVote }" />
						</div>
						<a
							href="${pageContext.request.contextPath}/vote?discovery_id=${discovery.id}&vote=VOTE_DOWN"
							class="btn btn-block btn-primary btn-warning"><span
							class="glyphicon glyphicon-thumbs-down"></span> </a>
					</div>
					<div class="col col-md-11 col-sm-10">
						<h3 class="centered">
							<a href="<c:out value="${discovery.url }" />"><c:out
									value="${discovery.name }" /></a>
						</h3>
						<h6>
							<small>Dodane przez: <c:out
									value="${discovery.user.username }" />, 
									Dnia: <fmt:formatDate value="${discovery.timestamp}" pattern="dd-MM-yyyy" /></small>
						</h6>
						<p>
							<c:out value="${discovery.description }" />
						</p>
						<a href="<c:out value="${discovery.url }" />"
							class="btn btn-default btn-xm"> <span
							class="glyphicon glyphicon-globe" aria-hidden="true"></span> Przejdź
							do strony
						</a>
					</div>
				</div>
			</div>
			</c:if>
		</c:forEach>
	</c:if>

	<jsp:include page="fragment/footer.jspf" />

	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="resources/js/bootstrap.js"></script>
</body>
</html>