<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="br.cefetrj.sca.dominio.Turma"%>
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

		<select>
			<c:forEach items="${requestScope.turmas}" var="turma">
				<option value="${turma[0]}">${turma[1]}</option>
			</c:forEach>
		</select>
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