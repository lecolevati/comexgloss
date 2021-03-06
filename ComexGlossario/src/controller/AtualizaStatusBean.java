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
 * Servlet implementation class AtualizaStatusBean
 */
@WebServlet("/atualizaStatus")
public class AtualizaStatusBean extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtualizaStatusBean() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String erro = "";
		int codigoTermo = Integer.parseInt(request.getParameter("codigoTermo"));
		String opcao = request.getParameter("opcoes");
		String coment = request.getParameter("coments");
		AdminDao admDao = new AdminDao();
		Termo t = new Termo();
		t.setCodigo(codigoTermo);
		if (opcao.equals("aprovado")){
			t.setCodigoStatus(3);
		} else {
			if (opcao.equals("corrigir")){
				t.setCodigoStatus(2);
			} else {
				t.setCodigoStatus(5);
			}
		}
		t.setComentarios(coment);
		try {
			if (t.getCodigoStatus() == 5){
				admDao.reprovaTermo(t);
			} else {
				admDao.atualizaStatus(t);	
			}
		} catch (SQLException e) {
//			erro = e.getMessage();
		} finally{
			request.getRequestDispatcher("close.html").forward(request, response);
		}
	}
	
}
