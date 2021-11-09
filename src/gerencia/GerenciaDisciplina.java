package gerencia;

import java.util.ArrayList;
import java.util.Scanner;

import model.Disciplina;
import model.Prova;
import model.Questao;

public class GerenciaDisciplina implements Gerencia {

	private ArrayList<Disciplina> disciplinas;
	private ArrayList<Prova> provas;
	private Scanner sc;
	
	public GerenciaDisciplina(ArrayList<Disciplina> disciplinas, ArrayList<Prova> provas) {
		sc = new Scanner(System.in);
		this.disciplinas = disciplinas;
		this.provas = provas;
	}

	@Override
	public void adicionar() {
		System.out.println("==============================");
		System.out.println("CADASTRO DE DISCIPLINA");
		
		Disciplina disciplina = new Disciplina();
		
		lerDados(disciplina);	
		
		if(disciplinas.add(disciplina)) {
			System.out.println("SUCESSO: Disciplina adicionada!");
		}
		else {
			System.out.println("ERRO: Não foi possível adicionar a disciplina!");
		}
	}

	@Override
	public void remover() {
		System.out.println("==============================");
		System.out.println("REMOÇÃO DE DISCIPLINA");
		
		if(!disciplinas.isEmpty()) {
			
			System.out.println("Digite a posição da disciplina a ser removida: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < disciplinas.size()) {
				System.out.println("A disciplina que deseja remover é essa?");
				System.out.println("==============================");
				System.out.println(disciplinas.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					disciplinas.remove(pos);
					System.out.println("SUCESSO: Disciplina removida!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Operação de remoção de disciplina cancelada!");
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
			System.out.println("AVISO: Não há disciplinas cadastradas. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	@Override
	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERAÇÃO DE DISCIPLINA");
		
		if(!disciplinas.isEmpty()) {
			System.out.println("Digite a posição da disciplina a ser alterada: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < disciplinas.size()) {
				System.out.println("A disciplina que deseja alterar é este?");
				System.out.println("==============================");
				System.out.println(disciplinas.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					Disciplina disciplina = disciplinas.get(pos);
					
					lerDados(disciplina);
					
					System.out.println("SUCESSO: Disciplina alterada!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Operação de alteração de dados da disciplina cancelada! Voltando ao menu inicial...");
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
			System.out.println("AVISO: Não há disciplinas cadastradas. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}

	@Override
	public void consultar() {
		System.out.println("==============================");
		System.out.println("CONSULTA DE DISCIPLINAS");
		
		if(!disciplinas.isEmpty()) {
			System.out.println("Digite a posição da disciplina a ser consultada: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < disciplinas.size()) {
				System.out.println("==============================");
				System.out.println(disciplinas.get(pos));
				System.out.println("==============================");
			}
			else {
				System.out.println("AVISO: Posição informada não é válida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: Não há disciplinas cadastradas. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}

	@Override
	public void gerarRelatorio() {
		System.out.println("==============================");
		System.out.println("RELATÓRIO DE DISCIPLINAS");
		
		if(!disciplinas.isEmpty()) {
			System.out.println("==============================");
			for (Disciplina disciplina : disciplinas) {
				System.out.println(disciplina);
				System.out.println("------------------------------");
			}
		}
		else {
			System.out.println("AVISO: Não há disciplinas cadastradas. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void adicionarProva() {
		System.out.println("==============================");
		System.out.println("ADICIONAR PROVA DA DISCIPLINA");
		
		if(!disciplinas.isEmpty()) {
			System.out.println("Escolha a disciplina pela sua posição: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < disciplinas.size()) {
				Disciplina disciplina = disciplinas.get(pos);
				System.out.println("A disciplina que deseja adicionar a prova é esta?");
				System.out.println("==============================");
				System.out.println(disciplina);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(!provas.isEmpty()) {
					if(opcao == 1) {
						System.out.println("Escolha a prova pela sua posição: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < provas.size()) {
							Prova prova = provas.get(pos);
							System.out.println("A prova que deseja adicionar na disciplina é essa?");
							System.out.println("==============================");
							System.out.println(prova);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] Não");
							
							opcao = sc.nextInt();
							sc.skip("\r\n");
							
							if(opcao == 1) {
								disciplina.adicionarProva(prova);
								System.out.println("SUCESSO: Prova adicionada na disciplina!");
							}
							else {
								System.out.println("AVISO: Operação cancelada!");
							}
						}
						else {
							System.out.println("ERRO: Posição informada não é válida. Voltando ao menu inicial...");
						}
					}
					else {
						System.out.println("AVISO: Operação cancelada!");
					}
				}
				else {
					System.out.println("AVISO: Não há provas cadastradas. Impossível continuar operação. Voltando ao menu inicial...");
				}
			}
			else {
				System.out.println("ERRO: Posição informada não é válida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: Não há disciplinas cadastradas. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void removerProva() {
		System.out.println("==============================");
		System.out.println("REMOVER PROVA DA DISCIPLINA");
		
		if(!disciplinas.isEmpty()) {
			System.out.println("Escolha a disciplina pela sua posição: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < disciplinas.size()) {
				Disciplina disciplina = disciplinas.get(pos);
				System.out.println("A disciplina que deseja remover a prova é esta?");
				System.out.println("==============================");
				System.out.println(disciplina);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					ArrayList<Prova> provasDaDisciplina = disciplina.getProvas();
					
					if(!provasDaDisciplina.isEmpty()) {
						System.out.println("Escolha a prova pela sua posição: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < provasDaDisciplina.size()) {
							Prova prova = provasDaDisciplina.get(pos);
							System.out.println("A prova que deseja remover da disciplina é essa?");
							System.out.println("==============================");
							System.out.println(prova);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] Não");
							
							opcao = sc.nextInt();
							sc.skip("\r\n");
							
							if(opcao == 1) {
								provasDaDisciplina.remove(pos);
								System.out.println("SUCESSO: Prova removida da disciplina!");
							}
							else {
								System.out.println("AVISO: Operação cancelada!");
							}
						}
						else {
							System.out.println("ERRO: Posição informada não é válida. Voltando ao menu inicial...");
						}
					}
					else {
						System.out.println("AVISO: Não há provas cadastradas para NESSA DISCIPLINA. Impossível continuar operação. Voltando ao menu inicial...");
					}
				}
				else {
					System.out.println("AVISO: Operação cancelada!");
				}
			}
			else {
				System.out.println("ERRO: Posição informada não é válida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: Não há disciplinas cadastrados. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void consultarProvas() {
		System.out.println("==============================");
		System.out.println("CONSULTAR PROVAS DA DISCIPLINA");
		
		if(!disciplinas.isEmpty()) {
			System.out.println("Escolha a disciplina pela sua posição: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < disciplinas.size()) {
				Disciplina disciplina = disciplinas.get(pos);
				System.out.println("A disciplina que deseja consultar as provas é este?");
				System.out.println("==============================");
				System.out.println(disciplina);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					ArrayList<Prova> provasDaDisciplina = disciplina.getProvas();
					
					if(!provasDaDisciplina.isEmpty()) {
						if(pos >= 0 && pos < provasDaDisciplina.size()) {
							for(Prova p : provasDaDisciplina) {
								System.out.println("------------------------------");
								System.out.println("Posição " + provasDaDisciplina.indexOf(p));
								System.out.println(p);

								System.out.println("------------------------------");
								System.out.println("Questões: ");
								
								for (Questao questao : p.getQuestoes()) {
									System.out.println(questao);
									System.out.println("------------------------------");
								}
							}
							System.out.println("==============================");
						}
						else {
							System.out.println("ERRO: Posição informada não é válida. Voltando ao menu inicial...");
						}
					}
					else {
						System.out.println("AVISO: Não há provas cadastradas NESSA DISCIPLINA. Impossível continuar operação. Voltando ao menu inicial...");
					}
				}
				else {
					System.out.println("AVISO: Operação cancelada!");
				}
			}
			else {
				System.out.println("ERRO: Posição informada não é válida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: Não há disciplinas cadastradas. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	private void lerDados(Disciplina disciplina) {
		
		System.out.println("1. Digite o código da disciplina: ");
		disciplina.setCodigo(sc.nextLine());
		
		System.out.println("2. Digite o nome da disciplina: ");
		disciplina.setNome(sc.nextLine());
		
		System.out.println("3. Digite a ementa da disciplina: ");
		disciplina.setEmenta(sc.nextLine());
		
		System.out.println("4. Digite a carga horária da disciplina: ");
		disciplina.setCargaHoraria(sc.nextInt());
		sc.skip("\r\n");
		
	}
}
