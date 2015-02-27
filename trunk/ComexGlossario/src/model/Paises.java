package model;

public class Paises {

	private int codigo;
	private String nome;
	private String capital;
	
	public int getCodigo() {
		return codigo;
	}
	public String getNome() {
		return nome;
	}
	public String getCapital() {
		return capital;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome;
	}

	
}
