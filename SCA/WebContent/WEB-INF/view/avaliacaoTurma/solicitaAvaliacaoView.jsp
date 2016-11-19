<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SCA - Avaliação</title>
<link href="${pageContext.request.contextPath}/css/base.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/table.css"
	rel="stylesheet" type="text/css" />
</head>
<body class="basic-grey">

	<h1>Solicita Avaliação</h1>

	<c:if test="${requestScope.error != null}">
		<div>
			<p class="error">${requestScope.error}</p>
		</div>
	</c:if>

	<c:if test="${requestScope.info != null}">
		<div>
			<p class="info">${requestScope.info}</p>
		</div>
	</c:if>

	<div class="table">
		<form class="row"
			action="${pageContext.request.contextPath}/avaliacaoTurma/solicitaAvaliacaoMatricula"
			method="post">
			<div class="field">
				<div>Matrícula:</div>
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

</body>
</html>
