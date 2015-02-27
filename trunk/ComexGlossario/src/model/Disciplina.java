package model;

public class Disciplina {

	private int codigo;
	private String nome;
	private String sigla;
	
	public int getCodigo() {
		return codigo;
	}
	public String getNome() {
		return nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome;
	}
	
}
