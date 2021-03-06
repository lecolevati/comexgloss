package model;

public class Termo {

	private int codigo;
	private String raAluno;
	private int codigoDisciplina;
	private int codigoPais;
	private String assunto;
	private String texto;
	private int codigoStatus;
	private String siglaDisciplina;
	private String nomePais;
	private String estadoStatus;
	private String nomeAluno;
	private String comentarios;
	private String textoFonte;

	
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public int getCodigo() {
		return codigo;
	}
	public String getRaAluno() {
		return raAluno;
	}
	public int getCodigoDisciplina() {
		return codigoDisciplina;
	}
	public int getCodigoPais() {
		return codigoPais;
	}
	public String getAssunto() {
		return assunto;
	}
	public String getTexto() {
		return texto;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public void setRaAluno(String raAluno) {
		this.raAluno = raAluno;
	}
	public void setCodigoDisciplina(int codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}
	public void setCodigoPais(int codigoPais) {
		this.codigoPais = codigoPais;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public int getCodigoStatus() {
		return codigoStatus;
	}
	public void setCodigoStatus(int codigoStatus) {
		this.codigoStatus = codigoStatus;
	}
	public String getSiglaDisciplina() {
		return siglaDisciplina;
	}
	public String getNomePais() {
		return nomePais;
	}
	public String getEstadoStatus() {
		return estadoStatus;
	}
	public void setSiglaDisciplina(String siglaDisciplina) {
		this.siglaDisciplina = siglaDisciplina;
	}
	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}
	public void setEstadoStatus(String estadoStatus) {
		this.estadoStatus = estadoStatus;
	}
	
	public String getTextoFonte() {
		return textoFonte;
	}
	public void setTextoFonte(String textoFonte) {
		this.textoFonte = textoFonte;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return assunto;
	}
}
