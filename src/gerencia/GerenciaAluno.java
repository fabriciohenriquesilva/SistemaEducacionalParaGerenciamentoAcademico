package gerencia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import model.Aluno;
import model.Prova;
import model.Questao;

public class GerenciaAluno implements Gerencia {

	private ArrayList<Aluno> alunos;
	private ArrayList<Prova> provas;
	private Scanner sc;
	
	public GerenciaAluno(ArrayList<Aluno> alunos, ArrayList<Prova> provas) {
		this.alunos = alunos;
		this.provas = provas;
		sc = new Scanner(System.in);
	}
	
	public void adicionar() {
		
		System.out.println("==============================");
		System.out.println("CADASTRO DE ALUNOS");
		
		Aluno aluno = new Aluno();
		
		lerDados(aluno);
		
		if(alunos.add(aluno)) {
			System.out.println("SUCESSO: Aluno adicionado!");
		}
		else {
			System.out.println("ERRO: Não foi possível cadastrar o aluno!");
		}
	}
	
	public void remover() {
		
		System.out.println("==============================");
		System.out.println("REMOÇÃO DE ALUNOS");
		
		if(!alunos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Digite a posição do aluno a ser removido: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < alunos.size()) {
				System.out.println("O aluno que deseja remover é este?");
				System.out.println("==============================");
				System.out.println(alunos.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					alunos.remove(pos);
					System.out.println("SUCESSO: Aluno removido!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Operação de remoção de aluno cancelada!");
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
			System.out.println("AVISO: Não há alunos cadastrados NO BANCO DE ALUNOS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERAÇÃO DE ALUNOS");
		
		if(!alunos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Digite a posição do aluno a ser alterado: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < alunos.size()) {
				System.out.println("O aluno que deseja alterar é este?");
				System.out.println("==============================");
				System.out.println(alunos.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					Aluno aluno = alunos.get(pos);
					
					lerDados(aluno);
					
					System.out.println("SUCESSO: Aluno alterado!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Operação de alteração de dados do aluno cancelada! Voltando ao menu inicial...");
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
			System.out.println("AVISO: Não há alunos cadastrados NO BANCO DE ALUNOS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void consultar() {
		System.out.println("==============================");
		System.out.println("CONSULTA DE ALUNOS");
		
		if(!alunos.isEmpty()) {
			System.out.println("Digite a posição do aluno a ser consultado: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < alunos.size()) {
				System.out.println("==============================");
				System.out.println(alunos.get(pos));
				System.out.println("==============================");
			}
			else {
				System.out.println("AVISO: Posição informada não é válida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: Não há aluno cadastrados NO BANCO DE ALUNOS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void gerarRelatorio() {
		
		System.out.println("==============================");
		System.out.println("RELATÓRIO DE ALUNOS");
		
		if(!alunos.isEmpty()) {
			System.out.println("==============================");
			for (Aluno aluno : alunos) {
				System.out.println("Posição: #" + alunos.indexOf(aluno));
				System.out.println(aluno);
				System.out.println("------------------------------");
			}
		}
		else {
			System.out.println("AVISO: Não há alunos cadastrados NO BANCO DE ALUNOS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void adicionarProva() {
		System.out.println("==============================");
		System.out.println("ADICIONAR PROVA DO ALUNO");
		
		if(!alunos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha o aluno pela sua posição: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < alunos.size()) {
				Aluno aluno = alunos.get(pos);
				System.out.println("O aluno que deseja adicionar a prova é este?");
				System.out.println("==============================");
				System.out.println(aluno);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					if(!provas.isEmpty()) {
						
						System.out.println("==============================");
						System.out.println("BANCO DE PROVAS");
						for (Prova prova : provas) {
							System.out.println("Posição: #" + provas.indexOf(prova));
							System.out.println(prova);
							System.out.println("------------------------------");
						}
						
						System.out.println("Escolha a prova pela sua posição: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < provas.size()) {
							Prova prova = provas.get(pos);
							System.out.println("A prova que deseja adicionar no aluno é esta?");
							System.out.println("==============================");
							System.out.println(prova);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] Não");
							
							opcao = sc.nextInt();
							sc.skip("\r\n");
							
							if(opcao == 1) {
								aluno.adicionarProva(prova);
								System.out.println("SUCESSO: Prova adicionada ao aluno!");
							}
							else if(opcao == 2) {
								System.out.println("AVISO: Voltando ao menu inicial...");
							}
							else {
								System.out.println("AVISO: Opção invpalida!");
							}
						}
						else {
							System.out.println("ERRO: Posição informada não é válida. Voltando ao menu inicial...");
						}
					}
					else {
						System.out.println("AVISO: Não há provas cadastradas no BANCO DE PROVAS. Impossível continuar operação. Voltando ao menu inicial...");
					}
				}
				else if(opcao == 2) {
					System.out.println("AVISO: Voltando ao menu inicial...");
				}
				else {
					System.out.println("AVISO: Opção inválida!");
				}
			}
			else {
				System.out.println("ERRO: Posição informada não é válida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: Não há alunos cadastrados NO BANCO DE ALUNOS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void removerProva() {
		System.out.println("==============================");
		System.out.println("REMOVER PROVA DO ALUNO");
		
		if(!alunos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha o aluno pela sua posição: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < alunos.size()) {
				Aluno aluno = alunos.get(pos);
				System.out.println("O aluno que deseja remover a prova é este?");
				System.out.println("==============================");
				System.out.println(aluno);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					ArrayList<Prova> provasDoAluno = aluno.getProvas();
					
					if(!provasDoAluno.isEmpty()) {
					
						System.out.println("==============================");
						for (Prova prova : provasDoAluno) {
							System.out.println("Posição: #" + provasDoAluno.indexOf(prova));
							System.out.println(prova);
							System.out.println("------------------------------");
						}
						
						System.out.println("Escolha a prova pela sua posição: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < provasDoAluno.size()) {
							Prova prova = provasDoAluno.get(pos);
							System.out.println("A prova que deseja remover do aluno é esta?");
							System.out.println("==============================");
							System.out.println(prova);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] Não");
							
							opcao = sc.nextInt();
							sc.skip("\r\n");
							
							if(opcao == 1) {
								aluno.getProvas().remove(pos);
								System.out.println("SUCESSO: Prova removida do aluno!");
							}
							else if(opcao == 2) {
								System.out.println("AVISO: Voltando ao menu inicial...");
							}
							else {
								System.out.println("AVISO: Opção inválida!");
							}
						}
						else {
							System.out.println("ERRO: Posição informada não é válida. Voltando ao menu inicial...");
						}
					}
					else {
						System.out.println("AVISO: Não há provas cadastradas para ESSE ALUNO. Impossível continuar operação. Voltando ao menu inicial...");
					}
				}
				else if(opcao == 2) {
					System.out.println("AVISO: Voltando ao menu inicial...");
				}
				else {
					System.out.println("AVISO: Opção inválida!");
				}
			}
			else {
				System.out.println("ERRO: Posição informada não é válida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: Não há alunos cadastrados NO BANCO DE ALUNOS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void consultarProvas() {
		System.out.println("==============================");
		System.out.println("CONSULTAR PROVAS DO ALUNO");
		
		if(!alunos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha o aluno pela sua posição: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < alunos.size()) {
				Aluno aluno = alunos.get(pos);
				System.out.println("O aluno que deseja consultar as provas é este?");
				System.out.println("==============================");
				System.out.println(aluno);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					ArrayList<Prova> provasDoAluno = aluno.getProvas();
					
					if(!provasDoAluno.isEmpty()) {
						for(Prova p : provasDoAluno) {
							System.out.println("Posição " + provasDoAluno.indexOf(p));
							System.out.println(p);

							System.out.println("------------------------------");
							System.out.println("Questões: ");
							
							ArrayList<Questao> questoesDaProvaDoAluno = p.getQuestoes();
							
							for (Questao questao : questoesDaProvaDoAluno) {
								System.out.println("Posição " + questoesDaProvaDoAluno.indexOf(questao));
								System.out.println(questao);
								System.out.println("------------------------------");
							}
						}
						System.out.println("==============================");
					}
					else {
						System.out.println("AVISO: Não há provas cadastradas NESSE ALUNO. Impossível continuar operação. Voltando ao menu inicial...");
					}
				}
				else if(opcao == 2) {
					System.out.println("AVISO: Voltando ao menu inicial...");
				}
				else {
					System.out.println("AVISO: Opção inválida!");
				}
			}
			else {
				System.out.println("ERRO: Posição informada não é válida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: Não há alunos cadastradas NO BANCO DE ALUNOS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	private void lerDados(Aluno aluno) {
		
		System.out.println("1. Digite o nome do aluno: ");
		aluno.setNome(sc.nextLine());
		
		System.out.println("2. Digite o CPF do aluno: ");
		aluno.setCpf(sc.nextLine());
		
		System.out.println("3. Digite a matrícula do aluno (somente números): ");
		aluno.setMatricula(sc.nextInt());
		sc.skip("\r\n");
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("4. Digite a data da matrícula (formato: dd/mm/yyyy): ");
		aluno.setDataMatricula(LocalDate.parse(sc.nextLine(), formato));
		
	}
}
