package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Aluno extends Pessoa {
	
	private String matricula;
	private LocalDate dataMatricula;
	private ArrayList<Prova> provas;
	
	public Aluno() {
		provas = new ArrayList<>();
	}

	public Aluno(String matricula, String nome, String cpf, LocalDate dataMatricula) {
		super(nome, cpf);
		this.matricula = matricula;
		this.dataMatricula = dataMatricula;
		provas = new ArrayList<>();
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
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
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return    "Nome                  = " + this.getNome() +
				"\nCPF                   = " + this.getCpf() +
				"\nMatrícula             = " + matricula +
				"\nData da Matrícula     = " + dataMatricula.format(formato);
	}
	
}
