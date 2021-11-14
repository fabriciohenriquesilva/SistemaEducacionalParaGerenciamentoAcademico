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
		
		System.out.println("3. Digite a titulação do professor: ");
		prof.setTitulacao(sc.nextLine());
		
		if(professores.add(prof)) {
			System.out.println("SUCESSO: Professor adicionado!");
		}
		else {
			System.out.println("ERRO: Não foi possível cadastrar o professor!");
		}
	}
	
	public void remover() {
		
		System.out.println("==============================");
		System.out.println("REMOÇÃO DE PROFESSORES");
		
		if(!professores.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Digite a posição do professor a ser removido: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < professores.size()) {
				System.out.println("O professor que deseja remover é este?");
				System.out.println("==============================");
				System.out.println(professores.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					professores.remove(pos);
					System.out.println("SUCESSO: Professor removido!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Operação de remoção de professor cancelada!");
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
			System.out.println("AVISO: Não há professores cadastrados. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERAÇÃO DE PROFESSORES");
		
		if(!professores.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Digite a posição do professor a ser alterado: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < professores.size()) {
				System.out.println("O professor que deseja alterar é este?");
				System.out.println("==============================");
				System.out.println(professores.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					Professor prof = professores.get(pos);
					
					System.out.println("1. Digite o nome do professor: ");
					prof.setNome(sc.nextLine());
					
					System.out.println("2. Digite o CPF do professor: ");
					prof.setCpf(sc.nextLine());
					
					System.out.println("3. Digite a titulação do professor: ");
					prof.setTitulacao(sc.nextLine());
					
					System.out.println("SUCESSO: Professor alterado!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Operação de alteração de dados do professor cancelada! Voltando ao menu inicial...");
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
			System.out.println("AVISO: Não há professores cadastrados. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void consultar() {
		System.out.println("==============================");
		System.out.println("CONSULTA DE PROFESSORES");
		
		if(!professores.isEmpty()) {
			System.out.println("Digite a posição do professor a ser consultado: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < professores.size()) {
				System.out.println("==============================");
				System.out.println(professores.get(pos));
				System.out.println("==============================");
			}
			else {
				System.out.println("AVISO: Posição informada não é válida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: Não há professores cadastrados. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void gerarRelatorio() {
		
		System.out.println("==============================");
		System.out.println("RELATÓRIO DE PROFESSORES");
		
		if(!professores.isEmpty()) {
			System.out.println("==============================");
			for (Professor professor : professores) {
				System.out.println("Posição: #" + professores.indexOf(professor));
				System.out.println(professor);
				System.out.println("------------------------------");
			}
		}
		else {
			System.out.println("AVISO: Não há professores cadastrados. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
}
