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
	<c:if test="${usrAdm ne null}">

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
								<td >
									<a href="${pageContext.request.contextPath}/excel" accesskey="1" title="">
										<img src="./images/buttonexcel.png" alt="" />
									</a>
								</td>
							</tr>
							<tr>
								<td >
									<a href="${pageContext.request.contextPath}/adminGlossario?id=cadastro" accesskey="1" title="">
										<img src="./images/button3.png" alt="" />
									</a>
								</td>
							</tr>
						</table>
						<div class="header_01">ADMINISTRADOR</div>
						<form action="admin" method="post">
							<p>
								<c:if test="${not empty erro}">
									<c:out value="${erro}"></c:out>
								</c:if>
							</p>
							<table>
								<tr>
									<td>Disciplina</td>
									<td><select id="disciplina" name="disciplina">
											<option value="0">
												<c:out value="--Selecione uma opção--" />
											</option>
											<c:forEach items="${listaDisciplina}" var="disc">
												<option value="<c:out value="${disc.codigo}"/>">
													<c:out value="${disc}" />
												</option>
											</c:forEach>
									</select>
									<input type="hidden" name="usrAdm" value="${userAdm}"/>
									</td>
								</tr>
								<tr>
									<td>
										<input name="turno" type="radio" value="1">Manhã</input>
										<input name="turno" type="radio" value="2">Tarde</input>
									</td>
								</tr>
								<tr>
									<td>Status</td>
									<td><select id="status" name="status">
											<option value="0">
												<c:out value="--Selecione uma opção--" />
											</option>
											<option value="1">
												<c:out value="Aguardando" />
											</option>
											<option value="2">
												<c:out value="Corrigir" />
											</option>
											<option value="3">
												<c:out value="Aprovado" />
											</option>			
									</select></td>
								</tr>
								<tr>
									<td colspan="2"><input type="submit" name="BuscarAlunos"
										value="Listar" /></td>
								</tr>
								<c:if test="${listaAlunos ne null}">
								<tr>
									<!-- TODO fazer o select já selecionado -->
									<td colspan="2" align="center" style="color: white;"> 
										<c:out value="${materia}" />
									</td>
								</tr>
								<tr>
									<td>Alunos</td>
									<td><select id="alunos" name="aluno">
											<option value="0">
												<c:out value="--Selecione uma opção--" />
											</option>
											<c:forEach items="${listaAlunos}" var="al">
												<option value="<c:out value="${al.ra}"/>">
													<c:out value="${al}" />
												</option>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td colspan="2"><input type="submit" name="BuscarTrabalhos"
										value="Procurar Trabalhos" />
									<input type="hidden" name="codigoDisc" value="${codigoMateria}"/>
									<input type="hidden" name="codigoStat" value="${codigoStatus}"/>
									</td>
								</tr>
								</c:if>
							</table>
							<br />
							<br />
							<br />
							<c:if test="${listaAssuntos ne null}">
							
							<table border="1" style="font-size: 150%">
								<tr>
									<td colspan="4" align="center">
										<c:out value="${nmAluno}" />
									 </td>
								</tr>
								<tr>
									<td>
										País
									</td>
									<td>
										Assunto
									</td>
									<td>
										Status
									</td>
									<td>
										Validar
									</td>
								</tr>
								<c:forEach items="${listaAssuntos}" var="termos">
								<tr>
									<td>
										<c:out value="${termos.nomePais}"></c:out>
									</td>
									<td>
										<c:out value="${termos.assunto}"></c:out>
									</td>
									<td>
										<c:out value="${termos.estadoStatus}"></c:out>
									</td>
									<td align="center">
										<a href="${pageContext.request.contextPath}/validar?idexc=<c:out value="${termos.codigo}"/>&idal=<c:out value="${termos.raAluno}"/>" accesskey="1" title="" target="_blank">
					 						<img src="./images/check.png" width="16" height="16" alt="" />
					 					</a>
				 					</td>									
								</tr>
								</c:forEach>
							</table>
							</c:if>
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
					alt="Valid XHTML 1.0 Transitional" width="88" height="31"
					vspace="8" border="0" /></a> <a
					href="http://jigsaw.w3.org/css-validator/check/referer"><img
					style="border: 0; width: 88px; height: 31px"
					src="http://jigsaw.w3.org/css-validator/images/vcss-blue"
					alt="Valid CSS!" vspace="8" border="0" /></a>

			</div>
			<!-- end of footer -->
		</div>
		<!-- end of container -->

	</c:if>
	<c:if test="${raAluno ne admin}">
		<!-- TODO redirect index.jsp -->
	</c:if>
</body>

</html>