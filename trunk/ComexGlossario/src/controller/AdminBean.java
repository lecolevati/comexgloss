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

import model.Aluno;
import model.Disciplina;

import persistence.AdminDao;
import persistence.DisciplinaDao;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin")
public class AdminBean extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminBean() {
        super();
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
		if (request.getParameter("VerificarAdmin") != null){
			//TODO Validar usuario com senha
		} else {
			if (request.getParameter("BuscarAlunos") != null){
				String erro = "";
				String url = "admin.jsp";
				Disciplina d = new Disciplina();
				d.setCodigo(Integer.parseInt(request.getParameter("disciplina")));
				int codigoStatus = Integer.parseInt(request.getParameter("status"));
				AdminDao admDao = new AdminDao();
				List<Aluno> listaAlunos = new ArrayList<Aluno>();
				List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
				try {
					listaAlunos = admDao.listaAlunosPorDisciplinaStatus(d, codigoStatus);
					DisciplinaDao dDao = new DisciplinaDao();
					listaDisciplina = dDao.consultaDisciplinas();
				} catch (SQLException e) {
					erro = e.getMessage();
				} finally {
					request.setAttribute("listaAlunos", listaAlunos);
					request.setAttribute("listaDisciplina", listaDisciplina);
					request.setAttribute("erro", erro);
					request.getRequestDispatcher(url).forward(request, response);
				}
			} else {
				if (request.getParameter("BuscarTrabalhos") != null){
					//TODO lista de trabalhos por aluno da disciplina
				}
			}
			
		}
	}

}
