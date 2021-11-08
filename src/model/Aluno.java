package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Aluno extends Pessoa {
	
	private int matricula;
	private LocalDate dataMatricula;
	private ArrayList<Prova> provas;
	
	public Aluno() {
		super();
	}

	public Aluno(int matricula, LocalDate dataMatricula, ArrayList<Prova> provas) {
		super();
		this.matricula = matricula;
		this.dataMatricula = dataMatricula;
		this.provas = provas;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public LocalDate getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(LocalDate dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public ArrayList<Prova> getProvas() {
		return provas;
	}

	public void adicionarProva(Prova prova) {
		this.provas.add(prova);
	}
	
	@Override
	public String toString() {
		return    "Nome              = " + this.getNome() +
				"\nCPF               = " + this.getCpf() +
				"\nMatrícula         = " + matricula +
				"\nData da Matrícula = " + dataMatricula;
	}
	
}
