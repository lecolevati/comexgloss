package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.PaisesDao;

import model.Paises;

/**
 * Servlet implementation class ConsultaPaises
 */
@WebServlet("/consultapaises")
public class ConsultaPaisesBean extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConsultaPaisesBean() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PaisesDao pDao = new PaisesDao();
		String erro = "";
		List<Paises> lista = new ArrayList<Paises>();
		String url = "listaPaises.jsp";
		try {
			lista = pDao.consultaDisciplinasPorLetra(request.getParameter("letra"));
		} catch (SQLException e) {
			erro = "ERRO no processamento da requisição";
			url = "index.jsp";
		} finally {
			request.setAttribute("listaPaises", lista);
			
			request.setAttribute("erro", erro);
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

}
