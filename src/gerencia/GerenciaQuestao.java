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
		System.out.println("CADASTRO DE QUEST�O");
		
		Questao questao = new Questao();
		
		lerDados(questao);
		
		if(questoes.add(questao)) {
			System.out.println("SUCESSO: Quest�o adicionada!");
		}
		else {
			System.out.println("ERRO: N�o foi poss�vel cadastrar a quest�o!");
		}
	}
	
	public void remover() {
		
		System.out.println("==============================");
		System.out.println("REMO��O DE QUEST�O");
		
		if(!questoes.isEmpty()) {
			
			System.out.println("Digite a posi��o da quest�o a ser removido: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < questoes.size()) {
				System.out.println("A quest�o que deseja remover � essa?");
				System.out.println("==============================");
				System.out.println(questoes.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					questoes.remove(pos);
					System.out.println("SUCESSO: Quest�o removida!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Opera��o de remo��o de quest�o cancelada!");
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
			System.out.println("AVISO: N�o h� quest�es cadastradas. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERA��O DE QUEST�ES");
		
		if(!questoes.isEmpty()) {
			System.out.println("Digite a posi��o da quest�o a ser alterada: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < questoes.size()) {
				System.out.println("A quest�o que deseja alterar � este?");
				System.out.println("==============================");
				System.out.println(questoes.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					Questao questao = questoes.get(pos);
					
					lerDados(questao);
					
					System.out.println("SUCESSO: Quest�o alterada!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Opera��o de altera��o de dados da quest�o cancelada! Voltando ao menu inicial...");
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
			System.out.println("AVISO: N�o h� quest�es cadastradas. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void consultar() {
		System.out.println("==============================");
		System.out.println("CONSULTA DE QUEST�O");
		
		if(!questoes.isEmpty()) {
			System.out.println("Digite a posi��o da quest�o a ser consultada: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < questoes.size()) {
				System.out.println("==============================");
				System.out.println(questoes.get(pos));
				System.out.println("==============================");
			}
			else {
				System.out.println("AVISO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: N�o h� quest�es cadastradas. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void gerarRelatorio() {
		
		System.out.println("==============================");
		System.out.println("RELAT�RIO DE QUEST�ES");
		
		if(!questoes.isEmpty()) {
			System.out.println("==============================");
			for (Questao questao : questoes) {
				System.out.println(questao);
				System.out.println("------------------------------");
			}
		}
		else {
			System.out.println("AVISO: N�o h� quest�es cadastradas. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	private void lerDados(Questao questao) {
		
		System.out.println("1. Digite o c�digo da quest�o: ");
		questao.setCodigo(sc.nextLine());
		
		System.out.println("2. Digite o enunciado da quest�o: ");
		questao.setEnunciado(sc.nextLine());
		
		System.out.println("3. Digite o valor da quest�o: ");
		questao.setValor(sc.nextFloat());
		sc.skip("\r\n");
		
	}
}
