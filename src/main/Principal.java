package main;

import java.util.ArrayList;
import java.util.Scanner;

import gerencia.Gerencia;
import gerencia.GerenciaAluno;
import gerencia.GerenciaProfessor;
import gerencia.GerenciaProva;
import gerencia.GerenciaQuestao;
import model.Aluno;
import model.Avaliacao;
import model.Curso;
import model.Disciplina;
import model.Professor;
import model.Prova;
import model.Questao;
import model.Turma;

public class Principal {
	
	private static Scanner sc; // Posso fazer isso?

	public static void main(String[] args) {
		
		sc = new Scanner(System.in);
		int menu;
	
		ArrayList<Aluno> alunos = new ArrayList<>();
		ArrayList<Professor> professores = new ArrayList<>();
		ArrayList<Questao> questoes = new ArrayList<>();
		ArrayList<Avaliacao> avaliacoes = new ArrayList<>();
		ArrayList<Disciplina> disciplinas = new ArrayList<>();
		ArrayList<Prova> provas = new ArrayList<>();
		ArrayList<Curso> cursos = new ArrayList<>();
		ArrayList<Turma> turmas = new ArrayList<>();

		GerenciaProfessor ficharioProfessor = new GerenciaProfessor(professores);
		GerenciaAluno ficharioAluno = new GerenciaAluno(alunos);
		GerenciaProva ficharioProva = new GerenciaProva(provas, questoes, turmas);
		GerenciaQuestao ficharioQuestao = new GerenciaQuestao(questoes);
		
		do {
			System.out.println("============ MENU ============");
			System.out.println("Escolha um menu:");
			System.out.println("1. Aluno");
			System.out.println("2. Professor");
			System.out.println("3. Questão");
			System.out.println("4. Prova");
			System.out.println("5. Disciplina");
			System.out.println("6. Prova");
			System.out.println("7. Turma");
			System.out.println("8. Curso");
			System.out.println("0. Encerrar aplicação");
			System.out.println("Opção: ");
			
			menu =  sc.nextInt();
			sc.skip("\r\n");
			
			switch(menu) {
				case 1: operacao(ficharioAluno); break;
				case 2: operacao(ficharioProfessor); break;
				case 3: operacao(ficharioQuestao); break;
				case 4: operacao(ficharioProva); break;
				
				
				/*
				case 3:	fichario.alterar(); break;
				case 4:	fichario.remover(); break;
				case 5:	fichario.gerarRelatorio(); break;*/
				case 0: System.out.println("Encerrando aplicação..."); break;
				default: System.out.println("ALERTA: Opção inválida!");	break;
			}
			
		} while(menu != 0);
	}

	public static void operacao(Gerencia fichario) {
		
		System.out.println("============ OPERAÇÃO ============");
		System.out.println("Escolha uma opção:");
		System.out.println("1. Incluir");
		System.out.println("2. Consultar");
		System.out.println("3. Alterar");
		System.out.println("4. Excluir");
		System.out.println("5. Gerar relatório");
		System.out.println("0. Sair");
		System.out.println("Opção: ");
		
		int opcao = sc.nextInt();
		sc.skip("\r\n");
		
		switch(opcao) {
			case 1: fichario.adicionar(); break;
			case 2: fichario.consultar(); break;
			case 3:	fichario.alterar(); break;
			case 4:	fichario.remover(); break;
			case 5:	fichario.gerarRelatorio(); break;
			case 0: break;
			default: System.out.println("ALERTA: Opção inválida!");	break;
		}
	}
}
