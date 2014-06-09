<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SCA - Home</title>
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/table.css"
	rel="stylesheet" type="text/css" />
</head>
<body class="basic-grey">
	<h1>Home</h1>

	<h2>
		<a
			href="${pageContext.request.contextPath}/avaliacaoTurma/solicitaAvaliacao">
			Registrar avaliação de turma.</a>
	</h2>

	<c:if test="${requestScope.error != null}">
		<br />
		<h2>${requestScope.error}</h2>
	</c:if>
	<c:if test="${requestScope.info != null}">
		<br />
		<h2>${requestScope.info}</h2>
	</c:if>
</body>
</html>
