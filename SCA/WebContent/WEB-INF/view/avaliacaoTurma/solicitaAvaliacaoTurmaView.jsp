<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<c:if test="${requestScope.questoes == null}">
		<h1>Não existem questões a serem respondidas!.</h1>
	</c:if>
	<c:if test="${requestScope.questoes != null}">
		<form
			action="${pageContext.request.contextPath}/avaliacaoTurma/avaliaTurma"
			method="post">
			<h1>
				Responder às questões de avaliação da turma<b>
					${requestScope.questoes.getCodigoTurma()}:</b>
			</h1>
			<input type="hidden" name="codigoTurma"
				value="${requestScope.questoes.getCodigoTurma()}" />
			<c:forEach items="${requestScope.questoes}" var="quesito"
				varStatus="i">
				<h3>Quesito ${i.index + 1}: ${quesito.quesito}</h3>
				<c:forEach items="${quesito.alternativas}" var="alternativas"
					varStatus="j">
					<input type="radio" name="quesito${i.index}" value="${j.index}">${alternativas}<br />
				</c:forEach>
				<br />
			</c:forEach>
			<input type="submit" value="Submeter" />
		</form>
	</c:if>

	<c:if test="${requestScope.error != null}">
		<br />
		<h2 class="error">${requestScope.error}</h2>
	</c:if>
	<c:if test="${requestScope.info != null}">
		<br />
		<h2 class="info">${requestScope.info}</h2>
	</c:if>
</body>
</html>
