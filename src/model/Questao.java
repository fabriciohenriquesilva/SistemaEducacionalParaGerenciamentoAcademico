package model;

public class Questao {
	
	private String codigo;
	private String enunciado;
	private float valor;
	private Disciplina disciplina;
	
	public Questao() {}
	
	public Questao(String codigo, String enunciado, float valor, Disciplina disciplina) {
		this.codigo = codigo;
		this.enunciado = enunciado;
		this.valor = valor;
		this.disciplina = disciplina;
	}

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	} 
	
	@Override
	public String toString() {
		return    "Código                = " + codigo + 
				"\nEnunciado             = " + enunciado +
				"\nValor da Questão      = " + valor;
	}

}
