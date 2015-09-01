package controller.tags;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import model.Termo;
import persistence.TermoDao;

public class AssuntoTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		TermoDao tDao = new TermoDao();
		StringBuffer sb = new StringBuffer();
		try {
			List<Termo> lista = tDao.listaAssuntosAprovados();
			sb.append("<select name=\"listatermos\" style=\"width:200px;\">");
			for (Termo t : lista){
				sb.append("<option value=\"");
				sb.append(t.getCodigo());
				sb.append("\">");
				sb.append(t.getNomePais());
				sb.append(" - ");
				sb.append(t.getAssunto());
				sb.append("</option>");
			}
			sb.append("</select>");
		} catch (SQLException e) {
			sb = new StringBuffer();
			sb.append("Erro ao processar a base de dados");
		} 
		out.println(sb.toString());
	}

	
	
}
