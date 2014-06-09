<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SCA - Avaliação</title>
</head>
<body>
	<h1>Solicita Avaliação</h1>
	<hr />

	<form action="/sca/avaliacaoTurma/solicitaAvaliacao" method="post">
		Matricula: <input type="text" name="matricula" value="" size=30
			maxlength=16> <input type="submit" value="Solicita Avaliação">
	</form>

	<c:if test="${requestScope.error != null}">
		<hr />
		<h2>${requestScope.error}</h2>
	</c:if>


	<c:if test="${requestScope.info != null}">
		<hr />
		<p>${requestScope.info}</p>
	</c:if>
</body>
</html>
