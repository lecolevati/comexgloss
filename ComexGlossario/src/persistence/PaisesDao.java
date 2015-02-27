package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Paises;

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
	
}
