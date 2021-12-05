package main;

import java.util.Scanner;
import gerencia.GerenciaAluno;
import gerencia.GerenciaCurso;
import gerencia.GerenciaDisciplina;
import gerencia.GerenciaProfessor;
import gerencia.GerenciaProva;
import gerencia.GerenciaQuestao;
import gerencia.GerenciaTurma;

public class Principal {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int menu, operacao;
		
		GerenciaAluno ficharioAluno = new GerenciaAluno();
		GerenciaProfessor ficharioProfessor = new GerenciaProfessor();
		GerenciaQuestao ficharioQuestao = new GerenciaQuestao();
		GerenciaProva ficharioProva = new GerenciaProva();
		GerenciaDisciplina ficharioDisciplina = new GerenciaDisciplina();
		GerenciaTurma ficharioTurma = new GerenciaTurma();
		GerenciaCurso ficharioCurso = new GerenciaCurso();
		
		System.out.println("............................................................");
		System.out.println("BEM VINDO AO SEGA");
		System.out.println("SEU MELHOR SOFTWARE EDUCACIONAL PARA GERENCIAMENTO ACADÊMICO");
		System.out.println("............................................................\n");
		
		System.out.println("...:::::[ S E G A ]:::::...\n");
		
		do {
			System.out.println("============ MENU ============");
			System.out.println("Escolha um menu:");
			System.out.println("1. Aluno");
			System.out.println("2. Professor");
			System.out.println("3. Questão");
			System.out.println("4. Prova");
			System.out.println("5. Disciplina");
			System.out.println("6. Turma");
			System.out.println("7. Curso");
			System.out.println("0. Encerrar aplicação");
			System.out.println("Opção: ");
			
			menu =  sc.nextInt();
			sc.skip("\r\n");
			
			switch(menu) {
			
				case 1:
					menuDeOperacoes();
					System.out.println("6. Adicionar Prova");
					System.out.println("7. Remover Prova");
					System.out.println("8. Consultar Provas");
					System.out.println("9. Consultar turmas matriculadas");
					System.out.println("Operação: ");
					
					operacao = sc.nextInt();
					sc.skip("\r\n");
					
					switch(operacao) {
						case 1: ficharioAluno.adicionar(); break;
						case 2: ficharioAluno.consultar(); break;
						case 3:	ficharioAluno.alterar(); break;
						case 4:	ficharioAluno.remover(); break;
						case 5:	ficharioAluno.gerarRelatorio(); break;
						case 6: ficharioAluno.adicionarProva(); break;
						case 7: ficharioAluno.removerProva(); break;
						case 8: ficharioAluno.consultarProvas(); break;
						case 9: ficharioAluno.consultarTurmas(); break;
						case 0: break;
						default: System.out.println("ALERTA: Opção inválida!");	break;
					}
					break;
					
				case 2:
					menuDeOperacoes();
					System.out.println("Operação: ");
					
					operacao = sc.nextInt();
					sc.skip("\r\n");
					
					switch(operacao) {
						case 1: ficharioProfessor.adicionar(); break;
						case 2: ficharioProfessor.consultar(); break;
						case 3:	ficharioProfessor.alterar(); break;
						case 4:	ficharioProfessor.remover(); break;
						case 5:	ficharioProfessor.gerarRelatorio(); break;
						case 0: break;
						default: System.out.println("ALERTA: Opção inválida!");	break;
					}
					break;
					
				case 3: 
					menuDeOperacoes();
					System.out.println("6. Consultar provas");
					System.out.println("Operação: ");
					
					operacao = sc.nextInt();
					sc.skip("\r\n");
					
					switch(operacao) {
						case 1: ficharioQuestao.adicionar(); break;
						case 2: ficharioQuestao.consultar(); break;
						case 3:	ficharioQuestao.alterar(); break;
						case 4:	ficharioQuestao.remover(); break;
						case 5:	ficharioQuestao.gerarRelatorio(); break;
						case 6:	ficharioQuestao.consultarProvas(); break;
						case 0: break;
						default: System.out.println("ALERTA: Opção inválida!");	break;
					}
					break;
					
				case 4:
					menuDeOperacoes();
					System.out.println("6. Adicionar questão");
					System.out.println("7. Remover questão");
					System.out.println("8. Consultar questões");
					System.out.println("9. Consultar alunos que fizeram uma prova");
					System.out.println("Operação: ");
					
					operacao = sc.nextInt();
					sc.skip("\r\n");
					
					switch(operacao) {
						case 1: ficharioProva.adicionar(); break;
						case 2: ficharioProva.consultar(); break;
						case 3:	ficharioProva.alterar(); break;
						case 4:	ficharioProva.remover(); break;
						case 5:	ficharioProva.gerarRelatorio(); break;
						case 6: ficharioProva.adicionarQuestao(); break;
						case 7: ficharioProva.removerQuestao(); break;
						case 8: ficharioProva.consultarQuestoes(); break;
						case 9: ficharioProva.consultarAlunos(); break;
						case 0: break;
						default: System.out.println("ALERTA: Opção inválida!");	break;
					}
					break;
					
				case 5:
					menuDeOperacoes();
					System.out.println("6. Adicionar Prova");
					System.out.println("7. Remover Prova");
					System.out.println("8. Consultar Provas");
					System.out.println("Operação: ");
					
					operacao = sc.nextInt();
					sc.skip("\r\n");
					
					switch(operacao) {
						case 1: ficharioDisciplina.adicionar(); break;
						case 2: ficharioDisciplina.consultar(); break;
						case 3:	ficharioDisciplina.alterar(); break;
						case 4:	ficharioDisciplina.remover(); break;
						case 5:	ficharioDisciplina.gerarRelatorio(); break;
						case 6: ficharioDisciplina.adicionarProva(); break;
						case 7: ficharioDisciplina.removerProva(); break;
						case 8: ficharioDisciplina.consultarProvas(); break;
						case 0: break;
						default: System.out.println("ALERTA: Opção inválida!");	break;
					}
					break;
				
				case 6:
					menuDeOperacoes();
					System.out.println("6. Adicionar aluno");
					System.out.println("7. Remover aluno");
					System.out.println("8. Consultar alunos");
					System.out.println("Operação: ");
					
					operacao = sc.nextInt();
					sc.skip("\r\n");
					
					switch(operacao) {
						case 1: ficharioTurma.adicionar(); break;
						case 2: ficharioTurma.consultar(); break;
						case 3:	ficharioTurma.alterar(); break;
						case 4:	ficharioTurma.remover(); break;
						case 5:	ficharioTurma.gerarRelatorio(); break;
						case 6: ficharioTurma.adicionarAluno(); break;
						case 7: ficharioTurma.removerAluno(); break;
						case 8: ficharioTurma.consultarAlunos(); break;
						case 0: break;
						default: System.out.println("ALERTA: Opção inválida!");	break;
					}
					break;
				
				case 7:
					menuDeOperacoes();
					System.out.println("6. Adicionar disciplina");
					System.out.println("7. Remover disciplina");
					System.out.println("8. Consultar disciplinas");	
					System.out.println("Operação: ");
					
					operacao = sc.nextInt();
					sc.skip("\r\n");
					
					switch(operacao) {
						case 1: ficharioCurso.adicionar(); break;
						case 2: ficharioCurso.consultar(); break;
						case 3:	ficharioCurso.alterar(); break;
						case 4:	ficharioCurso.remover(); break;
						case 5:	ficharioCurso.gerarRelatorio(); break;
						case 6: ficharioCurso.adicionarDisciplina(); break;
						case 7: ficharioCurso.removerDisciplina(); break;
						case 8: ficharioCurso.consultarDisciplinas(); break;
						case 0: break;
						default: System.out.println("ALERTA: Opção inválida!");	break;
					}
					break;
					
				case 0: System.out.println("Encerrando aplicação..."); break;
				default: System.out.println("ALERTA: Opção inválida!");	break;
			}
			
		} while(menu != 0);
		
		sc.close();
	}

	public static void menuDeOperacoes() {
		
		System.out.println("============ OPERAÇÃO ============");
		System.out.println("Escolha uma opção:");
		System.out.println("0. Voltar ao menu inicial");
		System.out.println("1. Incluir");
		System.out.println("2. Consultar");
		System.out.println("3. Alterar");
		System.out.println("4. Excluir");
		System.out.println("5. Gerar relatório");
	}
}
