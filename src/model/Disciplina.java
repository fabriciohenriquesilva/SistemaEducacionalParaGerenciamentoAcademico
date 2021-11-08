package model;

import java.util.ArrayList;

public class Disciplina {
	
	private String codigo;
	private String nome;
	private ArrayList<Prova> provas;
	private String ementa;
	private int cargaHoraria;
	
	public Disciplina() {}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Prova> getProvas() {
		return provas;
	}

	public void adicionarProva(Prova prova) {
		this.provas.add(prova);
	}

	public String getEmenta() {
		return ementa;
	}

	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	@Override
	public String toString() {
		return     "C�digo da Disciplina = " + codigo +
				 "\nNome da Disciplina   = " + nome +
				 "\nCarga Hor�ria        = " + cargaHoraria +
				 "\nEmenta               = " + ementa;
	}

}
