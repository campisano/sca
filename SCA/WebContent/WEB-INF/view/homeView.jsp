<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SCA - Home</title>
<link href="${pageContext.request.contextPath}/css/base.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
</head>
<body class="basic-grey">

	<h1>Home</h1>

	<c:if test="${requestScope.error != null}">
		<div class="error">${requestScope.error}</div>
	</c:if>

	<c:if test="${requestScope.info != null}">
		<div class="info">${requestScope.info}</div>
	</c:if>

	<ul>
		<li><a
			href="${pageContext.request.contextPath}/avaliacaoTurma/solicitaAvaliacao">
				Registrar avalia��o de turma.</a></li>
	</ul>
</body>
</html>
