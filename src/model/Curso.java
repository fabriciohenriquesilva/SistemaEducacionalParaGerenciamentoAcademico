package model;

import java.util.ArrayList;

public class Curso {

	private String codigo;
	private String nome;
	private ArrayList<Disciplina> disciplinas;
	
	public Curso(String codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public Curso() {
		disciplinas = new ArrayList<>();
	}

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

	public void adicionarDisciplina(Disciplina disciplina) {
		this.disciplinas.add(disciplina);
	}
	
	@Override
	public String toString() {
		return     "Código do Curso      = " + codigo +
				 "\nNome do Curso        = " + nome;
	}
}
