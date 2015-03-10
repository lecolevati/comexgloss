<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<c:if test="${termo ne null}">
		<div align="center">
			<table border="1" style="font-size: 160%">
				<tr>
					<td style="color: #F7D358">
						Disciplina
					</td>
					<td style="color: #F7D358">
						RA
					</td>					
					<td style="color: #F7D358">
						Aluno
					</td>
				</tr>
				<tr>
					<td>
						<c:out value="${disciplina}"></c:out>
					</td>
					<td>
						<c:out value="${raAluno}"></c:out>
					</td>
					<td>
						<c:out value="${nomeAluno}"></c:out>
					</td>					
				</tr>			
				<tr>
					<td style="color: #F7D358">
						País
					</td>
					<td colspan="2" style="color: #F7D358">
						Assunto
					</td>					
				</tr>
				<tr>
					<td>
						<c:out value="${nomePais}"></c:out>
					</td>
					<td colspan="2">
						<c:out value="${assunto}"></c:out>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<c:out value="${termo}"></c:out>
					</td>
				</tr>
				<tr>
					<td>
						<a href="${pageContext.request.contextPath}/atualizaStatus?idexc=<c:out value="${codigo}"/>&idstat=3" accesskey="1" title="" >
					 		<img src="./images/aprovado.png" width="16" height="16" alt="" />
					 	</a>
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/atualizaStatus?idexc=<c:out value="${codigo}"/>&idstat=2" accesskey="1" title="" >
					 		<img src="./images/corrigir.png" width="16" height="16" alt="" />
					 	</a>
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/atualizaStatus?idexc=<c:out value="${codigo}"/>&idstat=5" accesskey="1" title="" >
					 		<img src="./images/reprovado.png" width="16" height="16" alt="" />
					 	</a>
					</td>
				</tr>
			</table>
		</div>
	</c:if>
</body>
</html>