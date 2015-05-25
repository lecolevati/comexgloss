package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Aluno;

public class AlunoDao {

	private Connection c;
	
	public AlunoDao(){
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	public boolean validaAluno(Aluno a) throws SQLException{
		boolean retorno = false;
		String sql = "select * from aluno where ra = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, a.getRa());
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			retorno = true;
		}
		rs.close();
		ps.close();
		return retorno;
	}
	
	public void cadastraAluno(Aluno a) throws SQLException{
		String sql = "INSERT INTO aluno VALUES (?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, a.getRa());
		ps.setString(2, a.getNome());
		ps.execute();
		ps.close();
	}
	
	public Aluno consultaAluno(Aluno a) throws SQLException{
		Aluno aluno = new Aluno();
		String sql = "select * from aluno where ra = ?";
		if (a.getTurno() != 0){
			sql = sql + " and turno = ?";
		}
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, a.getRa());
		if (a.getTurno() != 0){
			ps.setInt(2, a.getTurno());
		}
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			aluno.setRa(rs.getString("ra"));
			aluno.setNome(rs.getString("nome"));
			if (a.getTurno() != 0){
				ps.setInt(3, a.getTurno());
			}
		}
		rs.close();
		ps.close();
		return aluno;
	}
	
}
