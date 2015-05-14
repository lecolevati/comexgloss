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

import model.Paises;
import model.Termo;

import persistence.PaisesDao;
import persistence.TermoDao;

/**
 * Servlet implementation class ConsultaTermosPaisBean
 */
@WebServlet("/consultatermospais")
public class ConsultaTermosPaisBean extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConsultaTermosPaisBean() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TermoDao tDao = new TermoDao();
		PaisesDao pDao = new PaisesDao(); 
		
		Paises p = new Paises();
		p.setCodigo(Integer.parseInt(request.getParameter("pais")));
		
		
		List<Termo> lista = new ArrayList<Termo>();
		String erro = "";
		String url = "listaTermos.jsp";

		try {
			p = pDao.consultaPaisPorCodigo(p);
			lista = tDao.listaTermosPorPais(p);
		} catch (SQLException e) {
			erro = "ERRO no processamento da requisição";
			url = "index.jsp";
		} finally {
			request.setAttribute("listaTermos", lista);
			request.setAttribute("nomePais", p.getNome());
			request.setAttribute("capitalPais", p.getCapital());
			
			request.setAttribute("erro", erro);
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

}
