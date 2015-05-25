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
 * Servlet implementation class AlunoCadastroBean
 */
@WebServlet("/alunocadastro")
public class AlunoCadastroBean extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AlunoCadastroBean() {
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
		if (!request.getParameter("racadastro").trim().isEmpty()
				&& !request.getParameter("nomecadastro").trim().isEmpty()) {
			String erro = "";
			String url = "cadastroAluno.jsp";
			List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
			List<Paises> listaPaises = new ArrayList<Paises>();
			AlunoDao aDao = new AlunoDao();
			Aluno a = new Aluno();
			a.setRa(request.getParameter("racadastro").trim());
			a.setNome(request.getParameter("nomecadastro").trim());
			a.setTurno(Integer.parseInt(request.getParameter("turno")));
			
			List<Termo> listaTermos = new ArrayList<Termo>();
			try {
				aDao.cadastraAluno(a);
				url = "cadastroGlossario.jsp";

				DisciplinaDao dDao = new DisciplinaDao();
				listaDisciplina = dDao.consultaDisciplinas();

				PaisesDao pDao = new PaisesDao();
				listaPaises = pDao.consultaDisciplinas();
				
				TermoDao tDao = new TermoDao();
				listaTermos = tDao.listaTermosPorAluno(a);

			} catch (SQLException e) {
				erro = "Erro no processamento da requisição";
				url = "index.jsp";
			} finally {
				request.setAttribute("erro", erro);
				request.setAttribute("raAluno", a.getRa());
				request.setAttribute("nomeAluno", a.getNome());
				request.setAttribute("turnoAluno", a.getTurno());
				
				request.setAttribute("listaDisciplina", listaDisciplina);
				request.setAttribute("listaPaises", listaPaises);
				request.setAttribute("listaTermos", listaTermos);

				request.getRequestDispatcher(url).forward(request, response);
			}
		}
	}
}
