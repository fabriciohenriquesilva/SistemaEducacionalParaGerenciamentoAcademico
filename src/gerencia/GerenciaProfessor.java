package gerencia;

import java.util.ArrayList;
import java.util.Scanner;

import dao.ProfessorDAO;
import model.Professor;
import model.TipoRelatorio;

public class GerenciaProfessor {
	
	private ArrayList<Professor> professores;
	private ProfessorDAO professorDao;
	private Scanner sc;
	
	public GerenciaProfessor() {
		this.professores = null;
		this.professorDao = new ProfessorDAO();
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
		
		professorDao.inserir(prof);
		System.out.println("SUCESSO: Professor adicionado!");
	}
	
	public void remover() {
		
		System.out.println("==============================");
		System.out.println("REMOÇÃO DE PROFESSORES");
		
		int codigo;
		boolean achou = false;
		int opcao;
		
		professores = professorDao.relatorio();
		
		if(professores != null && !professores.isEmpty()) {
			imprimeProfessores(professores, TipoRelatorio.SINTETICO);
			System.out.println("Qual o código do professor que deseja remover?");
			codigo = sc.nextInt();
			sc.skip("\r\n");
			
			Professor prof = null;
			for (Professor p : professores) {
				if(p.getCodigo() == codigo) {
					achou = true;
					prof = p;
				}
			}
			
			if(achou) {
				System.out.println("==============================");
				System.out.println(prof);
				System.out.println("==============================");
				
				System.out.println("Confirma a remoção?");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					professorDao.excluir(prof);
					System.out.println("SUCESSO: Professor removido!");
				}
				else if(opcao == 2) {
					System.out.println("ERRO: Operação cancelada!");
				}
				else {
					System.out.println("AVISO: Opção inválida!");
				}
			}
			else {
				System.out.println("AVISO: Professor não encontrado!");
			}
		}
		else {
			System.out.println("AVISO: Não há professores cadastrados NO BANCO DE PROFESSORES. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}

	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERAÇÃO DE PROFESSORES");
		
		int codigo;
		boolean achou = false;
		int opcao;

		professores = professorDao.relatorio();
		
		if(professores != null && !professores.isEmpty()) {
			imprimeProfessores(professores, TipoRelatorio.SINTETICO);
			System.out.println("Qual o código do professor que deseja alterar?");
			codigo = sc.nextInt();
			sc.skip("\r\n");
			
			Professor prof = null;
			for (Professor p : professores) {
				if(p.getCodigo() ==  codigo) {
					achou = true;
					prof = p;
				}
			}
			
			if(achou) {
				System.out.println("==============================");
				System.out.println(prof);
				System.out.println("==============================");
				
				System.out.println("Confirma a alteração?");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					System.out.println("Digite os novos dados:");
					System.out.println("1. Digite o nome do professor: ");
					prof.setNome(sc.nextLine());
					
					System.out.println("2. Digite o CPF do professor: ");
					prof.setCpf(sc.nextLine());
					
					System.out.println("3. Digite a titulação do professor: ");
					prof.setTitulacao(sc.nextLine());
					
					professorDao.alterar(prof);
					System.out.println("SUCESSO: Professor alterado!");
				}
				else if(opcao == 2) {
					System.out.println("ERRO: Operação cancelada!");
				}
				else {
					System.out.println("AVISO: Opção inválida!");
				}
			}
			else {
				System.out.println("AVISO: Professor não encontrado!");
			}
		}
		else {
			System.out.println("AVISO: Não há professores cadastrados NO BANCO DE PROFESSORES. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void consultar() {
		System.out.println("==============================");
		System.out.println("CONSULTA DE PROFESSORES");
		
		int codigo;
		boolean achou = false;

		professores = professorDao.relatorio();
		
		if(professores != null && !professores.isEmpty()) {
			imprimeProfessores(professores, TipoRelatorio.SINTETICO);
			System.out.println("Qual o código do professor que deseja consultar?");
			codigo = sc.nextInt();
			sc.skip("\r\n");
			
			Professor prof = null;
			for (Professor p : professores) {
				if(p.getCodigo() ==  codigo) {
					achou = true;
					prof = p;
				}
			}
			
			if(achou) {
				System.out.println("==============================");
				System.out.println(prof);
				System.out.println("==============================");
			}
			else {
				System.out.println("AVISO: Professor não encontrado!");
			}
		}
		else {
			System.out.println("AVISO: Não há professores cadastrados NO BANCO DE PROFESSORES. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void gerarRelatorio() {
		
		System.out.println("==============================");
		System.out.println("RELATÓRIO DE PROFESSORES");
		
		professores = professorDao.relatorio();
		
		if(professores != null && !professores.isEmpty()) {
			imprimeProfessores(professores, TipoRelatorio.ANALITICO);
		}
		else {
			System.out.println("AVISO: Não há professores cadastrados NO BANCO DE PROFESSORES. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	private void imprimeProfessores(ArrayList<Professor> listaDeProfessores, TipoRelatorio tipo) {
		System.out.println("...:::::[ LISTA DE PROFESSORES ]:::::...");
		
		if(tipo == TipoRelatorio.ANALITICO) {
			for(Professor prof : professores) {
				System.out.println(prof);
				System.out.println("------------------------------");
			}
		}
		else {
			for(Professor prof : professores) {
				System.out.println("Código: " + prof.getCodigo() + " - Nome: " + prof.getNome());
			}
			System.out.println("------------------------------");
		}
	}
}
