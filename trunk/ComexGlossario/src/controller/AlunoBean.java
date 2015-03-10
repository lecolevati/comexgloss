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
import model.Paises;
import model.Termo;

import persistence.AlunoDao;
import persistence.DisciplinaDao;
import persistence.PaisesDao;
import persistence.TermoDao;

/**
 * Servlet implementation class AlunoBean
 */
@WebServlet("/aluno")
public class AlunoBean extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AlunoBean() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (!request.getParameter("ra").trim().isEmpty()) {
			List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
			List<Paises> listaPaises = new ArrayList<Paises>();

			String erro = "";
			String url = "cadastroAluno.jsp";
			AlunoDao aDao = new AlunoDao();
			Aluno a = new Aluno();
			a.setRa(request.getParameter("ra").trim());
			
			List<Termo> listaTermos = new ArrayList<Termo>();
			try {
				boolean valida = false;
				boolean admin = false;
				if (a.getRa().trim().equals("admin")){
					admin = true;
				} else {
					valida = aDao.validaAluno(a);
				}

				if (valida) {
					url = "cadastroGlossario.jsp";
					a = aDao.consultaAluno(a);
					
					DisciplinaDao dDao = new DisciplinaDao();
					listaDisciplina = dDao.consultaDisciplinas();

					PaisesDao pDao = new PaisesDao();
					listaPaises = pDao.consultaDisciplinas();
					
					TermoDao tDao = new TermoDao();
					listaTermos = tDao.listaTermosPorAluno(a);
					
				} else {
					if (admin){
						url = "adminLogin.jsp";
						DisciplinaDao dDao = new DisciplinaDao();
						listaDisciplina = dDao.consultaDisciplinas();
					} else {
						url = "cadastroAluno.jsp";
						erro = "Aluno não cadastrado";						
					}

				}
			} catch (SQLException e) {
				erro = "RA inválido";
			} finally {
				request.setAttribute("raAluno", a.getRa());
				request.setAttribute("nomeAluno", a.getNome());

				request.setAttribute("listaDisciplina", listaDisciplina);
				request.setAttribute("listaPaises", listaPaises);
				request.setAttribute("listaTermos", listaTermos);
				
				request.setAttribute("erro", erro);
				request.getRequestDispatcher(url).forward(request, response);
			}
		}
	}

}
