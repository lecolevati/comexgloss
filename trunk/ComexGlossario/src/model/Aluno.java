package model;

public class Aluno {

	private String ra;
	private String nome;
	
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
	@Override
	public String toString() {
		return this.nome;
	}

}
