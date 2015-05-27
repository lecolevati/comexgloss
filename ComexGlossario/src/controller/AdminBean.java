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
import model.Termo;

import persistence.AdminDao;
import persistence.AlunoDao;
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
				int turno = Integer.parseInt(request.getParameter("turno"));
				String usuarioAdm = request.getParameter("usrAdm");
				String erro = "";
				String url = "admin.jsp";
				Disciplina d = new Disciplina();
				if (request.getParameter("disciplina") != null){
					d.setCodigo(Integer.parseInt(request.getParameter("disciplina")));
				} else {
					if (request.getParameter("codigoDisc") != null){
						d.setCodigo(Integer.parseInt(request.getParameter("codigoDisc")));
					}
				}
				int codigoStatus = Integer.parseInt(request.getParameter("status"));
				AdminDao admDao = new AdminDao();
				List<Aluno> listaAlunos = new ArrayList<Aluno>();
				List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
				try {
					listaAlunos = admDao.listaAlunosPorDisciplinaStatus(d, codigoStatus, turno);
					DisciplinaDao dDao = new DisciplinaDao();
					d = dDao.consultaDisciplina(d);
					listaDisciplina = dDao.consultaDisciplinas();
				} catch (SQLException e) {
					erro = e.getMessage();
				} finally {
					request.setAttribute("usrAdm", usuarioAdm);
					request.setAttribute("codigoStatus", codigoStatus);
					request.setAttribute("materia", d.getNome());
					request.setAttribute("codigoMateria", d.getCodigo());
					request.setAttribute("listaAlunos", listaAlunos);
					request.setAttribute("listaDisciplina", listaDisciplina);
					request.setAttribute("erro", erro);
					request.getRequestDispatcher(url).forward(request, response);
				}
			} else {
				if (request.getParameter("BuscarTrabalhos") != null){
					String usuarioAdm = request.getParameter("usrAdm");
					String erro = "";
					String url = "admin.jsp";
					Aluno a = new Aluno();
					Disciplina d = new Disciplina();
					a.setRa(request.getParameter("aluno"));
					d.setCodigo(Integer.parseInt(request.getParameter("codigoDisc")));
					int codigoStatus = Integer.parseInt(request.getParameter("codigoStat"));
					AdminDao admDao = new AdminDao();
					List<Aluno> listaAlunos = new ArrayList<Aluno>();
					List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
					List<Termo> listaTermos = new ArrayList<Termo>();
					try {
						int turno = admDao.consultaTurnoAluno(a);
						listaTermos = admDao.listaAssuntosPorAlunoDisciplinaStatus(a, d, codigoStatus);
						listaAlunos = admDao.listaAlunosPorDisciplinaStatus(d, codigoStatus, turno);
						DisciplinaDao dDao = new DisciplinaDao();
						listaDisciplina = dDao.consultaDisciplinas();
						d = dDao.consultaDisciplina(d);
						AlunoDao aDao = new AlunoDao();
						a = aDao.consultaAluno(a);
					} catch (SQLException e) {
						erro = e.getMessage();
						url = "index.jsp";
					} finally {
						request.setAttribute("usrAdm", usuarioAdm);
						request.setAttribute("nmAluno", a.getNome());
						request.setAttribute("listaAssuntos", listaTermos);
						request.setAttribute("codigoStatus", codigoStatus);
						request.setAttribute("materia", d.getNome());
						request.setAttribute("codigoMateria", d.getCodigo());
						request.setAttribute("listaAlunos", listaAlunos);
						request.setAttribute("listaDisciplina", listaDisciplina);
						request.setAttribute("erro", erro);
						request.getRequestDispatcher(url).forward(request, response);
					}
				} else {
					if (request.getParameter("login") != null){
						String erro = "";
						String url = "admin.jsp";
						String usuario = request.getParameter("usuario");
						String senha = request.getParameter("senha");
						AdminDao admDao = new AdminDao();
						boolean valido = false;
						List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
						try {
							valido = admDao.validaAdmin(usuario, senha);
							DisciplinaDao dDao = new DisciplinaDao();
							listaDisciplina = dDao.consultaDisciplinas();
						} catch (SQLException e) {
							erro = e.getMessage();
						} finally {
							if (valido){
								request.setAttribute("usrAdm", usuario);
								request.setAttribute("listaDisciplina", listaDisciplina);
							}
							request.setAttribute("erro", erro);
							request.getRequestDispatcher(url).forward(request, response);
						}
					} else {
						if (request.getParameter("relogin") != null){
							String erro = "";
							String url = "admin.jsp";
							String usuario = request.getParameter("usuario");
							List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
							try {
								DisciplinaDao dDao = new DisciplinaDao();
								listaDisciplina = dDao.consultaDisciplinas();
							} catch (SQLException e) {
								erro = e.getMessage();
							} finally {
								request.setAttribute("usrAdm", usuario);
								request.setAttribute("listaDisciplina", listaDisciplina);
								request.setAttribute("erro", erro);
								request.getRequestDispatcher(url).forward(request, response);
							}
						}
					}
				}
			}
			
		}
	}

}
