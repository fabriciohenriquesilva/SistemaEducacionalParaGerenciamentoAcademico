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
			System.out.println("ERRO: N�o foi poss�vel cadastrar o aluno!");
		}
	}
	
	public void remover() {
		
		System.out.println("==============================");
		System.out.println("REMO��O DE ALUNOS");
		
		if(!alunos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Digite a posi��o do aluno a ser removido: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < alunos.size()) {
				System.out.println("O aluno que deseja remover � este?");
				System.out.println("==============================");
				System.out.println(alunos.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					alunos.remove(pos);
					System.out.println("SUCESSO: Aluno removido!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Opera��o de remo��o de aluno cancelada!");
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
			System.out.println("AVISO: N�o h� alunos cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERA��O DE ALUNOS");
		
		if(!alunos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Digite a posi��o do aluno a ser alterado: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < alunos.size()) {
				System.out.println("O aluno que deseja alterar � este?");
				System.out.println("==============================");
				System.out.println(alunos.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					Aluno aluno = alunos.get(pos);
					
					lerDados(aluno);
					
					System.out.println("SUCESSO: Aluno alterado!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Opera��o de altera��o de dados do aluno cancelada! Voltando ao menu inicial...");
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
			System.out.println("AVISO: N�o h� alunos cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void consultar() {
		System.out.println("==============================");
		System.out.println("CONSULTA DE ALUNOS");
		
		if(!alunos.isEmpty()) {
			System.out.println("Digite a posi��o do aluno a ser consultado: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < alunos.size()) {
				System.out.println("==============================");
				System.out.println(alunos.get(pos));
				System.out.println("==============================");
			}
			else {
				System.out.println("AVISO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: N�o h� aluno cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void gerarRelatorio() {
		
		System.out.println("==============================");
		System.out.println("RELAT�RIO DE ALUNOS");
		
		if(!alunos.isEmpty()) {
			System.out.println("==============================");
			for (Aluno aluno : alunos) {
				System.out.println("Posi��o: #" + alunos.indexOf(aluno));
				System.out.println(aluno);
				System.out.println("------------------------------");
			}
		}
		else {
			System.out.println("AVISO: N�o h� alunos cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void adicionarProva() {
		System.out.println("==============================");
		System.out.println("ADICIONAR PROVA DO ALUNO");
		
		if(!alunos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha o aluno pela sua posi��o: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < alunos.size()) {
				Aluno aluno = alunos.get(pos);
				System.out.println("O aluno que deseja adicionar a prova � este?");
				System.out.println("==============================");
				System.out.println(aluno);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(!provas.isEmpty()) {
					if(opcao == 1) {
						
						System.out.println("==============================");
						for (Prova prova : provas) {
							System.out.println("Posi��o: #" + provas.indexOf(prova));
							System.out.println(prova);
							System.out.println("------------------------------");
						}
						
						System.out.println("Escolha a prova pela sua posi��o: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < provas.size()) {
							Prova prova = provas.get(pos);
							System.out.println("A prova que deseja adicionar no aluno � esta?");
							System.out.println("==============================");
							System.out.println(prova);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] N�o");
							
							opcao = sc.nextInt();
							sc.skip("\r\n");
							
							if(opcao == 1) {
								aluno.adicionarProva(prova);
								System.out.println("SUCESSO: Prova adicionada ao aluno!");
							}
							else {
								System.out.println("AVISO: Opera��o cancelada!");
							}
						}
						else {
							System.out.println("ERRO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
						}
					}
					else if(opcao == 2) {
						System.out.println("AVISO: Voltando ao menu inicial...");
					}
					else {
						System.out.println("AVISO: Op��o inv�lida!");
					}
				}
				else {
					System.out.println("AVISO: N�o h� provas cadastradas no BANCO DE PROVAS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
				}
			}
			else {
				System.out.println("ERRO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: N�o h� alunos cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void removerProva() {
		System.out.println("==============================");
		System.out.println("REMOVER PROVA DO ALUNO");
		
		if(!alunos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha o aluno pela sua posi��o: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < alunos.size()) {
				Aluno aluno = alunos.get(pos);
				System.out.println("O aluno que deseja remover a prova � este?");
				System.out.println("==============================");
				System.out.println(aluno);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					ArrayList<Prova> provasDoAluno = aluno.getProvas();
					
					if(!provasDoAluno.isEmpty()) {
					
						System.out.println("==============================");
						for (Prova prova : provasDoAluno) {
							System.out.println("Posi��o: #" + provasDoAluno.indexOf(prova));
							System.out.println(prova);
							System.out.println("------------------------------");
						}
						
						System.out.println("Escolha a prova pela sua posi��o: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < provasDoAluno.size()) {
							Prova prova = provasDoAluno.get(pos);
							System.out.println("A prova que deseja remover do aluno � esta?");
							System.out.println("==============================");
							System.out.println(prova);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] N�o");
							
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
								System.out.println("AVISO: Opera��o cancelada!");
							}
						}
						else {
							System.out.println("ERRO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
						}
					}
					else {
						System.out.println("AVISO: N�o h� provas cadastradas para ESSE ALUNO. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
					}
				}
				else if(opcao == 2) {
					System.out.println("AVISO: Voltando ao menu inicial...");
				}
				else {
					System.out.println("AVISO: Op��o inv�lida!");
				}
			}
			else {
				System.out.println("ERRO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: N�o h� alunos cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void consultarProvas() {
		System.out.println("==============================");
		System.out.println("CONSULTAR PROVAS DO ALUNO");
		
		if(!alunos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha o aluno pela sua posi��o: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < alunos.size()) {
				Aluno aluno = alunos.get(pos);
				System.out.println("O aluno que deseja consultar as provas � este?");
				System.out.println("==============================");
				System.out.println(aluno);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					ArrayList<Prova> provasDoAluno = aluno.getProvas();
					
					if(!provasDoAluno.isEmpty()) {
						for(Prova p : provasDoAluno) {
							System.out.println("Posi��o " + provasDoAluno.indexOf(p));
							System.out.println(p);

							System.out.println("------------------------------");
							System.out.println("Quest�es: ");
							
							ArrayList<Questao> questoesDaProvaDoAluno = p.getQuestoes();
							
							for (Questao questao : questoesDaProvaDoAluno) {
								System.out.println("Posi��o " + questoesDaProvaDoAluno.indexOf(questao));
								System.out.println(questao);
								System.out.println("------------------------------");
							}
						}
						System.out.println("==============================");
					}
					else {
						System.out.println("AVISO: N�o h� provas cadastradas NESSE ALUNO. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
					}
				}
				else if(opcao == 2) {
					System.out.println("AVISO: Voltando ao menu inicial...");
				}
				else {
					System.out.println("AVISO: Op��o inv�lida!");
				}
			}
			else {
				System.out.println("ERRO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: N�o h� alunos cadastradas. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	private void lerDados(Aluno aluno) {
		
		System.out.println("1. Digite o nome do aluno: ");
		aluno.setNome(sc.nextLine());
		
		System.out.println("2. Digite o CPF do aluno: ");
		aluno.setCpf(sc.nextLine());
		
		System.out.println("3. Digite a matr�cula do aluno (somente n�meros): ");
		aluno.setMatricula(sc.nextInt());
		sc.skip("\r\n");
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("4. Digite a data da matr�cula (formato: dd/mm/yyyy): ");
		aluno.setDataMatricula(LocalDate.parse(sc.nextLine(), formato));
		
	}
}
