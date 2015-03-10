package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.Disciplina;
import model.Termo;

public class AdminDao {

	Connection c;

	public AdminDao(){
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	public boolean validaAdmin(String usuario, String senha) throws SQLException{
		boolean valido = false;
		String sql = "select usuario from administradores where usuario = ? and senha = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, usuario);
		ps.setString(2, senha);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			valido = true;
		}
		rs.close();
		ps.close();
		return valido;
	}
	
	public void reprovaTermo(Termo t) throws SQLException{
		String sql = "DELETE termo WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, t.getCodigo());
		ps.execute();
		ps.close();
	}
	
	public void atualizaStatus(Termo t) throws SQLException{
		String sql = "UPDATE termo SET codigo_status = ? WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, t.getCodigoStatus());
		ps.setInt(2, t.getCodigo());
		ps.execute();
		ps.close();
	}
	
	public List<Aluno> listaAlunosPorDisciplinaStatus(Disciplina d, int codigoStatus) throws SQLException{
		List<Aluno> lista = new ArrayList<Aluno>();
		String sql = "select distinct aluno.ra, aluno.nome from termo inner join aluno on aluno.ra = termo.ra_aluno inner join disciplina on termo.codigo_disciplina = disciplina.codigo inner join paises on paises.codigo = termo.codigo_pais inner join estado on estado.codigo = termo.codigo_status where termo.codigo_disciplina = ? AND termo.codigo_status = ? ORDER BY aluno.nome";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, d.getCodigo());
		ps.setInt(2, codigoStatus);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Aluno a = new Aluno();
			a.setRa(rs.getString("ra"));
			a.setNome(rs.getString("nome"));
			lista.add(a);
		}
		rs.close();
		ps.close();
		return lista;
	}

	public List<Termo> listaAssuntosPorAlunoDisciplinaStatus(Aluno a, Disciplina d, int codigoStatus) throws SQLException{
		List<Termo> lista = new ArrayList<Termo>();
		String sql = "select termo.codigo, aluno.ra, aluno.nome, termo.codigo_disciplina, disciplina.sigla as disciplina, termo.codigo_pais, paises.nome as pais, termo.assunto, termo.codigo_status, estado.estado from termo inner join aluno on aluno.ra = termo.ra_aluno inner join disciplina on termo.codigo_disciplina = disciplina.codigo inner join paises on paises.codigo = termo.codigo_pais inner join estado on estado.codigo = termo.codigo_status where termo.codigo_disciplina = ? AND aluno.ra = ? AND termo.codigo_status = ? ORDER BY paises.nome";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, d.getCodigo());
		ps.setString(2, a.getRa());
		ps.setInt(3, codigoStatus);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Termo t = new Termo();
			t.setCodigo(rs.getInt("codigo"));
			t.setRaAluno(rs.getString("ra"));
			t.setNomeAluno(rs.getString("nome"));
			t.setCodigoDisciplina(rs.getInt("codigo_disciplina"));
			t.setSiglaDisciplina(rs.getString("disciplina"));
			t.setCodigoPais(rs.getInt("codigo_pais"));
			t.setAssunto(rs.getString("assunto"));
			t.setNomePais(rs.getString("pais"));
			t.setCodigoStatus(rs.getInt("codigo_status"));
			t.setEstadoStatus(rs.getString("estado"));
			lista.add(t);
		}
		rs.close();
		ps.close();
		return lista;
	}

	public List<Termo> listaTermosPorAlunoDisciplinaStatus(Aluno a, Disciplina d, int codigoStatus) throws SQLException{
		List<Termo> lista = new ArrayList<Termo>();
		String sql = "select termo.codigo, aluno.ra, aluno.nome, termo.termo, termo.codigo_disciplina, disciplina.sigla as disciplina, termo.codigo_pais, paises.nome as pais, termo.assunto,	termo.termo, termo.codigo_status, estado.estado from termo inner join aluno on aluno.ra = termo.ra_aluno inner join disciplina on termo.codigo_disciplina = disciplina.codigo inner join paises on paises.codigo = termo.codigo_pais inner join estado on estado.codigo = termo.codigo_status where termo.codigo_disciplina = ? AND aluno.ra = ? AND termo.codigo_status = ? ORDER BY paises.nome";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, codigoStatus);
		ps.setString(2, a.getRa());
		ps.setInt(3, d.getCodigo());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Termo t = new Termo();
			t.setCodigo(rs.getInt("codigo"));
			t.setRaAluno(rs.getString("ra"));
			t.setNomeAluno(rs.getString("nome"));
			t.setCodigoDisciplina(rs.getInt("codigo_disciplina"));
			t.setSiglaDisciplina(rs.getString("disciplina"));
			t.setCodigoPais(rs.getInt("codigo_pais"));
			t.setAssunto(rs.getString("assunto"));
			t.setNomePais(rs.getString("pais"));
			t.setCodigoStatus(rs.getInt("codigo_status"));
			t.setEstadoStatus(rs.getString("estado"));
			t.setTexto(rs.getString("termo"));
			lista.add(t);
		}
		rs.close();
		ps.close();
		return lista;
	}
	
	public Termo consultaTermos(int codigoTermo) throws SQLException{
		Termo t = new Termo();
		String sql = "select termo.codigo, aluno.ra, aluno.nome, termo.codigo_disciplina, disciplina.sigla as disciplina, termo.codigo_pais, paises.nome as pais, termo.assunto,	termo.termo, termo.codigo_status, estado.estado from termo inner join aluno on aluno.ra = termo.ra_aluno inner join disciplina on termo.codigo_disciplina = disciplina.codigo inner join paises on paises.codigo = termo.codigo_pais inner join estado on estado.codigo = termo.codigo_status where termo.codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, codigoTermo);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			t.setCodigo(rs.getInt("codigo"));
			t.setRaAluno(rs.getString("ra"));
			t.setNomeAluno(rs.getString("nome"));
			t.setCodigoDisciplina(rs.getInt("codigo_disciplina"));
			t.setSiglaDisciplina(rs.getString("disciplina"));
			t.setCodigoPais(rs.getInt("codigo_pais"));
			t.setAssunto(rs.getString("assunto"));
			t.setNomePais(rs.getString("pais"));
			t.setCodigoStatus(rs.getInt("codigo_status"));
			t.setEstadoStatus(rs.getString("estado"));
			t.setTexto(rs.getString("termo"));
		}
		rs.close();
		ps.close();
		return t;
	}

	
}
