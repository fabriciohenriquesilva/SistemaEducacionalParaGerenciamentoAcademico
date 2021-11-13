package model;

import java.util.ArrayList;

public class Turma {
	
	private Curso curso;
	private Professor professor;
	private ArrayList<Aluno> alunos;
	private Disciplina disciplina;
	private int ano;
	private int semestre;
	
	public Turma() {
		alunos = new ArrayList<>();
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public ArrayList<Aluno> getAlunos() {
		return alunos;
	}

	public void adicionarAluno(Aluno aluno) {
		this.alunos.add(aluno);
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	
	@Override
	public String toString() {
		return     "Curso: " +
				 "\n------------------------------\n" + curso +
				 "\n------------------------------" +
				 "\nProfessor: " +
				 "\n------------------------------\n" + professor +
				 "\n------------------------------" +
				 "\nDisciplina: " +
				 "\n------------------------------\n" + disciplina +
				 "\n------------------------------" +
				 "\nAno                  = " + ano +
				 "\nSemestre             = " + semestre;
	}
	
}
