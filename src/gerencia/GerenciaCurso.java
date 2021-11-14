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
			System.out.println("ERRO: Não foi possível cadastrar o curso!");
		}
	}
	
	public void remover() {
		
		System.out.println("==============================");
		System.out.println("REMOÇÃO DE CURSO");
		
		if(!cursos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Digite a posição do curso a ser removido: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < cursos.size()) {
				System.out.println("O curso que deseja remover é esse?");
				System.out.println("==============================");
				System.out.println(cursos.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					cursos.remove(pos);
					System.out.println("SUCESSO: Curso removido!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Operação de remoção de cursos cancelada!");
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
			System.out.println("AVISO: Não há cursos cadastradas. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERAÇÃO DE CURSO");
		
		if(!cursos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Digite a posição do curso a ser alterado: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < cursos.size()) {
				System.out.println("O curso que deseja alterar é este?");
				System.out.println("==============================");
				System.out.println(cursos.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					Curso curso = cursos.get(pos);
					
					lerDados(curso);
					
					System.out.println("SUCESSO: Curso alterado!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Operação de alteração de dados do curso cancelada! Voltando ao menu inicial...");
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
			System.out.println("AVISO: Não há cursos cadastradas. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void consultar() {
		System.out.println("==============================");
		System.out.println("CONSULTA DE CURSOS");
		
		if(!cursos.isEmpty()) {
			System.out.println("Digite a posição do curso a ser consultado: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < cursos.size()) {
				System.out.println("==============================");
				System.out.println(cursos.get(pos));
				System.out.println("==============================");
			}
			else {
				System.out.println("AVISO: Posição informada não é válida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: Não há cursos cadastradas. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void gerarRelatorio() {
		
		System.out.println("==============================");
		System.out.println("RELATÓRIO DE CURSOS");
		
		if(!cursos.isEmpty()) {
			System.out.println("==============================");
			for (Curso curso : cursos) {
				System.out.println("Posição: #" + cursos.indexOf(curso));
				System.out.println(curso);
				System.out.println("------------------------------");
			}
		}
		else {
			System.out.println("AVISO: Não há cursos cadastradas. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void adicionarDisciplina() {
		System.out.println("==============================");
		System.out.println("ADICIONAR DISCIPLINA NO CURSO");
		
		if(!cursos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha o curso pela sua posição: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < cursos.size()) {
				Curso curso = cursos.get(pos);
				System.out.println("O curso que deseja adicionar a disciplina é este?");
				System.out.println("==============================");
				System.out.println(curso);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {					
					if(!disciplinas.isEmpty()) {
					
						System.out.println("==============================");
						System.out.println("BANCO DE DISCIPLINAS");
						for (Disciplina disc : disciplinas) {
							System.out.println("Posição: #" + disciplinas.indexOf(disc));
							System.out.println(disc);
							System.out.println("------------------------------");
						}
					
						System.out.println("Escolha a disciplina pela sua posição: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < disciplinas.size()) {
							Disciplina disciplina = disciplinas.get(pos);
							System.out.println("A disciplina que deseja adicionar no curso é esta?");
							System.out.println("==============================");
							System.out.println(disciplina);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] Não");
							
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
								System.out.println("AVISO: Opção inválida!");
							}
						}
						else {
							System.out.println("ERRO: Posição informada não é válida. Voltando ao menu inicial...");
						}
					}
					else {
						System.out.println("AVISO: Não há disciplinas cadastradas no BANCO DE DISCIPLINAS. Impossível continuar operação. Voltando ao menu inicial...");
					}
				}
				else if(opcao == 2) {
					System.out.println("AVISO: Voltando ao menu inicial...");
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
			System.out.println("AVISO: Não há curso cadastrados. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void removerDisciplina() {
		System.out.println("==============================");
		System.out.println("REMOVER DISCIPLINA DO CURSO");
		
		if(!cursos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha o curso pela sua posição: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < cursos.size()) {
				Curso curso = cursos.get(pos);
				System.out.println("O curso que deseja remover a disciplina é esse?");
				System.out.println("==============================");
				System.out.println(curso);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					ArrayList<Disciplina> disciplinasDoCurso = curso.getDisciplinas();
					
					if(!disciplinasDoCurso.isEmpty()) {
						
						System.out.println("==============================");
						for (Disciplina disc : disciplinasDoCurso) {
							System.out.println("Posição: #" + disciplinasDoCurso.indexOf(disc));
							System.out.println(disc);
							System.out.println("------------------------------");
						}

						System.out.println("Escolha a disciplina pela sua posição: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < disciplinasDoCurso.size()) {
							Disciplina disciplina = disciplinasDoCurso.get(pos);
							System.out.println("A disciplina que deseja remover do curso é essa?");
							System.out.println("==============================");
							System.out.println(disciplina);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] Não");
							
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
								System.out.println("AVISO: Opção inválida!");
							}
						}
						else {
							System.out.println("ERRO: Posição informada não é válida. Voltando ao menu inicial...");
						}
					}
					else {
						System.out.println("AVISO: Não há disciplinas cadastradas NESSE CURSO. Impossível continuar operação. Voltando ao menu inicial...");
					}
				}
				else if(opcao == 2) {
					System.out.println("AVISO: Voltando ao menu inicial...");
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
			System.out.println("AVISO: Não há cursos cadastrados. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void consultarDisciplinas() {
		System.out.println("==============================");
		System.out.println("CONSULTAR DISCIPLINAS DO CURSO");
		
		if(!cursos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha o curso pela sua posição: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < cursos.size()) {
				Curso curso = cursos.get(pos);
				System.out.println("O curso que deseja consultar as disciplinas é este?");
				System.out.println("==============================");
				System.out.println(curso);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					ArrayList<Disciplina> disciplinasDoCurso = curso.getDisciplinas();
					
					if(!disciplinasDoCurso.isEmpty()) {
						for(Disciplina disc : disciplinasDoCurso) {
							System.out.println("Posição " + disciplinasDoCurso.indexOf(disc));
							System.out.println(disc);
							System.out.println("------------------------------");
						}
					}
					else {
						System.out.println("AVISO: Não há disciplinas cadastradas NESSE CURSO. Impossível continuar operação. Voltando ao menu inicial...");
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
			System.out.println("AVISO: Não há cursos cadastrados. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}

	
	private void lerDados(Curso curso) {
		
		System.out.println("1. Digite o código do curso: ");
		curso.setCodigo(sc.nextLine());
		
		System.out.println("2. Digite o nome do curso: ");
		curso.setNome(sc.nextLine());	
		
	}
}
