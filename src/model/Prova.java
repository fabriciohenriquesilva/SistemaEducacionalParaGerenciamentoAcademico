package model;

import java.util.ArrayList;

public class Prova {
	
	private String identificador;
	private Turma turma;
	private ArrayList<Questao> questoes;
	private float nota;
	
	public Prova() {
		questoes = new ArrayList<>();
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
	
	@Override
	public String toString() {
		return    "Identificador = " + identificador +
				"\nTurma         = " + turma +
				"\nNota          = " + nota;
	}
	
}
