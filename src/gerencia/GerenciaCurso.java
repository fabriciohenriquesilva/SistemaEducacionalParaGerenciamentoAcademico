package gerencia;

import java.util.ArrayList;
import java.util.Scanner;

import model.Curso;
import model.Disciplina;

public class GerenciaCurso implements Gerencia {

	private ArrayList<Curso> cursos;
	private ArrayList<Disciplina> disciplinas;
	private Scanner sc;
	
	public GerenciaCurso(ArrayList<Curso> cursos, ArrayList<Disciplina> disciplinas) {
		sc = new Scanner(System.in);
		this.cursos = cursos;
		this.disciplinas = disciplinas;
	}
	
	public void adicionar() {
		
		System.out.println("==============================");
		System.out.println("CADASTRO DE CURSO");
		
		Curso curso = new Curso();
		
		lerDados(curso);
		
		if(cursos.add(curso)) {
			System.out.println("SUCESSO: Curso adicionado!");
		}
		else {
			System.out.println("ERRO: N�o foi poss�vel cadastrar o curso!");
		}
	}
	
	public void remover() {
		
		System.out.println("==============================");
		System.out.println("REMO��O DE CURSO");
		
		if(!cursos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Digite a posi��o do curso a ser removido: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < cursos.size()) {
				System.out.println("O curso que deseja remover � esse?");
				System.out.println("==============================");
				System.out.println(cursos.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					cursos.remove(pos);
					System.out.println("SUCESSO: Curso removido!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Opera��o de remo��o de cursos cancelada!");
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
			System.out.println("AVISO: N�o h� cursos cadastradas NO BANCO DE CURSOS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERA��O DE CURSO");
		
		if(!cursos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Digite a posi��o do curso a ser alterado: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < cursos.size()) {
				System.out.println("O curso que deseja alterar � este?");
				System.out.println("==============================");
				System.out.println(cursos.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					Curso curso = cursos.get(pos);
					
					lerDados(curso);
					
					System.out.println("SUCESSO: Curso alterado!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Opera��o de altera��o de dados do curso cancelada! Voltando ao menu inicial...");
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
			System.out.println("AVISO: N�o h� cursos cadastradas NO BANCO DE CURSOS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void consultar() {
		System.out.println("==============================");
		System.out.println("CONSULTA DE CURSOS");
		
		if(!cursos.isEmpty()) {
			System.out.println("Digite a posi��o do curso a ser consultado: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < cursos.size()) {
				System.out.println("==============================");
				System.out.println(cursos.get(pos));
				System.out.println("==============================");
			}
			else {
				System.out.println("AVISO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: N�o h� cursos cadastradas NO BANCO DE CURSOS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void gerarRelatorio() {
		
		System.out.println("==============================");
		System.out.println("RELAT�RIO DE CURSOS");
		
		if(!cursos.isEmpty()) {
			System.out.println("==============================");
			for (Curso curso : cursos) {
				System.out.println("Posi��o: #" + cursos.indexOf(curso));
				System.out.println(curso);
				System.out.println("------------------------------");
			}
		}
		else {
			System.out.println("AVISO: N�o h� cursos cadastradas NO BANCO DE CURSOS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void adicionarDisciplina() {
		System.out.println("==============================");
		System.out.println("ADICIONAR DISCIPLINA NO CURSO");
		
		if(!cursos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha o curso pela sua posi��o: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < cursos.size()) {
				Curso curso = cursos.get(pos);
				System.out.println("O curso que deseja adicionar a disciplina � este?");
				System.out.println("==============================");
				System.out.println(curso);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {					
					if(!disciplinas.isEmpty()) {
					
						System.out.println("==============================");
						System.out.println("BANCO DE DISCIPLINAS");
						for (Disciplina disc : disciplinas) {
							System.out.println("Posi��o: #" + disciplinas.indexOf(disc));
							System.out.println(disc);
							System.out.println("------------------------------");
						}
					
						System.out.println("Escolha a disciplina pela sua posi��o: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < disciplinas.size()) {
							Disciplina disciplina = disciplinas.get(pos);
							System.out.println("A disciplina que deseja adicionar no curso � esta?");
							System.out.println("==============================");
							System.out.println(disciplina);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] N�o");
							
							opcao = sc.nextInt();
							sc.skip("\r\n");
							
							if(opcao == 1) {
								curso.adicionarDisciplina(disciplina);
								System.out.println("SUCESSO: Disciplina adicionada ao curso!");
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
						System.out.println("AVISO: N�o h� disciplinas cadastradas no BANCO DE DISCIPLINAS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
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
			System.out.println("AVISO: N�o h� curso cadastrados NO BANCO DE CURSOS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void removerDisciplina() {
		System.out.println("==============================");
		System.out.println("REMOVER DISCIPLINA DO CURSO");
		
		if(!cursos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha o curso pela sua posi��o: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < cursos.size()) {
				Curso curso = cursos.get(pos);
				System.out.println("O curso que deseja remover a disciplina � esse?");
				System.out.println("==============================");
				System.out.println(curso);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					ArrayList<Disciplina> disciplinasDoCurso = curso.getDisciplinas();
					
					if(!disciplinasDoCurso.isEmpty()) {
						
						System.out.println("==============================");
						for (Disciplina disc : disciplinasDoCurso) {
							System.out.println("Posi��o: #" + disciplinasDoCurso.indexOf(disc));
							System.out.println(disc);
							System.out.println("------------------------------");
						}

						System.out.println("Escolha a disciplina pela sua posi��o: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < disciplinasDoCurso.size()) {
							Disciplina disciplina = disciplinasDoCurso.get(pos);
							System.out.println("A disciplina que deseja remover do curso � essa?");
							System.out.println("==============================");
							System.out.println(disciplina);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] N�o");
							
							opcao = sc.nextInt();
							sc.skip("\r\n");
							
							if(opcao == 1) {
								disciplinasDoCurso.remove(pos);
								System.out.println("SUCESSO: Disciplina removida do curso!");
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
						System.out.println("AVISO: N�o h� disciplinas cadastradas NESSE CURSO. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
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
			System.out.println("AVISO: N�o h� cursos cadastrados NO BANCO DE CURSOS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void consultarDisciplinas() {
		System.out.println("==============================");
		System.out.println("CONSULTAR DISCIPLINAS DO CURSO");
		
		if(!cursos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha o curso pela sua posi��o: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < cursos.size()) {
				Curso curso = cursos.get(pos);
				System.out.println("O curso que deseja consultar as disciplinas � este?");
				System.out.println("==============================");
				System.out.println(curso);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					ArrayList<Disciplina> disciplinasDoCurso = curso.getDisciplinas();
					
					if(!disciplinasDoCurso.isEmpty()) {
						for(Disciplina disc : disciplinasDoCurso) {
							System.out.println("Posi��o " + disciplinasDoCurso.indexOf(disc));
							System.out.println(disc);
							System.out.println("------------------------------");
						}
					}
					else {
						System.out.println("AVISO: N�o h� disciplinas cadastradas NESSE CURSO. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
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
			System.out.println("AVISO: N�o h� cursos cadastrados NO BANCO DE CURSOS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}

	
	private void lerDados(Curso curso) {
		
		System.out.println("1. Digite o c�digo do curso: ");
		curso.setCodigo(sc.nextLine());
		
		System.out.println("2. Digite o nome do curso: ");
		curso.setNome(sc.nextLine());	
		
	}
}
