<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SCA</title>
</head>
<body>
	<c:if test="${requestScope.turmas != null}">
		<h1>Escolher uma das suas turma a serem avaliadas:</h1>
		<hr />

		<c:forEach items="${requestScope.turmas}" var="turma">
            Código: ${turma.codigoTurma} -
            Disciplina: ${turma.nomeDisciplina} -
            <c:if test="${! turma.isAvaliada}">
				<form action="/sca/avaliacao/solicitaAvaliacaoTurma" method="post">
					<input type="hidden" name="codigoTurma"
						value="${turma.codigoTurma}" /> <input type="submit"
						value="Avalia" />
				</form>
			</c:if>
			<c:if test="${turma.isAvaliada}">
                Avaliada
            </c:if>
			<br />
		</c:forEach>
	</c:if>

	<c:if test="${requestScope.turmas == null}">
		<h1>Você não é inscrito a nenhuma turma.</h1>
	</c:if>

	<c:if test="${requestScope.msg != null}">
		<hr />
		<h2>${requestScope.msg}</h2>
	</c:if>
</body>
</html>
