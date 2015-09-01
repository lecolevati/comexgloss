package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Paises;
import model.Termo;

public class PaisesDao {

	private Connection c;

	public PaisesDao() {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	public List<Paises> consultaDisciplinas() throws SQLException{
		List<Paises> lista = new ArrayList<Paises>();
		String sql = "SELECT codigo, nome, capital FROM paises ORDER BY nome";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Paises p = new Paises();
			p.setCodigo(rs.getInt("codigo"));
			p.setNome(rs.getString("nome"));
			p.setCapital(rs.getString("capital"));
			lista.add(p);
		}
		return lista;
	}
	
	public Paises consultaPaisPorCodigo(Paises p) throws SQLException{
		String sql = "SELECT codigo, nome, capital FROM paises WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, p.getCodigo());
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			p.setCodigo(rs.getInt("codigo"));
			p.setNome(rs.getString("nome"));
			p.setCapital(rs.getString("capital"));
		}
		return p;
	}
	
	public List<Paises> consultaDisciplinasPorLetra(String letra) throws SQLException{
		List<Paises> lista = new ArrayList<Paises>();
		String sql = "SELECT codigo, nome, capital FROM paises WHERE nome LIKE ? ORDER BY nome";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, letra+"%");
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Paises p = new Paises();
			p.setCodigo(rs.getInt("codigo"));
			p.setNome(rs.getString("nome"));
			p.setCapital(rs.getString("capital"));
			lista.add(p);
		}
		return lista;
	}
	
	public Paises consultaPaisPorCodigoTermo(Termo t) throws SQLException{
		Paises p = new Paises();
		String sql = "SELECT p.codigo, p.nome, p.capital FROM paises p INNER JOIN termo t ON p.codigo = t.codigo_pais WHERE t.codigo_pais = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, t.getCodigoPais());
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			p.setCodigo(rs.getInt("codigo"));
			p.setNome(rs.getString("nome"));
			p.setCapital(rs.getString("capital"));
		}
		return p;
	}
	
}
