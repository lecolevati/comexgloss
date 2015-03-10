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


					<div class="header_01">Login</div>
					<form action="admin" method="post">
						<p>
							<c:if test="${not empty erro}">
								<c:out value="${erro}"></c:out>
							</c:if>
						</p>
						<table>
							<tr>
								<td>Usuario</td>
								<td><input type="text" name="usuario" size="10" /></td>

							</tr>
							<tr>
								<td>Senha</td>
								<td><input type="password" name="senha" size="10" /></td>
							</tr>
							<tr>
								<td colspan="2"><input type="submit" name="login"
									value="Login" /></td>
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