package model;

public class Aluno {

	private String ra;
	private String nome;
	private int turno;
	
	public String getRa() {
		return ra;
	}
	public String getNome() {
		return nome;
	}
	public void setRa(String ra) {
		this.ra = ra;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getTurno() {
		return turno;
	}
	public void setTurno(int turno) {
		this.turno = turno;
	}
	@Override
	public String toString() {
		return this.nome;
	}

}
