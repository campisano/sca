<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SCA Home</title>
</head>
<body>
	<h1>Solicita Avaliação</h1>
	<hr />

	<form action="/sca/avaliacao/solicitaAvaliacao" method="post">
		Matricula: <input type="text" name="matricula" value="" size=30
			maxlength=16> <input type="submit" value="Solicita Avaliação">
	</form>

	<c:if test="${requestScope.msg != null}">
		<hr />
		<h2>${requestScope.msg}</h2>
	</c:if>

	<p>The time on the server is ${serverTime}.</p>
</body>
</html>
