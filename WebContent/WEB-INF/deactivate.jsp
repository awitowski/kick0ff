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

	
	
	<div class="container">
		<div class="col-sm-8 col-md-6 col-md-offset-3">
			<div class="alert alert-danger" role="alert"><strong>Uwaga! </strong>Twoje konto jest nieaktywne. Nie możesz wykonać tej operacji. </div>
		</div>
	</div>
	
	<jsp:include page="fragment/footer.jspf" />

	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="resources/js/bootstrap.js"></script>
</body>
</html>