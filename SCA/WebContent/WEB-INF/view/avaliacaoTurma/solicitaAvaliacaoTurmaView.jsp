<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	<c:if test="${requestScope.error != null}">
		<div class="error">${requestScope.error}</div>
	</c:if>

	<c:if test="${requestScope.info != null}">
		<div class="info">${requestScope.info}</div>
	</c:if>

	<c:if test="${requestScope.questoes == null}">
		<h3>Não existem questões a serem respondidas.</h3>
	</c:if>

	<c:if test="${requestScope.questoes != null}">

		<h1>
			Responder às questões de avaliação da turma<b>
				${requestScope.questoes.getCodigoTurma()}:</b>
		</h1>

		<form
			action="${pageContext.request.contextPath}/avaliacaoTurma/avaliaTurma"
			method="post">
			<input type="hidden" name="codigoTurma"
				value="${requestScope.questoes.getCodigoTurma()}" />
			<c:forEach items="${requestScope.questoes}" var="quesito"
				varStatus="i">
				<h2>${i.index + 1})&nbsp;${quesito.quesito}</h2>
				<c:forEach items="${quesito.alternativas}" var="alternativas"
					varStatus="j">
					<input type="radio" name="quesito${i.index}" value="${j.index}" />
					<div class="question">${alternativas}</div>
					<br />
				</c:forEach>
				<br />
			</c:forEach>
			<input type="submit" value="Submeter" />
		</form>
	</c:if>

</body>
</html>
