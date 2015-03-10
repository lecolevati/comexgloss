package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Disciplina;

public class DisciplinaDao {
	
	private Connection c;

	public DisciplinaDao() {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	public List<Disciplina> consultaDisciplinas() throws SQLException{
		List<Disciplina> lista = new ArrayList<Disciplina>();
		String sql = "SELECT codigo, nome, sigla FROM disciplina";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Disciplina d = new Disciplina();
			d.setCodigo(rs.getInt("codigo"));
			d.setNome(rs.getString("nome"));
			d.setSigla(rs.getString("sigla"));
			lista.add(d);
		}
		return lista;
	}
	
	public Disciplina consultaDisciplina(Disciplina disciplina) throws SQLException{
		Disciplina d = new Disciplina();
		String sql = "SELECT codigo, nome, sigla FROM disciplina where codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, disciplina.getCodigo());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			d.setCodigo(rs.getInt("codigo"));
			d.setNome(rs.getString("nome"));
			d.setSigla(rs.getString("sigla"));
		}
		return d;
	}
}
