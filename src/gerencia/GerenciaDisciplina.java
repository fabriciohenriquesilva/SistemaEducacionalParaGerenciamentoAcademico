package gerencia;

import java.util.ArrayList;
import java.util.Scanner;

import model.Disciplina;
import model.Prova;
import model.Questao;

public class GerenciaDisciplina {

	private ArrayList<Disciplina> disciplinas;
	private ArrayList<Prova> provas;
	private Scanner sc;
	
	public GerenciaDisciplina(ArrayList<Disciplina> disciplinas, ArrayList<Prova> provas) {
		sc = new Scanner(System.in);
		this.disciplinas = disciplinas;
		this.provas = provas;
	}

	public void adicionar() {
		System.out.println("==============================");
		System.out.println("CADASTRO DE DISCIPLINA");
		
		Disciplina disciplina = new Disciplina();
		
		lerDados(disciplina);	
		
		if(disciplinas.add(disciplina)) {
			System.out.println("SUCESSO: Disciplina adicionada!");
		}
		else {
			System.out.println("ERRO: N�o foi poss�vel adicionar a disciplina!");
		}
	}

	public void remover() {
		System.out.println("==============================");
		System.out.println("REMO��O DE DISCIPLINA");
		
		if(!disciplinas.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Digite a posi��o da disciplina a ser removida: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < disciplinas.size()) {
				System.out.println("A disciplina que deseja remover � essa?");
				System.out.println("==============================");
				System.out.println(disciplinas.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					disciplinas.remove(pos);
					System.out.println("SUCESSO: Disciplina removida!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Opera��o de remo��o de disciplina cancelada!");
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
			System.out.println("AVISO: N�o h� disciplinas cadastradas NO BANCO DE DISCIPLINAS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERA��O DE DISCIPLINA");
		
		if(!disciplinas.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Digite a posi��o da disciplina a ser alterada: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < disciplinas.size()) {
				System.out.println("A disciplina que deseja alterar � este?");
				System.out.println("==============================");
				System.out.println(disciplinas.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					Disciplina disciplina = disciplinas.get(pos);
					
					lerDados(disciplina);
					
					System.out.println("SUCESSO: Disciplina alterada!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Opera��o de altera��o de dados da disciplina cancelada! Voltando ao menu inicial...");
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
			System.out.println("AVISO: N�o h� disciplinas cadastradas NO BANCO DE DISCIPLINAS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}

	public void consultar() {
		System.out.println("==============================");
		System.out.println("CONSULTA DE DISCIPLINAS");
		
		if(!disciplinas.isEmpty()) {
			System.out.println("Digite a posi��o da disciplina a ser consultada: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < disciplinas.size()) {
				System.out.println("==============================");
				System.out.println(disciplinas.get(pos));
				System.out.println("==============================");
			}
			else {
				System.out.println("AVISO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: N�o h� disciplinas cadastradas NO BANCO DE DISCIPLINAS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}

	public void gerarRelatorio() {
		System.out.println("==============================");
		System.out.println("RELAT�RIO DE DISCIPLINAS");
		
		if(!disciplinas.isEmpty()) {
			System.out.println("==============================");
			for (Disciplina disciplina : disciplinas) {
				System.out.println("Posi��o: #" + disciplinas.indexOf(disciplina));
				System.out.println(disciplina);
				System.out.println("------------------------------");
			}
		}
		else {
			System.out.println("AVISO: N�o h� disciplinas cadastradas NO BANCO DE DISCIPLINAS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void adicionarProva() {
		System.out.println("==============================");
		System.out.println("ADICIONAR PROVA DA DISCIPLINA");
		
		if(!disciplinas.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha a disciplina pela sua posi��o: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < disciplinas.size()) {
				Disciplina disciplina = disciplinas.get(pos);
				System.out.println("A disciplina que deseja adicionar a prova � esta?");
				System.out.println("==============================");
				System.out.println(disciplina);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					if(!provas.isEmpty()) {
					
						System.out.println("==============================");
						System.out.println("BANCO DE PROVAS");
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
							System.out.println("A prova que deseja adicionar na disciplina � essa?");
							System.out.println("==============================");
							System.out.println(prova);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] N�o");
							
							opcao = sc.nextInt();
							sc.skip("\r\n");
							
							if(opcao == 1) {
								disciplina.adicionarProva(prova);
								System.out.println("SUCESSO: Prova adicionada na disciplina!");
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
			System.out.println("AVISO: N�o h� disciplinas cadastradas NO BANCO DE DISCIPLINAS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void removerProva() {
		System.out.println("==============================");
		System.out.println("REMOVER PROVA DA DISCIPLINA");
		
		if(!disciplinas.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha a disciplina pela sua posi��o: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < disciplinas.size()) {
				Disciplina disciplina = disciplinas.get(pos);
				System.out.println("A disciplina que deseja remover a prova � esta?");
				System.out.println("==============================");
				System.out.println(disciplina);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					ArrayList<Prova> provasDaDisciplina = disciplina.getProvas();
					
					if(!provasDaDisciplina.isEmpty()) {
						
						System.out.println("==============================");
						for (Prova prova : provasDaDisciplina) {
							System.out.println("Posi��o: #" + provasDaDisciplina.indexOf(prova));
							System.out.println(prova);
							System.out.println("------------------------------");
						}
						
						System.out.println("Escolha a prova pela sua posi��o: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < provasDaDisciplina.size()) {
							Prova prova = provasDaDisciplina.get(pos);
							System.out.println("A prova que deseja remover da disciplina � essa?");
							System.out.println("==============================");
							System.out.println(prova);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] N�o");
							
							opcao = sc.nextInt();
							sc.skip("\r\n");
							
							if(opcao == 1) {
								provasDaDisciplina.remove(pos);
								System.out.println("SUCESSO: Prova removida da disciplina!");
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
						System.out.println("AVISO: N�o h� provas cadastradas para NESSA DISCIPLINA. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
					}
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
			System.out.println("AVISO: N�o h� disciplinas cadastrados NO BANCO DE DISCIPLINAS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void consultarProvas() {
		System.out.println("==============================");
		System.out.println("CONSULTAR PROVAS DA DISCIPLINA");
		
		if(!disciplinas.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha a disciplina pela sua posi��o: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < disciplinas.size()) {
				Disciplina disciplina = disciplinas.get(pos);
				System.out.println("A disciplina que deseja consultar as provas � este?");
				System.out.println("==============================");
				System.out.println(disciplina);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					ArrayList<Prova> provasDaDisciplina = disciplina.getProvas();
					
					if(!provasDaDisciplina.isEmpty()) {
						for(Prova p : provasDaDisciplina) {
							System.out.println("Posi��o " + provasDaDisciplina.indexOf(p));
							System.out.println(p);

							System.out.println("------------------------------");
							System.out.println("Quest�es: ");
							
							ArrayList<Questao> questoesDaProvaDaDisciplina = p.getQuestoes();
							
							for (Questao questao : questoesDaProvaDaDisciplina) {
								System.out.println("Posi��o " + questoesDaProvaDaDisciplina.indexOf(questao));
								System.out.println(questao);
								System.out.println("------------------------------");
							}
						}
						
					}
					else {
						System.out.println("AVISO: N�o h� provas cadastradas NESSA DISCIPLINA. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
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
			System.out.println("AVISO: N�o h� disciplinas cadastradas NO BANCO DE DISCIPLINAS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	private void lerDados(Disciplina disciplina) {
		
		System.out.println("1. Digite o c�digo da disciplina: ");
		disciplina.setCodigo(sc.nextLine());
		
		System.out.println("2. Digite o nome da disciplina: ");
		disciplina.setNome(sc.nextLine());
		
		System.out.println("3. Digite a ementa da disciplina: ");
		disciplina.setEmenta(sc.nextLine());
		
		System.out.println("4. Digite a carga hor�ria da disciplina (apenas n�meros inteiros): ");
		disciplina.setCargaHoraria(sc.nextInt());
		sc.skip("\r\n");
		
	}
}
