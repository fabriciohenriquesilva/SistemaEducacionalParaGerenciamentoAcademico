package gerencia;

import java.util.ArrayList;
import java.util.Scanner;

import model.Questao;

public class GerenciaQuestao implements Gerencia {
	
	private ArrayList<Questao> questoes;
	private Scanner sc;
	
	public GerenciaQuestao(ArrayList<Questao> questoes) {
		sc = new Scanner(System.in);
		this.questoes = questoes;
	}
	
	public void adicionar() {
		
		System.out.println("==============================");
		System.out.println("CADASTRO DE QUESTÃO");
		
		Questao questao = new Questao();
		
		lerDados(questao);
		
		if(questoes.add(questao)) {
			System.out.println("SUCESSO: Questão adicionada!");
		}
		else {
			System.out.println("ERRO: Não foi possível cadastrar a questão!");
		}
	}
	
	public void remover() {
		
		System.out.println("==============================");
		System.out.println("REMOÇÃO DE QUESTÃO");
		
		if(!questoes.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Digite a posição da questão a ser removido: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < questoes.size()) {
				System.out.println("A questão que deseja remover é essa?");
				System.out.println("==============================");
				System.out.println(questoes.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					questoes.remove(pos);
					System.out.println("SUCESSO: Questão removida!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Operação de remoção de questão cancelada!");
				}
				else {
					System.out.println("ERRO: Opção inválida!");
				}
			}
			else {
				System.out.println("AVISO: Posição informada não é válida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: Não há questões cadastradas NO BANCO DE QUESTÕES. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERAÇÃO DE QUESTÕES");
		
		if(!questoes.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Digite a posição da questão a ser alterada: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < questoes.size()) {
				System.out.println("A questão que deseja alterar é este?");
				System.out.println("==============================");
				System.out.println(questoes.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					Questao questao = questoes.get(pos);
					
					lerDados(questao);
					
					System.out.println("SUCESSO: Questão alterada!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Operação de alteração de dados da questão cancelada! Voltando ao menu inicial...");
				}
				else {
					System.out.println("ERRO: Opção inválida!");
				}
			}
			else {
				System.out.println("AVISO: Posição informada não é válida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: Não há questões cadastradas NO BANCO DE QUESTÕES. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void consultar() {
		System.out.println("==============================");
		System.out.println("CONSULTA DE QUESTÃO");
		
		if(!questoes.isEmpty()) {
			System.out.println("Digite a posição da questão a ser consultada: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < questoes.size()) {
				System.out.println("==============================");
				System.out.println(questoes.get(pos));
				System.out.println("==============================");
			}
			else {
				System.out.println("AVISO: Posição informada não é válida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: Não há questões cadastradas NO BANCO DE QUESTÕES. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void gerarRelatorio() {
		
		System.out.println("==============================");
		System.out.println("RELATÓRIO DE QUESTÕES");
		
		if(!questoes.isEmpty()) {
			System.out.println("==============================");
			for (Questao questao : questoes) {
				System.out.println("Posição: #" + questoes.indexOf(questao));
				System.out.println(questao);
				System.out.println("------------------------------");
			}
		}
		else {
			System.out.println("AVISO: Não há questões cadastradas NO BANCO DE QUESTÕES. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	private void lerDados(Questao questao) {
		
		System.out.println("1. Digite o código da questão: ");
		questao.setCodigo(sc.nextLine());
		
		System.out.println("2. Digite o enunciado da questão: ");
		questao.setEnunciado(sc.nextLine());
		
		System.out.println("3. Digite o valor da questão: ");
		questao.setValor(sc.nextFloat());
		sc.skip("\r\n");
		
	}
}
