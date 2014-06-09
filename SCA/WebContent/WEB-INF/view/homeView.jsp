<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SCA - Home</title>
</head>
<body>
	<h1>Home</h1>
	<hr />

	<a href="/sca/avaliacaoTurma/solicitaAvaliacao"> Registrar avaliação de
		turma.</a>

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
