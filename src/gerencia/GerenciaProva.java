package gerencia;

import java.util.ArrayList;
import java.util.Scanner;

import model.Prova;
import model.Questao;
import model.Turma;

public class GerenciaProva implements Gerencia {
	
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
			System.out.println("AVISO: N�o h� turmas cadastradas. Necess�rio ter ao menos uma turma para cadastrar uma prova!");
			System.out.println("AVISO: Voltando ao menu inicial");
		}
		else {
			Prova prova = new Prova();
			
			lerDados(prova);
			
			if(provas.add(prova)) {
				System.out.println("SUCESSO: Prova adicionada!");
			}
			else {
				System.out.println("ERRO: N�o foi poss�vel adicionar a prova!");
			}
		}
	}

	public void remover() {
		System.out.println("==============================");
		System.out.println("REMO��O DE PROVA");
		
		if(!provas.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Digite a posi��o da prova a ser removida: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < provas.size()) {
				Prova prova = provas.get(pos);
				System.out.println("A prova que deseja remover � essa?");
				System.out.println("==============================");
				System.out.println(prova);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
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
					System.out.println("AVISO: Op��o inv�lida!");
				}
			}
			else {
				System.out.println("AVISO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: N�o h� provas cadastradas NO BANCO DE PROVAS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}

	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERA��O DE PROVA");
		
		if(!provas.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Digite a posi��o da prova a ser alterada: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < provas.size()) {
				System.out.println("A prova que deseja alterar � esta?");
				System.out.println("==============================");
				System.out.println(provas.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					Prova prova = provas.get(pos);
					
					lerDados(prova);
					
					System.out.println("SUCESSO: Prova alterada!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Opera��o de altera��o de dados da prova cancelada! Voltando ao menu inicial...");
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
			System.out.println("AVISO: N�o h� provas cadastradas NO BANCO DE PROVAS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}

	public void consultar() {
		System.out.println("==============================");
		System.out.println("CONSULTA DE PROVA");
		
		if(!provas.isEmpty()) {
			System.out.println("Digite a posi��o da prova a ser consultada: ");
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
				System.out.println("AVISO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: N�o h� provas cadastradas NO BANCO DE PROVAS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}

	public void gerarRelatorio() {
		System.out.println("==============================");
		System.out.println("RELAT�RIO DE PROVAS");
		
		if(!provas.isEmpty()) {
			System.out.println("==============================");
			for (Prova prova : provas) {
				System.out.println("Posi��o: #" + provas.indexOf(prova));
				System.out.println(prova);
				System.out.println("------------------------------");
			}
		}
		else {
			System.out.println("AVISO: N�o h� provas cadastradas NO BANCO DE PROVAS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void adicionarQuestao() {
		System.out.println("==============================");
		System.out.println("ADICIONAR QUEST�O NA PROVA");
		
		if(!provas.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha a prova pela sua posi��o: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < provas.size()) {
				Prova prova = provas.get(pos);
				System.out.println("A prova que deseja adicionar quest�o � esta?");
				System.out.println("==============================");
				System.out.println(prova);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					if(!questoes.isEmpty()) {
						
						System.out.println("==============================");
						System.out.println("BANCO DE QUEST�ES");
						for (Questao questao : questoes) {
							System.out.println("Posi��o " + questoes.indexOf(questao));
							System.out.println(questao);
							System.out.println("------------------------------");
						}
						
						System.out.println("Escolha a quest�o pela sua posi��o: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < questoes.size()) {
							Questao questao = questoes.get(pos);
							System.out.println("A quest�o que deseja adicionar na prova � esta?");
							System.out.println("==============================");
							System.out.println(questao);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] N�o");
							
							opcao = sc.nextInt();
							sc.skip("\r\n");
							
							if(opcao == 1) {
								prova.adicionarQuestao(questao);
								System.out.println("SUCESSO: Quest�o adicionada na prova!");
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
						System.out.println("AVISO: N�o h� quest�es cadastradas no BANCO DE QUEST�ES. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
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
			System.out.println("AVISO: N�o h� provas cadastradas NO BANCO DE PROVAS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void removerQuestao() {
		System.out.println("==============================");
		System.out.println("REMOVER QUEST�O DA PROVA");
		
		if(!provas.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha a prova pela sua posi��o: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < provas.size()) {
				Prova prova = provas.get(pos);
				System.out.println("A prova que deseja remover a quest�o � esta?");
				System.out.println("==============================");
				System.out.println(prova);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					ArrayList<Questao> questoesDaProva = prova.getQuestoes(); 
				
					if(!questoesDaProva.isEmpty()) {
						
						System.out.println("==============================");
						for (Questao questao : questoesDaProva) {
							System.out.println("Posi��o " + questoesDaProva.indexOf(questao));
							System.out.println(questao);
							System.out.println("------------------------------");
						}
					
						System.out.println("Escolha a quest�o pela sua posi��o: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < questoesDaProva.size()) {
							Questao questao = questoesDaProva.get(pos);
							System.out.println("A quest�o que deseja remover da prova � esta?");
							System.out.println("==============================");
							System.out.println(questao);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] N�o");
							
							opcao = sc.nextInt();
							sc.skip("\r\n");
							
							if(opcao == 1) {
								prova.getQuestoes().remove(pos);
								System.out.println("SUCESSO: Quest�o removida da prova!");
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
						System.out.println("AVISO: N�o h� quest�es cadastradas NESSA PROVA. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
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
			System.out.println("AVISO: N�o h� provas cadastradas NO BANCO DE PROVAS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void consultarQuestoes() {
		System.out.println("==============================");
		System.out.println("CONSULTAR QUEST�ES DA PROVAS");
		
		if(!provas.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha a prova pela sua posi��o: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < provas.size()) {
				Prova prova = provas.get(pos);
				System.out.println("A prova que deseja consultar as quest�es � esta?");
				System.out.println("==============================");
				System.out.println(prova);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					ArrayList<Questao> questoesDaProva = prova.getQuestoes();
					
					if(!questoesDaProva.isEmpty()) {
						for(Questao q : questoesDaProva) {
							System.out.println("------------------------------");
							System.out.println("Posi��o " + questoesDaProva.indexOf(q));
							System.out.println(q);
						}
						System.out.println("==============================");
					}
					else {
						System.out.println("AVISO: N�o h� quest�es cadastradas NESSA PROVA. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
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
			System.out.println("AVISO: N�o h� provas cadastradas NO BANCO DE PROVAS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
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
			System.out.println("Posi��o #" + turmas.indexOf(turma));
			System.out.println(turma);
			System.out.println("------------------------------");	
		}
		
		System.out.println("3. Digite a posi��o da turma para adicionar na prova: ");
		pos = sc.nextInt();
		sc.skip("\r\n");
		
		if(pos >= 0 && pos < turmas.size()) {
			prova.setTurma(turmas.get(pos));
			System.out.println("SUCESSO: Turma adicionada na prova!");
			
			System.out.println("==============================");
			System.out.println("QUEST�ES CADASTRADAS NO SISTEMA: ");
			System.out.println("4. Selecione as quest�es para adicionar na prova: ");
			for (Questao questao : questoes) {
				System.out.println("------------------------------");
				System.out.println("Posi��o #" + questoes.indexOf(questao));
				System.out.println(questao);
				System.out.println("------------------------------");
			}
			
			int opcao;
			do {
				System.out.println("Deseja adicionar uma quest�o na prova?");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					System.out.println("Digite a posi��o da quest�o para adicionar: ");
					pos = sc.nextInt();
					sc.skip("\r\n");
					
					if(pos >= 0 && pos < questoes.size()) {
						if(prova.adicionarQuestao(questoes.get(pos))) {
							System.out.println("SUCESSO: Quest�o adicionada na prova!");
						}
					}
					else {
						System.out.println("ERRO: Posi��o inv�lida!");
					}
				}
				else if(opcao == 2 && questoes.isEmpty()) {
					System.out.println("ERRO: Adicione ao menos uma quest�o na prova!");
				}
				else {
					if(opcao != 2) {
						System.out.println("ERRO: Op��o inv�lida!");
					}
				}
			} while(opcao == 1);
			System.out.println("AVISO: Voltando ao menu inicial...");
		}
		else {
			System.out.println("ERRO: Posi��o inv�lida!");
		}
	}
}
