<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Gloss�rio Comex Fatec ZL</title>
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript">
	function clearText(field) {

		if (field.defaultValue == field.value)
			field.value = '';
		else if (field.value == '')
			field.value = field.defaultValue;

	}
	
	function alteraFonte (fonte){
		
		
		var txtaTexto = document.getElementById("txtaTexto");
		if (fonte.value == '1'){
		
			txtaTexto.style.fontFamily = "Arial";
			
		}
		else{
			if (fonte.value == '2'){
				txtaTexto.style.fontFamily = "Arial Black";
			}
				
			else{
				if (fonte.value == '3'){
					txtaTexto.style.fontFamily = "Times New Roman";
					
				}
				else{
					if (fonte.value == '4'){
						txtaTexto.style.fontFamily = "Comic Sans MS";
						
					}
					else{
						if (fonte.value == '5'){
							txtaTexto.style.fontFamily = "Verdana";
							
						}
					}
				}
			}			
		}		
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
								<td >
									<a href="#cadastrados">
										<img src="./images/button.png" alt="" />
									</a>
								</td>
							</tr>
						</table>
					<div class="header_01">Cadastro de Termos</div>
					<h2><c:out value="${mensagem}" /></h2>
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
								<td><select id="disciplina" name="disciplina">
										<option value="0">
											<c:out value="--Selecione uma op��o--" />
										</option>
										<c:forEach items="${listaDisciplina}" var="disc">
											<option value="<c:out value="${disc.codigo}"/>">
												<c:out value="${disc}" />
											</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
							</tr>
							<tr></tr>
							<tr></tr>
							<tr></tr>
							<tr></tr>
							<tr></tr>
							<tr>
								<td>Pa�s</td>
								<td><select id="paises" name="paises">
										<option value="0">
											<c:out value="--Selecione uma op��o--" />
										</option>
										<c:forEach items="${listaPaises}" var="pais">
											<option value="<c:out value="${pais.codigo}"/>">
												<c:out value="${pais}" />
											</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td>Assunto</td>
								<td><input type="text" name="assunto" size="40" /></td>
							</tr>
							<tr></tr>
							<tr></tr>
							<tr></tr>
							<tr>
								<td>Fonte</td>
								<td>
									<select name="cbFonte" onchange="alteraFonte(this)" >
										<option value="1">Arial</option>
										<option value="2">Arial Black</option>
										<option value="3">Times New Roman</option>
										<option value="4">Comic Sans MS</option>
										<option value="5">Verdana</option>
									</select>
								</td>
							</tr>
							
							<tr>
								<td>Texto (*Apenas texto)</td>
								<td>
									<textarea rows="15" cols="42" name="texto" id="txtaTexto" style="font-family:Arial;resize:none" ></textarea>
								</td>
							</tr>
							<tr>
								<td colspan="2"><input type="submit" name="CadastroGlossario"
									value="Cadastrar" /></td>
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
				 	
				 	<jsp:include page="footerTable.jsp" />
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