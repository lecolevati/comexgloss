package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Termo;

import persistence.AdminDao;

/**
 * Servlet implementation class ValidarBean
 */
@WebServlet("/validar")
public class ValidarBean extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValidarBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String erro = "";
		int codigoTermo = Integer.parseInt(request.getParameter("idexc"));
		AdminDao admDao = new AdminDao();
		Termo t = new Termo();
		String url = "validarTermo.jsp";
		try {
			t = admDao.consultaTermos(codigoTermo);
			
		} catch (SQLException e) {
			erro = e.getMessage();
		} finally {
			request.setAttribute("disciplina", t.getSiglaDisciplina());
			request.setAttribute("raAluno", t.getRaAluno());
			request.setAttribute("nomeAluno", t.getNomeAluno());
			request.setAttribute("nomePais", t.getNomePais());
			request.setAttribute("assunto", t.getAssunto());
			request.setAttribute("termo", t.getTexto());
			request.setAttribute("codigo", t.getCodigo());
			request.setAttribute("erro", erro);
			request.getRequestDispatcher(url).forward(request, response);
		}
	}
}
