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

	<c:if test="${requestScope.turmas == null}">
		<br />
		<h1>Você não é inscrito a nenhuma turma.</h1>
	</c:if>
	<c:if test="${requestScope.turmas != null}">
		<h1>Escolher uma das suas turma a serem avaliadas:</h1>
		<div class="table">
			<c:forEach items="${requestScope.turmas}" var="turma">
				<div class="row">
					<div class="field">
						<span>Código: ${turma.codigoTurma}</span>
					</div>
					<div class="field">
						<span>Disciplina: ${turma.nomeDisciplina}</span>
					</div>
					<div class="field">
						<c:if test="${turma.isAvaliada}">
							<input class="lastfield" type="button" value="Avaliada"
								disabled="disabled" />
						</c:if>
						<c:if test="${! turma.isAvaliada}">
							<form
								action="${pageContext.request.contextPath}/avaliacaoTurma/solicitaAvaliacaoTurma"
								method="post">
								<input type="hidden" name="codigoTurma"
									value="${turma.codigoTurma}" /> <input class="lastfield"
									type="submit" value="Avalia" />
							</form>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</div>
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
