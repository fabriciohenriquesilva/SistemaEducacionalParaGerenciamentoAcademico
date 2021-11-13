package model;

public class Questao {
	
	private String codigo;
	private String enunciado;
	private float valor;
	
	public Questao() {}
	
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
	
	@Override
	public String toString() {
		return    "Código                = " + codigo + 
				"\nEnunciado             = " + enunciado +
				"\nValor da Questão      = " + valor;
	}

}
