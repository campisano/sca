<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SCA - Avaliação</title>
</head>
<body>
	<c:if test="${requestScope.questoes != null}">
		<form action="/sca/avaliacaoTurma/avaliaTurma" method="post">
			<h1>Responder às questões de avaliação da turma
				${requestScope.questoes.getCodigoTurma()}:</h1>
			<input type="hidden" name="codigoTurma"
				value="${requestScope.questoes.getCodigoTurma()}" />
			<hr />
			<c:forEach items="${requestScope.questoes}" var="quesito"
				varStatus="i">
                Quesito ${i.index + 1}: ${quesito.quesito}<br />
				<c:forEach items="${quesito.alternativas}" var="alternativas"
					varStatus="j">
					<input type="radio" name="quesito${i.index}" value="${j.index}">${alternativas}<br />
				</c:forEach>
				<hr />
			</c:forEach>
			<input type="submit" value="Submeter" />
		</form>
	</c:if>

	<c:if test="${requestScope.questoes == null}">
		<h1>Não existem questões a serem respondidas!.</h1>
	</c:if>

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
