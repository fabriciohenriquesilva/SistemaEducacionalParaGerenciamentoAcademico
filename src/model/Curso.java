package model;

import java.util.ArrayList;

public class Curso {

	private String codigo;
	private String nome;
	private ArrayList<Disciplina> disciplinas;
	
	public Curso() {}

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

	public ArrayList<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void addDisciplinas(Disciplina disciplina) {
		this.disciplinas.add(disciplina);
	}
	
	@Override
	public String toString() {
		return     "C�digo do Curso = " + codigo +
				 "\nNome do Curso   = " + nome;
	}
}
