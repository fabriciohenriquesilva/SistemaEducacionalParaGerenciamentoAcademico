package model;

import java.util.ArrayList;

public class Avaliacao {
	
	private String identificador;
	private ArrayList<Questao> questoes;

	public Avaliacao() {}
	
	public String getIdentificador() {
		return identificador;
	}
	
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	
	public ArrayList<Questao> getQuestoes() {
		return questoes;
	}
	
	public void addQuestoes(Questao questao) {
		this.questoes.add(questao);
	}
	
	@Override
	public String toString() {
		return "Identificador da Prova = " + identificador;
	}
	
}
