package model;

import java.util.ArrayList;

public class Prova {
	
	private String identificador;
	private Turma turma;
	private ArrayList<Questao> questoes;
	private float nota;
	private Disciplina disciplina;
	
	public Prova() {
		questoes = new ArrayList<>();
	}
	
	public Prova(String identificador, Turma turma, Disciplina disciplina) {
		this.identificador = identificador;
		this.turma = turma;
		this.disciplina = disciplina;
	}

	public String getIdentificador() {
		return identificador;
	}
	
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	public ArrayList<Questao> getQuestoes() {
		return questoes;
	}
	
	public boolean adicionarQuestao(Questao questao) {
		this.questoes.add(questao);
		return true;
	}

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	@Override
	public String toString() {
		return    "Identificador         = " + identificador +
				"\nTurma: " +
				"\n------------------------------\n" + turma +
				"\n------------------------------" +
				"\nNota                  = " + nota;
	}
	
}
