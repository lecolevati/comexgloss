package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Entregues;

public class ExcelDao {

	Connection c;

	public ExcelDao() {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	public List<Entregues> listaEntregues(int codigoDisciplina, int turno) throws SQLException {
		List<Entregues> lista = new ArrayList<Entregues>();
		String sql = "select ra, UPPER(nome) as nome, aprovados, corrigir, aguardando, turno from fn_listaentregas(?, ?) order by nome";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, codigoDisciplina);
		ps.setInt(2, turno);
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			Entregues e = new Entregues();
			e.setRa(rs.getString("ra"));
			e.setNome(rs.getString("nome"));
			e.setAprovados(rs.getInt("aprovados"));
			e.setCorrigir(rs.getInt("corrigir"));
			e.setAguardando(rs.getInt("aguardando"));
			e.setTurno(rs.getInt("turno"));
			lista.add(e);
		}
		rs.close();
		ps.close();
		return lista;
	}

}
