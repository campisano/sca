<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SCA</title>
</head>
<body>
	<h1>Solicita Avaliação</h1>
	<hr />
	<form action="rest/avaliacao/solicitaAvaliacao" method="post">
		Matricula: <input type="text" name="matricula" value="" size=30
			maxlength=16> <input type="submit" value="Solicita Avaliação">
	</form>

</body>
</html>