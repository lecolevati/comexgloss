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
 * Servlet implementation class AdminGlossario
 */
@WebServlet("/adminGlossario")
public class AdminGlossario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminGlossario() {
		super();
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
		if (request.getParameter("id") != null) {
			/**
			 * Monta tela de Cadastro de Termo
			 */
			String mensagem = "";
			String url = "cadastroGlossarioAdmin.jsp";
			Aluno a = new Aluno();
			a.setRa("0000000");
			a.setNome("admin");
			
			TermoDao tDao = new TermoDao();
			List<Termo> listaTermos = new ArrayList<Termo>();
			List<Paises> listaPaises = new ArrayList<Paises>();
			List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();

			try {
				listaTermos = tDao.listaTermosPorAluno(a);

				PaisesDao pDao = new PaisesDao();
				listaPaises = pDao.consultaDisciplinas();
				DisciplinaDao dDao = new DisciplinaDao();
				listaDisciplina = dDao.consultaDisciplinas();

			} catch (SQLException e) {
				mensagem = "ERRO: Falha no processamento da requisição !!";
			} finally {
				request.setAttribute("raAluno", a.getRa());
				request.setAttribute("nomeAluno", a.getNome());
				request.setAttribute("listaPaises", listaPaises);
				request.setAttribute("listaTermos", listaTermos);
				request.setAttribute("listaDisciplina", listaDisciplina);
				request.setAttribute("mensagem", mensagem);
				request.getRequestDispatcher(url).forward(request,response);
			}
		} else {
			if (request.getParameter("sit") != null) {
				if (request.getParameter("sit").equals("ver")
						&& request.getParameter("codigoTermo") != null) {
					/**
					 * Monta tela de Visualização de Termo
					 */
					List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
					List<Paises> listaPaises = new ArrayList<Paises>();
					List<Termo> listaTermos = new ArrayList<Termo>();
					Aluno a = new Aluno();
					String url = "verGlossarioAdmin.jsp";

					String mensagem = "";
					Termo t = new Termo();
					t.setCodigo(Integer.parseInt(request
							.getParameter("codigoTermo")));
					TermoDao tDao = new TermoDao();
					try {
						t = tDao.consultaTermosPorCodigo(t);

						a.setRa(t.getRaAluno());
						AlunoDao aDao = new AlunoDao();
						a = aDao.consultaAluno(a);

						listaTermos = tDao.listaTermosPorAluno(a);

						DisciplinaDao dDao = new DisciplinaDao();
						listaDisciplina = dDao.consultaDisciplinas();

						PaisesDao pDao = new PaisesDao();
						listaPaises = pDao.consultaDisciplinas();

					} catch (SQLException e) {
						mensagem = "Falha no processamento da requisição !!";
					} finally {
						request.setAttribute("raAluno", a.getRa());
						request.setAttribute("nomeAluno", a.getNome());
						request.setAttribute("termoAssunto", t.getAssunto());
						request.setAttribute("termoTexto", t.getTexto());
						request.setAttribute("termoAssunto", t.getAssunto());
						request.setAttribute("termoDisciplina",
								t.getSiglaDisciplina());
						request.setAttribute("termoPais", t.getNomePais());
						request.setAttribute("termoCodigo", t.getCodigo());
						request.setAttribute("termoComentarios",
								t.getComentarios());

						request.setAttribute("listaDisciplina", listaDisciplina);
						request.setAttribute("listaPaises", listaPaises);
						request.setAttribute("listaTermos", listaTermos);

						request.setAttribute("mensagem", mensagem);
						request.getRequestDispatcher(url).forward(request,
								response);
					}
				}
			} else {
				if (request.getParameter("CadastroGlossario") != null) {
					/**
					 * Cadastra termo
					 */
					String mensagem = "";
					String url = "cadastroGlossarioAdmin.jsp";
					if (request.getParameter("disciplina").equals("0")
							|| request.getParameter("paises").equals("0")) {
						mensagem = "ERRO: Dados não cadastrados -> Os parâmetros Disciplina e País devem ser selecionados !!";
					} else {
						Aluno a = new Aluno();
						a.setRa("0000000");
						a.setNome("admin");

						Termo t = new Termo();
						t.setAssunto(request.getParameter("assunto"));
						t.setCodigoDisciplina(Integer.parseInt(request
								.getParameter("disciplina")));
						t.setCodigoPais(Integer.parseInt(request
								.getParameter("paises")));
						t.setTexto(request.getParameter("texto"));
						t.setRaAluno(request.getParameter("racadastro"));

						TermoDao tDao = new TermoDao();

						List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
						List<Paises> listaPaises = new ArrayList<Paises>();
						List<Termo> listaTermos = new ArrayList<Termo>();

						try {
							tDao.cadastraTermo(t);
							mensagem = "Dados inseridos com sucesso !";

							listaTermos = tDao.listaTermosPorAluno(a);

							DisciplinaDao dDao = new DisciplinaDao();
							listaDisciplina = dDao.consultaDisciplinas();

							PaisesDao pDao = new PaisesDao();
							listaPaises = pDao.consultaDisciplinas();

						} catch (SQLException e) {
							mensagem = "ERRO: Dados não cadastrados -> Falha no processamento da requisição !!";
						} finally {
							request.setAttribute("raAluno", a.getRa());
							request.setAttribute("nomeAluno", a.getNome());

							request.setAttribute("listaDisciplina",
									listaDisciplina);
							request.setAttribute("listaPaises", listaPaises);
							request.setAttribute("listaTermos", listaTermos);

							request.setAttribute("mensagem", mensagem);
							request.getRequestDispatcher(url).forward(request,
									response);
						}
					}

				} else {
					/**
					 * Monta a tela de update
					 */
					if (request.getParameter("codigoTermo") != null) {

						List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
						List<Paises> listaPaises = new ArrayList<Paises>();
						List<Termo> listaTermos = new ArrayList<Termo>();
						Aluno a = new Aluno();
						String url = "atualizaGlossarioAdmin.jsp";

						String mensagem = "";
						Termo t = new Termo();
						t.setCodigo(Integer.parseInt(request
								.getParameter("codigoTermo")));
						TermoDao tDao = new TermoDao();
						try {
							t = tDao.consultaTermosPorCodigo(t);

							a.setRa(t.getRaAluno());
							AlunoDao aDao = new AlunoDao();
							a = aDao.consultaAluno(a);

							listaTermos = tDao.listaTermosPorAluno(a);

							DisciplinaDao dDao = new DisciplinaDao();
							listaDisciplina = dDao.consultaDisciplinas();

							PaisesDao pDao = new PaisesDao();
							listaPaises = pDao.consultaDisciplinas();

						} catch (SQLException e) {
							mensagem = "Falha no processamento da requisição !!";
						} finally {
							request.setAttribute("raAluno", a.getRa());
							request.setAttribute("nomeAluno", a.getNome());
							request.setAttribute("termoAssunto", t.getAssunto());
							request.setAttribute("termoTexto", t.getTexto());
							request.setAttribute("termoAssunto", t.getAssunto());
							request.setAttribute("termoDisciplina",
									t.getSiglaDisciplina());
							request.setAttribute("termoPais", t.getNomePais());
							request.setAttribute("termoCodigo", t.getCodigo());
							request.setAttribute("termoComentarios",
									t.getComentarios());

							request.setAttribute("listaDisciplina",
									listaDisciplina);
							request.setAttribute("listaPaises", listaPaises);
							request.setAttribute("listaTermos", listaTermos);

							request.setAttribute("mensagem", mensagem);
							request.getRequestDispatcher(url).forward(request,
									response);
						}
					} else {
						/**
						 * Atualiza Texto
						 */
						if (request.getParameter("UpdateGlossario") != null) {
							Termo t = new Termo();
							t.setCodigo(Integer.parseInt(request
									.getParameter("termocodigo")));
							t.setTexto(request.getParameter("texto").trim());
							TermoDao tDao = new TermoDao();
							String mensagem = "";
							String url = "cadastroGlossarioAdmin.jsp";
							List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
							List<Paises> listaPaises = new ArrayList<Paises>();
							List<Termo> listaTermos = new ArrayList<Termo>();
							Aluno a = new Aluno();
							try {
								tDao.atualizaTermo(t);

								a.setRa(request.getParameter("racadastro"));
								AlunoDao aDao = new AlunoDao();
								a = aDao.consultaAluno(a);

								listaTermos = tDao.listaTermosPorAluno(a);

								DisciplinaDao dDao = new DisciplinaDao();
								listaDisciplina = dDao.consultaDisciplinas();

								PaisesDao pDao = new PaisesDao();
								listaPaises = pDao.consultaDisciplinas();

								mensagem = "Atualizado com sucesso !!";

							} catch (SQLException e) {
								mensagem = "Falha no processamento da requisição !!";
							} finally {
								request.setAttribute("raAluno", a.getRa());
								request.setAttribute("nomeAluno", a.getNome());

								request.setAttribute("listaDisciplina",
										listaDisciplina);
								request.setAttribute("listaPaises", listaPaises);
								request.setAttribute("listaTermos", listaTermos);

								request.setAttribute("mensagem", mensagem);
								request.getRequestDispatcher(url).forward(
										request, response);
							}
						} else {
							if (request.getParameter("idexc") != null) {
								Termo t = new Termo();
								t.setCodigo(Integer.parseInt(request
										.getParameter("idexc")));
								TermoDao tDao = new TermoDao();
								String mensagem = "";
								String url = "cadastroGlossarioAdmin.jsp";
								List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
								List<Paises> listaPaises = new ArrayList<Paises>();
								List<Termo> listaTermos = new ArrayList<Termo>();
								Aluno a = new Aluno();
								try {
									tDao.excluiTermo(t);

									a.setRa(request.getParameter("idal"));
									AlunoDao aDao = new AlunoDao();
									a = aDao.consultaAluno(a);

									listaTermos = tDao.listaTermosPorAluno(a);

									DisciplinaDao dDao = new DisciplinaDao();
									listaDisciplina = dDao
											.consultaDisciplinas();

									PaisesDao pDao = new PaisesDao();
									listaPaises = pDao.consultaDisciplinas();

									mensagem = "Excluído com sucesso !!";

								} catch (SQLException e) {
									mensagem = "Falha no processamento da requisição !!";
								} finally {
									request.setAttribute("raAluno", a.getRa());
									request.setAttribute("nomeAluno",
											a.getNome());

									request.setAttribute("listaDisciplina",
											listaDisciplina);
									request.setAttribute("listaPaises",
											listaPaises);
									request.setAttribute("listaTermos",
											listaTermos);

									request.setAttribute("mensagem", mensagem);
									request.getRequestDispatcher(url).forward(
											request, response);
								}
							}
						}
					}
				}
			}
		}
	}

}
