package model;

public class Entregues {

	private String ra;
	private String nome;
	private int aprovados;
	private int corrigir;
	private int aguardando;
	private int turno;
	
	public String getRa() {
		return ra;
	}
	public void setRa(String ra) {
		this.ra = ra;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getAprovados() {
		return aprovados;
	}
	public void setAprovados(int aprovados) {
		this.aprovados = aprovados;
	}
	public int getCorrigir() {
		return corrigir;
	}
	public void setCorrigir(int corrigir) {
		this.corrigir = corrigir;
	}
	public int getAguardando() {
		return aguardando;
	}
	public void setAguardando(int aguardando) {
		this.aguardando = aguardando;
	}
	public int getTurno() {
		return turno;
	}
	public void setTurno(int turno) {
		this.turno = turno;
	}
	
}
