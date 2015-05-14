package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.Paises;
import model.Termo;

public class TermoDao {

	private Connection c;

	public TermoDao() {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	public void cadastraTermo(Termo t) throws SQLException {
		String sql = "INSERT INTO termo (ra_aluno, codigo_disciplina, codigo_pais, assunto, termo, dthr) VALUES (?,?,?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, t.getRaAluno());
		ps.setInt(2, t.getCodigoDisciplina());
		ps.setInt(3, t.getCodigoPais());
		ps.setString(4, t.getAssunto());
		ps.setString(5, t.getTexto());
		ps.setDate(6, getCurrentDate());
		ps.execute();
		ps.close();
	}
	
	/**
	 * Atualiza apenas o termo
	 * @param t
	 * @throws SQLException
	 */
	public void atualizaTermo(Termo t) throws SQLException {
		String sql = "UPDATE termo SET termo = ?, codigo_status = 1, dthr = ? WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, t.getTexto());
		ps.setDate(2, getCurrentDate());
		ps.setInt(3, t.getCodigo());
		ps.execute();
		ps.close();
	}
	
	public void excluiTermo(Termo t) throws SQLException {
		String sql = "DELETE termo WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, t.getCodigo());
		ps.execute();
		ps.close();
	}
	
	public List<Termo> listaTermosPorAluno(Aluno a) throws SQLException{
		List<Termo> lista = new ArrayList<Termo>();
		String sql = "select termo.codigo, termo.ra_aluno, termo.codigo_disciplina, disciplina.sigla, termo.codigo_pais, paises.nome as pais, termo.assunto, termo.codigo_status, estado.estado from termo inner join disciplina on termo.codigo_disciplina = disciplina.codigo inner join paises on paises.codigo = termo.codigo_pais inner join estado on estado.codigo = termo.codigo_status where ra_aluno = ? order by codigo_pais";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, a.getRa());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Termo t = new Termo();
			t.setAssunto(rs.getString("assunto"));
			t.setCodigo(rs.getInt("codigo"));
			t.setCodigoDisciplina(rs.getInt("codigo_disciplina"));
			t.setCodigoPais(rs.getInt("codigo_pais"));
			t.setCodigoStatus(rs.getInt("codigo_status"));
			t.setEstadoStatus(rs.getString("estado"));
			t.setNomePais(rs.getString("pais"));
			t.setRaAluno(rs.getString("ra_aluno"));
			t.setSiglaDisciplina(rs.getString("sigla"));
			lista.add(t);
		}
		rs.close();
		ps.close();
		return lista;
	}
	
	public Termo consultaTermosPorCodigo(Termo t) throws SQLException{
		String sql = "select termo.comentarios, termo.termo, termo.codigo as cod, termo.ra_aluno, termo.codigo_disciplina as codigo_disciplina, disciplina.sigla, termo.codigo_pais as codigo_pais, paises.nome as pais, termo.assunto, termo.codigo_status as codigo_status, estado.estado from termo inner join disciplina on termo.codigo_disciplina = disciplina.codigo inner join paises on paises.codigo = termo.codigo_pais inner join estado on estado.codigo = termo.codigo_status where termo.codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, t.getCodigo());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			t.setAssunto(rs.getString("assunto"));
			t.setCodigo(rs.getInt("cod"));
			t.setCodigoDisciplina(rs.getInt("codigo_disciplina"));
			t.setCodigoPais(rs.getInt("codigo_pais"));
			t.setCodigoStatus(rs.getInt("codigo_status"));
			t.setEstadoStatus(rs.getString("estado"));
			t.setNomePais(rs.getString("pais"));
			t.setRaAluno(rs.getString("ra_aluno"));
			t.setSiglaDisciplina(rs.getString("sigla"));
			t.setTexto(rs.getString("termo"));
			t.setComentarios(rs.getString("comentarios"));
		}
		rs.close();
		ps.close();
		return t;
	}

	public List<Termo> listaTermosPorPais(Paises p) throws SQLException{
		List<Termo> lista = new ArrayList<Termo>();
		String sql = "select termo.termo, termo.codigo as cod, termo.codigo_pais as codigo_pais, paises.nome as pais, termo.assunto, aluno.nome as nome_aluno from termo inner join disciplina on termo.codigo_disciplina = disciplina.codigo inner join paises on paises.codigo = termo.codigo_pais inner join estado on estado.codigo = termo.codigo_status inner join aluno on aluno.ra = termo.ra_aluno where termo.codigo_pais = ? AND estado.codigo = 3 order by termo.assunto";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, p.getCodigo());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Termo t = new Termo();
			t.setAssunto(rs.getString("assunto"));
			t.setCodigo(rs.getInt("cod"));
			t.setCodigoPais(rs.getInt("codigo_pais"));
			t.setNomePais(rs.getString("pais"));
			if (rs.getString("nome_aluno").equals("admin")){
				t.setNomeAluno("Equipe do Comex Glossário");
			} else {
				t.setNomeAluno(rs.getString("nome_aluno"));
			}
			t.setTexto(rs.getString("termo"));
			lista.add(t);
		}
		rs.close();
		ps.close();
		return lista;
	}
	
	private static java.sql.Date getCurrentDate() {
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Date(today.getTime());
	}
}
