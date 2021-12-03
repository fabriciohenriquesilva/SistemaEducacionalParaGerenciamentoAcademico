package model;

public class Professor extends Pessoa {

	private int codigo;
	private String titulacao;
	
	public Professor() { }
	
	public Professor(int codigo, String nome, String cpf, String titulacao) {
		super(nome, cpf);
		this.titulacao = titulacao;
		this.codigo = codigo;
	}

	public String getTitulacao() {
		return titulacao;
	}

	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public String toString() {
		return    "Código                = " + codigo +
				"\nNome                  = " + this.getNome() + 
				"\nCPF                   = " + this.getCpf() +
				"\nTitulação             = " + titulacao;
	}
	
}
