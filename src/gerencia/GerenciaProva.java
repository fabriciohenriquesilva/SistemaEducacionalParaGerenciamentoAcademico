package gerencia;

import java.util.ArrayList;
import java.util.Scanner;

import model.Prova;
import model.Questao;
import model.Turma;

public class GerenciaProva {
	
	private ArrayList<Prova> provas;
	private ArrayList<Questao> questoes;
	private ArrayList<Turma> turmas;
	private Scanner sc;
	
	public GerenciaProva(ArrayList<Prova> provas, ArrayList<Questao> questoes, ArrayList<Turma> turmas) {
		sc = new Scanner(System.in);
		
		this.provas = provas;
		this.questoes = questoes;
		this.turmas = turmas;
	}

	public void adicionar() {
		System.out.println("==============================");
		System.out.println("CADASTRO DE PROVA");
		
		if(turmas.isEmpty()) {
			System.out.println("AVISO: Não há turmas cadastradas. Necessário ter ao menos uma turma para cadastrar uma prova!");
			System.out.println("AVISO: Voltando ao menu inicial");
		}
		else {
			Prova prova = new Prova();
			
			lerDados(prova);
			
			if(provas.add(prova)) {
				System.out.println("SUCESSO: Prova adicionada!");
			}
			else {
				System.out.println("ERRO: Não foi possível adicionar a prova!");
			}
		}
	}

	public void remover() {
		System.out.println("==============================");
		System.out.println("REMOÇÃO DE PROVA");
		
		if(!provas.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Digite a posição da prova a ser removida: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < provas.size()) {
				Prova prova = provas.get(pos);
				System.out.println("A prova que deseja remover é essa?");
				System.out.println("==============================");
				System.out.println(prova);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					provas.remove(pos);
					System.out.println("SUCESSO: Prova removida!");
				}
				else if(opcao == 2) {
					System.out.println("AVISO: Voltando ao menu inicial...");
				}
				else {
					System.out.println("AVISO: Opção inválida!");
				}
			}
			else {
				System.out.println("AVISO: Posição informada não é válida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: Não há provas cadastradas NO BANCO DE PROVAS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}

	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERAÇÃO DE PROVA");
		
		if(!provas.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Digite a posição da prova a ser alterada: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < provas.size()) {
				System.out.println("A prova que deseja alterar é esta?");
				System.out.println("==============================");
				System.out.println(provas.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					Prova prova = provas.get(pos);
					
					lerDados(prova);
					
					System.out.println("SUCESSO: Prova alterada!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Operação de alteração de dados da prova cancelada! Voltando ao menu inicial...");
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
			System.out.println("AVISO: Não há provas cadastradas NO BANCO DE PROVAS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}

	public void consultar() {
		System.out.println("==============================");
		System.out.println("CONSULTA DE PROVA");
		
		if(!provas.isEmpty()) {
			System.out.println("Digite a posição da prova a ser consultada: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < provas.size()) {
				Prova prova = provas.get(pos);
				System.out.println("==============================");
				System.out.println(prova);
				
				for(Questao q : prova.getQuestoes()) {
					System.out.println("------------------------------");
					System.out.println(q);
				}
				System.out.println("==============================");
			}
			else {
				System.out.println("AVISO: Posição informada não é válida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: Não há provas cadastradas NO BANCO DE PROVAS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}

	public void gerarRelatorio() {
		System.out.println("==============================");
		System.out.println("RELATÓRIO DE PROVAS");
		
		if(!provas.isEmpty()) {
			System.out.println("==============================");
			for (Prova prova : provas) {
				System.out.println("Posição: #" + provas.indexOf(prova));
				System.out.println(prova);
				System.out.println("------------------------------");
			}
		}
		else {
			System.out.println("AVISO: Não há provas cadastradas NO BANCO DE PROVAS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void adicionarQuestao() {
		System.out.println("==============================");
		System.out.println("ADICIONAR QUESTÃO NA PROVA");
		
		if(!provas.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha a prova pela sua posição: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < provas.size()) {
				Prova prova = provas.get(pos);
				System.out.println("A prova que deseja adicionar questão é esta?");
				System.out.println("==============================");
				System.out.println(prova);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					if(!questoes.isEmpty()) {
						
						System.out.println("==============================");
						System.out.println("BANCO DE QUESTÕES");
						for (Questao questao : questoes) {
							System.out.println("Posição " + questoes.indexOf(questao));
							System.out.println(questao);
							System.out.println("------------------------------");
						}
						
						System.out.println("Escolha a questão pela sua posição: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < questoes.size()) {
							Questao questao = questoes.get(pos);
							System.out.println("A questão que deseja adicionar na prova é esta?");
							System.out.println("==============================");
							System.out.println(questao);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] Não");
							
							opcao = sc.nextInt();
							sc.skip("\r\n");
							
							if(opcao == 1) {
								prova.adicionarQuestao(questao);
								System.out.println("SUCESSO: Questão adicionada na prova!");
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
						System.out.println("AVISO: Não há questões cadastradas no BANCO DE QUESTÕES. Impossível continuar operação. Voltando ao menu inicial...");
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
			System.out.println("AVISO: Não há provas cadastradas NO BANCO DE PROVAS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void removerQuestao() {
		System.out.println("==============================");
		System.out.println("REMOVER QUESTÃO DA PROVA");
		
		if(!provas.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha a prova pela sua posição: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < provas.size()) {
				Prova prova = provas.get(pos);
				System.out.println("A prova que deseja remover a questão é esta?");
				System.out.println("==============================");
				System.out.println(prova);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					ArrayList<Questao> questoesDaProva = prova.getQuestoes(); 
				
					if(!questoesDaProva.isEmpty()) {
						
						System.out.println("==============================");
						for (Questao questao : questoesDaProva) {
							System.out.println("Posição " + questoesDaProva.indexOf(questao));
							System.out.println(questao);
							System.out.println("------------------------------");
						}
					
						System.out.println("Escolha a questão pela sua posição: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < questoesDaProva.size()) {
							Questao questao = questoesDaProva.get(pos);
							System.out.println("A questão que deseja remover da prova é esta?");
							System.out.println("==============================");
							System.out.println(questao);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] Não");
							
							opcao = sc.nextInt();
							sc.skip("\r\n");
							
							if(opcao == 1) {
								prova.getQuestoes().remove(pos);
								System.out.println("SUCESSO: Questão removida da prova!");
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
						System.out.println("AVISO: Não há questões cadastradas NESSA PROVA. Impossível continuar operação. Voltando ao menu inicial...");
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
			System.out.println("AVISO: Não há provas cadastradas NO BANCO DE PROVAS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void consultarQuestoes() {
		System.out.println("==============================");
		System.out.println("CONSULTAR QUESTÕES DA PROVAS");
		
		if(!provas.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha a prova pela sua posição: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < provas.size()) {
				Prova prova = provas.get(pos);
				System.out.println("A prova que deseja consultar as questões é esta?");
				System.out.println("==============================");
				System.out.println(prova);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					ArrayList<Questao> questoesDaProva = prova.getQuestoes();
					
					if(!questoesDaProva.isEmpty()) {
						for(Questao q : questoesDaProva) {
							System.out.println("------------------------------");
							System.out.println("Posição " + questoesDaProva.indexOf(q));
							System.out.println(q);
						}
						System.out.println("==============================");
					}
					else {
						System.out.println("AVISO: Não há questões cadastradas NESSA PROVA. Impossível continuar operação. Voltando ao menu inicial...");
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
			System.out.println("AVISO: Não há provas cadastradas NO BANCO DE PROVAS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	private void lerDados(Prova prova) {
		
		int pos;
		
		System.out.println("1. Digite o identificador da prova: ");
		prova.setIdentificador(sc.nextLine());
		
		System.out.println("2. Digite a nota da prova: ");
		prova.setNota(sc.nextFloat());
		sc.skip("\r\n");
		
		System.out.println("==============================");
		System.out.println("TURMAS CADASTRADAS NO SISTEMA: ");
		for (Turma turma : turmas) {
			System.out.println("------------------------------");
			System.out.println("Posição #" + turmas.indexOf(turma));
			System.out.println(turma);
			System.out.println("------------------------------");	
		}
		
		System.out.println("3. Digite a posição da turma para adicionar na prova: ");
		pos = sc.nextInt();
		sc.skip("\r\n");
		
		if(pos >= 0 && pos < turmas.size()) {
			prova.setTurma(turmas.get(pos));
			System.out.println("SUCESSO: Turma adicionada na prova!");
			
			System.out.println("==============================");
			System.out.println("QUESTÕES CADASTRADAS NO SISTEMA: ");
			System.out.println("4. Selecione as questões para adicionar na prova: ");
			for (Questao questao : questoes) {
				System.out.println("------------------------------");
				System.out.println("Posição #" + questoes.indexOf(questao));
				System.out.println(questao);
				System.out.println("------------------------------");
			}
			
			int opcao;
			do {
				System.out.println("Deseja adicionar uma questão na prova?");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					System.out.println("Digite a posição da questão para adicionar: ");
					pos = sc.nextInt();
					sc.skip("\r\n");
					
					if(pos >= 0 && pos < questoes.size()) {
						if(prova.adicionarQuestao(questoes.get(pos))) {
							System.out.println("SUCESSO: Questão adicionada na prova!");
						}
					}
					else {
						System.out.println("ERRO: Posição inválida!");
					}
				}
				else if(opcao == 2 && questoes.isEmpty()) {
					System.out.println("ERRO: Adicione ao menos uma questão na prova!");
				}
				else {
					if(opcao != 2) {
						System.out.println("ERRO: Opção inválida!");
					}
				}
			} while(opcao == 1);
			System.out.println("AVISO: Voltando ao menu inicial...");
		}
		else {
			System.out.println("ERRO: Posição inválida!");
		}
	}
}
