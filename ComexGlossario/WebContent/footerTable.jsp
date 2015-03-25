<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</script>
</head>
<body>
				 	<table border="1">
				 		<tr>
				 			<td>Disciplina</td>
				 			<td>País</td>
				 			<td>Assunto</td>
				 			<td>Status</td>
				 			<td>Editar Texto</td>
				 			<td>Excluir</td>
				 		</tr>
				 		<c:forEach items="${listaTermos}" var="termo">
				 		<tr>
				 			<td><c:out value="${termo.siglaDisciplina}"/></td>
				 			<td><c:out value="${termo.nomePais}"/></td>
				 			<td><c:out value="${termo.assunto}"/></td>
				 			<td><c:out value="${termo.estadoStatus}"/></td>
				 			<td align="center">
				 				<c:if test="${termo.codigoStatus <= 2}">
				 					<a href="${pageContext.request.contextPath}/glossario?codigoTermo=<c:out value="${termo.codigo}"/>" accesskey="1" title="" >
				 						<img src="./images/refresh.png" width="16" height="16" alt="" />
				 					</a>
				 				</c:if>
				 			</td>
				 			<td align="center">
				 				<c:if test="${termo.codigoStatus <= 1}">
				 					<a href="${pageContext.request.contextPath}/glossario?idexc=<c:out value="${termo.codigo}"/>&idal=<c:out value="${termo.raAluno}"/>" accesskey="1" title="" >
				 						<img src="./images/delete.png" width="16" height="16" alt="" />
				 					</a>
				 				</c:if>
				 			</td>
				 		</tr>
				 		</c:forEach>
				 	</table>
</body>
</html>