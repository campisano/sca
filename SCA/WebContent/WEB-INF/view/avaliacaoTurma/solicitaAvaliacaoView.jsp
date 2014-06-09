<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SCA - Avaliação</title>
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/table.css"
	rel="stylesheet" type="text/css" />
</head>
<body class="basic-grey">
	<h1>Solicita Avaliação</h1>

	<div class="table">
		<form class="row"
			action="${pageContext.request.contextPath}/avaliacaoTurma/solicitaAvaliacaoMatricula"
			method="post">
			<div class="field">
				<span>Matricula:</span>
			</div>
			<div class="field">
				<input type="text" name="matricula" value="" size="30"
					maxlength="16" />
			</div>
			<div class="field">
				<input class="lastfield big" type="submit"
					value="Solicita Avaliação" />
			</div>
		</form>
	</div>
	<c:if test="${requestScope.error != null}">
		<h2 class="error">${requestScope.error}</h2>
	</c:if>
	<c:if test="${requestScope.info != null}">
		<h2 class="info">${requestScope.info}</h2>
	</c:if>
</body>
</html>
