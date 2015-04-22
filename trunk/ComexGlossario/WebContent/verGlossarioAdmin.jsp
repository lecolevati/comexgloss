<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Glossário Comex Fatec ZL</title>
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript">
	function clearText(field) {

		if (field.defaultValue == field.value)
			field.value = '';
		else if (field.value == '')
			field.value = field.defaultValue;

	}
</script>
</head>
<body>
	<div id="templatemo_container">
		<div id="templatemo_banner">
			<div id="logo"></div>
			<div id="search_section">
				<form action="aluno" method="post">
					<input type="text" value="Digite seu RA" name="ra" size="10"
						id="searchfield" title="searchfield" onfocus="clearText(this)"
						onblur="clearText(this)" /> <input type="submit" name="Search"
						value="Entrar" alt="Search" id="searchbutton" title="Search" />
				</form>
			</div>

		</div>
		<!-- end of banner -->

		<jsp:include page="menu.jsp" />
		<div id="templatemo_content_wrapper">
			<div id="templatemo_content">

				<jsp:include page="colesquerda.jsp" />
				<!-- end of a column -->

				<div class="column_w430 fl vl_divider">
						<table align="center">
							<tr>
								<td align="center">
									<a href="admin.jsp?userAdm=admin">
										<img src="./images/button2.png" alt="" />
									</a>
								</td>
							</tr>
							<tr>
								<td align="center">
									<a href="${pageContext.request.contextPath}/adminGlossario?id=cadastro" accesskey="1" title="">
										<img src="./images/button3.png" alt="" />
									</a>
								</td>
							</tr>							
							<tr>
								<td>
									<a href="#cadastrados">
										<img src="./images/button.png" alt="" />
									</a>
								</td>
							</tr>
						</table>
					<div class="header_01">Visualização de Termos</div>
					<h2>
						<c:out value="${mensagem}" />
					</h2>
					<form action="glossario" method="post">
						<p>
							<c:if test="${not empty erro}">
								<c:out value="${erro}" />
							</c:if>
						</p>
						<table>
							<tr>
								<td>RA</td>
								<td><input type="text" name="racadastro" size="10"
									readonly="readonly" value="<c:out value="${raAluno}" />" /></td>

							</tr>
							<tr>
								<td>Nome</td>
								<td><input type="text" name="nomecadastro" size="40"
									readonly="readonly" value="<c:out value="${nomeAluno}" />" /></td>
							</tr>
							<tr>
								<td>Disciplina</td>
								<td><input type="text" name="disciplina" size="40"
									readonly="readonly"
									value="<c:out value="${termoDisciplina}" />" /></td>
							</tr>
							<tr>
							</tr>
							<tr></tr>
							<tr></tr>
							<tr></tr>
							<tr></tr>
							<tr></tr>
							<tr>
								<td>País</td>
								<td><input type="text" name="paises" size="40"
									readonly="readonly" value="<c:out value="${termoPais}" />" /></td>
							</tr>
							<tr>
								<td>Assunto</td>
								<td><input type="text" name="assunto" size="40"
									value="${termoAssunto}" readonly="readonly" /></td>
							</tr>
							<c:if test="${termoComentarios ne null}">
								<tr>
									<td>Comentários Prof.</td>
									<td style="max-height: 150px;"><textarea rows="15"
											cols="42" readonly="readonly">
											<c:out value="${termoComentarios}" />
										</textarea></td>
								</tr>
							</c:if>
							<tr>
								<td>Texto (*Apenas texto)</td>
								<td><textarea rows="15" cols="42" name="texto"
										style="resize: none" readonly="readonly">
										<c:out value="${termoTexto}" />
									</textarea> <input type="hidden" value="${termoCodigo}" name="termocodigo" />
								</td>
							</tr>

						</table>
					</form>
					<p>&nbsp</p>
					<p>&nbsp</p>
					<p>&nbsp</p>
					<p>&nbsp</p>
					<p>&nbsp</p>
					<p>&nbsp</p>

					<div class="cleaner"></div>
				</div>
				<!-- end of a column -->


				<div class="margin_bottom_20 h_divider"></div>
				<div class="margin_bottom_20"></div>

				<div class="column_w920" align="center" id="cadastrados">
					<jsp:include page="footerTableAdmin.jsp" />
				</div>

				<div class="cleaner"></div>
			</div>
			<!-- end of wrapper 02 -->
		</div>
		<!-- end of wrapper 01 -->

		<div id="templatemo_footer">

			<div class="margin_bottom_10"></div>

			<a href="http://validator.w3.org/check?uri=referer"><img
				style="border: 0; width: 88px; height: 31px"
				src="http://www.w3.org/Icons/valid-xhtml10"
				alt="Valid XHTML 1.0 Transitional" width="88" height="31" vspace="8"
				border="0" /></a> <a
				href="http://jigsaw.w3.org/css-validator/check/referer"><img
				style="border: 0; width: 88px; height: 31px"
				src="http://jigsaw.w3.org/css-validator/images/vcss-blue"
				alt="Valid CSS!" vspace="8" border="0" /></a>

		</div>
		<!-- end of footer -->
	</div>
	<!-- end of container -->
</body>
</html>