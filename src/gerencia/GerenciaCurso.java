package gerencia;

import java.util.ArrayList;
import java.util.Scanner;

import model.Curso;
import model.Disciplina;
import model.Questao;

public class GerenciaCurso implements Gerencia {

	private ArrayList<Curso> cursos;
	private ArrayList<Disciplina> disciplinas;
	private Scanner sc;
	
	public GerenciaCurso(ArrayList<Curso> cursos, ArrayList<Disciplina> disciplinas) {
		sc = new Scanner(System.in);
		this.cursos = cursos;
		this.disciplinas = disciplinas;
	}
	
	public void adicionar() {
		
		System.out.println("==============================");
		System.out.println("CADASTRO DE CURSO");
		
		Curso curso = new Curso();
		
		lerDados(curso);
		
		if(cursos.add(curso)) {
			System.out.println("SUCESSO: Curso adicionado!");
		}
		else {
			System.out.println("ERRO: N�o foi poss�vel cadastrar o curso!");
		}
	}
	
	public void remover() {
		
		System.out.println("==============================");
		System.out.println("REMO��O DE CURSO");
		
		if(!cursos.isEmpty()) {
			
			System.out.println("Digite a posi��o do curso a ser removido: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < cursos.size()) {
				System.out.println("O curso que deseja remover � esse?");
				System.out.println("==============================");
				System.out.println(cursos.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					cursos.remove(pos);
					System.out.println("SUCESSO: Curso removido!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Opera��o de remo��o de cursos cancelada!");
				}
				else {
					System.out.println("ERRO: Op��o inv�lida!");
				}
			}
			else {
				System.out.println("AVISO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: N�o h� cursos cadastradas. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERA��O DE CURSO");
		
		if(!cursos.isEmpty()) {
			System.out.println("Digite a posi��o do curso a ser alterado: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < cursos.size()) {
				System.out.println("O curso que deseja alterar � este?");
				System.out.println("==============================");
				System.out.println(cursos.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					Curso curso = cursos.get(pos);
					
					lerDados(curso);
					
					System.out.println("SUCESSO: Curso alterado!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Opera��o de altera��o de dados do curso cancelada! Voltando ao menu inicial...");
				}
				else {
					System.out.println("ERRO: Op��o inv�lida!");
				}
			}
			else {
				System.out.println("AVISO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: N�o h� cursos cadastradas. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void consultar() {
		System.out.println("==============================");
		System.out.println("CONSULTA DE CURSOS");
		
		if(!cursos.isEmpty()) {
			System.out.println("Digite a posi��o do curso a ser consultado: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < cursos.size()) {
				System.out.println("==============================");
				System.out.println(cursos.get(pos));
				System.out.println("==============================");
			}
			else {
				System.out.println("AVISO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: N�o h� cursos cadastradas. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void gerarRelatorio() {
		
		System.out.println("==============================");
		System.out.println("RELAT�RIO DE CURSOS");
		
		if(!cursos.isEmpty()) {
			System.out.println("==============================");
			for (Curso curso : cursos) {
				System.out.println(curso);
				System.out.println("------------------------------");
			}
		}
		else {
			System.out.println("AVISO: N�o h� cursos cadastradas. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	private void lerDados(Curso curso) {
		
		System.out.println("1. Digite o c�digo do curso: ");
		curso.setCodigo(sc.nextLine());
		
		System.out.println("2. Digite o nome do curso: ");
		curso.setNome(sc.nextLine());
		
		//System.out.println("3. Digite o valor do curso: ");
		//curso.setDisciplinas();
		
		
		
		
	}
}
