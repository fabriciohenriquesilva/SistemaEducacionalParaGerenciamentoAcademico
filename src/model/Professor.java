package model;

public class Professor extends Pessoa {

	private String titulacao;
	
	public Professor() { }
	
	public Professor(String nome, String cpf, String titulacao) {
		super();
		this.titulacao = titulacao;
	}

	public String getTitulacao() {
		return titulacao;
	}

	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}
	
	@Override
	public String toString() {
		return    "Nome      = " + this.getNome() + 
				"\nCPF       = " + this.getCpf() +
				"\nTitulação = " + titulacao;
	}
	
}
