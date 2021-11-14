package gerencia;

import java.util.ArrayList;
import java.util.Scanner;

import model.Professor;

public class GerenciaProfessor implements Gerencia {
	
	private ArrayList<Professor> professores;
	private Scanner sc;
	
	public GerenciaProfessor(ArrayList<Professor> professores) {
		this.professores = professores;
		sc = new Scanner(System.in);
	}
	
	public void adicionar() {
		
		System.out.println("==============================");
		System.out.println("CADASTRO DE PROFESSORES");
		
		Professor prof = new Professor();
		
		System.out.println("1. Digite o nome do professor: ");
		prof.setNome(sc.nextLine());
		
		System.out.println("2. Digite o CPF do professor: ");
		prof.setCpf(sc.nextLine());
		
		System.out.println("3. Digite a titula��o do professor: ");
		prof.setTitulacao(sc.nextLine());
		
		if(professores.add(prof)) {
			System.out.println("SUCESSO: Professor adicionado!");
		}
		else {
			System.out.println("ERRO: N�o foi poss�vel cadastrar o professor!");
		}
	}
	
	public void remover() {
		
		System.out.println("==============================");
		System.out.println("REMO��O DE PROFESSORES");
		
		if(!professores.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Digite a posi��o do professor a ser removido: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < professores.size()) {
				System.out.println("O professor que deseja remover � este?");
				System.out.println("==============================");
				System.out.println(professores.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					professores.remove(pos);
					System.out.println("SUCESSO: Professor removido!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Opera��o de remo��o de professor cancelada!");
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
			System.out.println("AVISO: N�o h� professores cadastrados NO BANCO DE PROFESSORES. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERA��O DE PROFESSORES");
		
		if(!professores.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Digite a posi��o do professor a ser alterado: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < professores.size()) {
				System.out.println("O professor que deseja alterar � este?");
				System.out.println("==============================");
				System.out.println(professores.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					Professor prof = professores.get(pos);
					
					System.out.println("1. Digite o nome do professor: ");
					prof.setNome(sc.nextLine());
					
					System.out.println("2. Digite o CPF do professor: ");
					prof.setCpf(sc.nextLine());
					
					System.out.println("3. Digite a titula��o do professor: ");
					prof.setTitulacao(sc.nextLine());
					
					System.out.println("SUCESSO: Professor alterado!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Opera��o de altera��o de dados do professor cancelada! Voltando ao menu inicial...");
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
			System.out.println("AVISO: N�o h� professores cadastrados NO BANCO DE PROFESSORES. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void consultar() {
		System.out.println("==============================");
		System.out.println("CONSULTA DE PROFESSORES");
		
		if(!professores.isEmpty()) {
			System.out.println("Digite a posi��o do professor a ser consultado: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < professores.size()) {
				System.out.println("==============================");
				System.out.println(professores.get(pos));
				System.out.println("==============================");
			}
			else {
				System.out.println("AVISO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: N�o h� professores cadastrados NO BANCO DE PROFESSORES. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void gerarRelatorio() {
		
		System.out.println("==============================");
		System.out.println("RELAT�RIO DE PROFESSORES");
		
		if(!professores.isEmpty()) {
			System.out.println("==============================");
			for (Professor professor : professores) {
				System.out.println("Posi��o: #" + professores.indexOf(professor));
				System.out.println(professor);
				System.out.println("------------------------------");
			}
		}
		else {
			System.out.println("AVISO: N�o h� professores cadastrados NO BANCO DE PROFESSORES. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
}
