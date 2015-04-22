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
			<form action="atualizaStatus" method="post">
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
						<textarea rows="15" cols="42" name="texto"
										style="resize: none" readonly="readonly">
							<c:out value="${termo}"></c:out>
						</textarea>
						
						<input type="hidden" name="codigoTermo" value="${codigo}" />
					</td>
				</tr>
				</table>
				<br />
				<br />
				<br />
				<table align="center">
				<tr>
					<td colspan="3" style="color: #F7D358">
						Comentários do Professor
					</td>
				</tr>
				<tr>
					<td>
						<textarea name="coments" rows="15" cols="80" style="resize:none" ></textarea>
					</td>
				</tr>			
				<tr>
					<td align="center">
						<input type="radio" name="opcoes" value="aprovado"><img src="./images/aprovado.png" width="16" height="16" alt="" />
						<input type="radio" name="opcoes" value="corrigir" checked="checked"><img src="./images/corrigir.png" width="16" height="16" alt="" />
						<input type="radio" name="opcoes" value="reprovado"><img src="./images/reprovado.png" width="16" height="16" alt="" />
					</td>
					<tr>
						<td align="center">
							<input type="submit" name="enviar" value="Validar" />
						</td>
					<tr>
				</tr>
			</table>
			</form>
		</div>
	</c:if>
</body>
</html>