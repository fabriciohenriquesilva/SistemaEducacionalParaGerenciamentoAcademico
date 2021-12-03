package gerencia;

import java.util.ArrayList;
import java.util.Scanner;

import dao.CursoDAO;
import model.Curso;
import model.Disciplina;
import model.TipoRelatorio;

public class GerenciaCurso {

	private ArrayList<Curso> cursos;
	private ArrayList<Disciplina> disciplinas;
	private CursoDAO cursoDao;
	private Scanner sc;
	
	public GerenciaCurso(ArrayList<Curso> cursos, ArrayList<Disciplina> disciplinas) {
		sc = new Scanner(System.in);
		this.cursos = cursos;
		this.disciplinas = disciplinas;
		this.cursoDao = new CursoDAO();
	}
	
	public void adicionar() {
		
		System.out.println("==============================");
		System.out.println("CADASTRO DE CURSO");
		
		Curso curso = new Curso();
		
		System.out.println("1. Digite o código do curso: ");
		curso.setCodigo(sc.nextLine());
		
		System.out.println("2. Digite o nome do curso: ");
		curso.setNome(sc.nextLine());
		
		cursoDao.inserir(curso);
	}
	
	public void remover() {
		
		System.out.println("==============================");
		System.out.println("REMOÇÃO DE CURSO");
		
		String codigo;
		boolean achou = false;
		int opcao;
		
		cursos = cursoDao.relatorio();
		
		if(cursos != null && !cursos.isEmpty()) {
			imprimeCursos(cursos, TipoRelatorio.SINTETICO);
			System.out.println("Qual o código do curso que deseja remover?");
			codigo = sc.nextLine();
			
			Curso curso = null;
			for (Curso c : cursos) {
				if(c.getCodigo().equals(codigo)) {
					achou = true;
					curso = c;
				}
			}
			
			if(achou) {
				System.out.println("==============================");
				System.out.println(curso);
				System.out.println("==============================");
				
				System.out.println("Confirma a remoção?");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					cursoDao.excluir(curso);
					System.out.println("SUCESSO: Curso removido!");
				}
				else if(opcao == 2) {
					System.out.println("ERRO: Operação cancelada!");
				}
				else {
					System.out.println("AVISO: Opção inválida!");
				}
			}
			else {
				System.out.println("AVISO: Curso não encontrado!");
			}
		}
		else {
			System.out.println("AVISO: Não há cursos cadastrados. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERAÇÃO DE CURSO");
		
		String codigo;
		boolean achou = false;
		int opcao;

		cursos = cursoDao.relatorio();
		
		if(cursos != null && !cursos.isEmpty()) {
			imprimeCursos(cursos, TipoRelatorio.SINTETICO);
			System.out.println("Qual o código do curso que deseja alterar?");
			codigo = sc.nextLine();
			
			Curso curso = null;
			for (Curso c : cursos) {
				if(c.getCodigo().equals(codigo)) {
					achou = true;
					curso = c;
				}
			}
			
			if(achou) {
				System.out.println("==============================");
				System.out.println(curso);
				System.out.println("==============================");
				
				System.out.println("Confirma a alteração?");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					System.out.println("Digite os novos dados:");
					System.out.println("1. Digite o nome do curso: ");
					curso.setNome(sc.nextLine());
					
					cursoDao.alterar(curso);
					System.out.println("SUCESSO: Curso alterado!");
				}
				else if(opcao == 2) {
					System.out.println("ERRO: Operação cancelada!");
				}
				else {
					System.out.println("AVISO: Opção inválida!");
				}
			}
			else {
				System.out.println("AVISO: Curso não encontrado!");
			}
		}
		else {
			System.out.println("AVISO: Não há cursos cadastrados. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void consultar() {
		System.out.println("==============================");
		System.out.println("CONSULTA DE CURSOS");
		
		String codigo;
		boolean achou = false;

		cursos = cursoDao.relatorio();
		
		if(cursos != null && !cursos.isEmpty()) {
			imprimeCursos(cursos, TipoRelatorio.SINTETICO);
			System.out.println("Qual o código do curso que deseja consultar?");
			codigo = sc.nextLine();
			
			Curso curso = null;
			for (Curso c : cursos) {
				if(c.getCodigo().equals(codigo)) {
					achou = true;
					curso = c;
				}
			}
			
			if(achou) {
				System.out.println("==============================");
				System.out.println(curso);
				System.out.println("==============================");
			}
			else {
				System.out.println("AVISO: Curso não encontrado!");
			}
		}
		else {
			System.out.println("AVISO: Não há cursos cadastrados. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void gerarRelatorio() {
		
		System.out.println("==============================");
		System.out.println("RELATÓRIO DE CURSOS");
		
		cursos = cursoDao.relatorio();
		
		if(cursos != null && !cursos.isEmpty()) {
			imprimeCursos(cursos, TipoRelatorio.ANALITICO);
		}
		else {
			System.out.println("AVISO: Não há cursos cadastrados. Impossível continuar operação. Voltando ao menu inicial...");
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
			System.out.println("AVISO: Não há curso cadastrados NO BANCO DE CURSOS. Impossível continuar operação. Voltando ao menu inicial...");
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
			System.out.println("AVISO: Não há cursos cadastrados NO BANCO DE CURSOS. Impossível continuar operação. Voltando ao menu inicial...");
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
			System.out.println("AVISO: Não há cursos cadastrados NO BANCO DE CURSOS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}

	
	private void imprimeCursos(ArrayList<Curso> listaDeCursos, TipoRelatorio tipo) {
		System.out.println("...:::::[ LISTA DE PROFESSORES ]:::::...");
		
		if(tipo == TipoRelatorio.ANALITICO) {
			for(Curso curso : listaDeCursos) {
				System.out.println(curso);
				System.out.println("------------------------------");
			}
		}
		else {
			for(Curso curso : listaDeCursos) {
				System.out.println("Código: " + curso.getCodigo() + " - Nome: " + curso.getNome());
			}
			System.out.println("------------------------------");
		}
	}
}
