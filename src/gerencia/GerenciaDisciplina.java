package gerencia;

import java.util.ArrayList;
import java.util.Scanner;

import dao.CursoDAO;
import dao.DisciplinaDAO;
import model.Curso;
import model.Disciplina;
import model.Prova;
import model.Questao;
import model.TipoRelatorio;

public class GerenciaDisciplina {
	
	private ArrayList<Disciplina> disciplinas;
	private ArrayList<Prova> provas;
	private DisciplinaDAO disciplinaDao;
	private CursoDAO cursoDao;
	private Scanner sc;
	
	public GerenciaDisciplina() {
		sc = new Scanner(System.in);
		this.disciplinaDao = new DisciplinaDAO();
		this.cursoDao = new CursoDAO();
	}

	public void adicionar() {
		System.out.println("==============================");
		System.out.println("CADASTRO DE DISCIPLINA");
		
		Disciplina disciplina = new Disciplina();
		
		System.out.println("1. Digite o código da disciplina: ");
		disciplina.setCodigo(sc.nextLine());
		
		System.out.println("2. Digite o nome da disciplina: ");
		disciplina.setNome(sc.nextLine());
		
		System.out.println("3. Digite a ementa da disciplina: ");
		disciplina.setEmenta(sc.nextLine());
		
		System.out.println("4. Digite a carga horária da disciplina (apenas números inteiros): ");
		disciplina.setCargaHoraria(sc.nextInt());
		sc.skip("\r\n");
		
		ArrayList<Curso> cursos = cursoDao.relatorio();
		System.out.println("==============================");
		System.out.println("CURSOS CADASTRADOS");
		
		for (Curso c : cursos) {
			System.out.println("Código: " + c.getCodigo() + " Nome: " + c.getNome());
		}
		System.out.println("------------------------------");
		
		System.out.println("5. Digite o código do curso à qual a disciplina pertence: ");
		String codcurso = sc.nextLine();
		
		disciplinaDao.inserir(disciplina, codcurso);
		
	}

	public void remover() {
		System.out.println("==============================");
		System.out.println("REMOÇÃO DE DISCIPLINA");
		
		String codigo;
		boolean achou = false;
		int opcao;
		
		disciplinas = disciplinaDao.relatorio();
		
		if(disciplinas != null && !disciplinas.isEmpty()) {
			imprimeDisciplinas(disciplinas, TipoRelatorio.SINTETICO);
			System.out.println("Qual o código da disciplina que deseja remover?");
			codigo = sc.nextLine();
			
			Disciplina disciplina = null;
			for (Disciplina disc : disciplinas) {
				if(disc.getCodigo().equals(codigo)) {
					achou = true;
					disciplina = disc;
				}
			}
			
			if(achou) {
				System.out.println("==============================");
				System.out.println(disciplina);
				System.out.println("==============================");
				
				System.out.println("Confirma a remoção?");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					disciplinaDao.excluir(disciplina);
					System.out.println("SUCESSO: Disciplina removida!");
				}
				else if(opcao == 2) {
					System.out.println("ERRO: Operação cancelada!");
				}
				else {
					System.out.println("AVISO: Opção inválida!");
				}
			}
			else {
				System.out.println("AVISO: Disciplina não encontrado!");
			}
		}
		else {
			System.out.println("AVISO: Não há disciplinas cadastrados. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERAÇÃO DE DISCIPLINA");
		
		String codigo;
		boolean achou = false;
		int opcao;

		disciplinas = disciplinaDao.relatorio();
		
		if(disciplinas != null && !disciplinas.isEmpty()) {
			imprimeDisciplinas(disciplinas, TipoRelatorio.SINTETICO);
			System.out.println("Qual o código da disciplina que deseja alterar?");
			codigo = sc.nextLine();
			
			Disciplina disciplina = null;
			for (Disciplina disc : disciplinas) {
				if(disc.getCodigo().equals(codigo)) {
					achou = true;
					disciplina = disc;
				}
			}
			
			if(achou) {
				System.out.println("==============================");
				System.out.println(disciplina);
				System.out.println("==============================");
				
				System.out.println("Confirma a alteração?");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					System.out.println("Digite os novos dados:");
					System.out.println("1. Digite o nome da disciplina: ");
					disciplina.setNome(sc.nextLine());
					
					System.out.println("2. Digite a ementa da disciplina: ");
					disciplina.setEmenta(sc.nextLine());
					
					System.out.println("3. Digite a carga horária da disciplina (apenas números inteiros): ");
					disciplina.setCargaHoraria(sc.nextInt());
					sc.skip("\r\n");
					
					ArrayList<Curso> cursos = cursoDao.relatorio();
					System.out.println("==============================");
					System.out.println("CURSOS CADASTRADOS");
					
					for (Curso c : cursos) {
						System.out.println("Código: " + c.getCodigo() + " Nome: " + c.getNome());
					}
					System.out.println("------------------------------");
					
					System.out.println("4. Digite o código do curso à qual a disciplina pertence: ");
					String codcurso = sc.nextLine();
					
					disciplinaDao.alterar(disciplina, codcurso);
					System.out.println("SUCESSO: Disciplina alterado!");
				}
				else if(opcao == 2) {
					System.out.println("ERRO: Operação cancelada!");
				}
				else {
					System.out.println("AVISO: Opção inválida!");
				}
			}
			else {
				System.out.println("AVISO: Disciplina não encontrado!");
			}
		}
		else {
			System.out.println("AVISO: Não há disciplinas cadastrados. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}

	public void consultar() {
		System.out.println("==============================");
		System.out.println("CONSULTA DE DISCIPLINAS");
		
		String codigo;
		boolean achou = false;

		disciplinas = disciplinaDao.relatorio();
		
		if(disciplinas != null && !disciplinas.isEmpty()) {
			imprimeDisciplinas(disciplinas, TipoRelatorio.SINTETICO);
			System.out.println("Qual o código da disciplina que deseja consultar?");
			codigo = sc.nextLine();
			
			Disciplina disciplina = null;
			for (Disciplina disc : disciplinas) {
				if(disc.getCodigo().equals(codigo)) {
					achou = true;
					disciplina = disc;
				}
			}
			
			if(achou) {
				System.out.println("==============================");
				System.out.println(disciplina);
				System.out.println("==============================");
			}
			else {
				System.out.println("AVISO: Disciplina não encontrado!");
			}
		}
		else {
			System.out.println("AVISO: Não há disciplinas cadastrados. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}

	public void gerarRelatorio() {
		System.out.println("==============================");
		System.out.println("RELATÓRIO DE DISCIPLINAS");
		
		disciplinas = disciplinaDao.relatorio();
		
		if(disciplinas != null && !disciplinas.isEmpty()) {
			imprimeDisciplinas(disciplinas, TipoRelatorio.ANALITICO);
		}
		else {
			System.out.println("AVISO: Não há disciplinas cadastradas. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void adicionarProva() {
		System.out.println("==============================");
		System.out.println("ADICIONAR PROVA DA DISCIPLINA");
		
		if(!disciplinas.isEmpty()) {
			
			gerarRelatorio();
			
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
			System.out.println("AVISO: Não há disciplinas cadastradas NO BANCO DE DISCIPLINAS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void removerProva() {
		System.out.println("==============================");
		System.out.println("REMOVER PROVA DA DISCIPLINA");
		
		if(!disciplinas.isEmpty()) {
			
			gerarRelatorio();
			
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
						
						System.out.println("==============================");
						for (Prova prova : provasDaDisciplina) {
							System.out.println("Posição: #" + provasDaDisciplina.indexOf(prova));
							System.out.println(prova);
							System.out.println("------------------------------");
						}
						
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
						System.out.println("AVISO: Não há provas cadastradas para NESSA DISCIPLINA. Impossível continuar operação. Voltando ao menu inicial...");
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
			System.out.println("AVISO: Não há disciplinas cadastrados NO BANCO DE DISCIPLINAS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void consultarProvas() {
		System.out.println("==============================");
		System.out.println("CONSULTAR PROVAS DA DISCIPLINA");
		
		if(!disciplinas.isEmpty()) {
			
			gerarRelatorio();
			
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
						for(Prova p : provasDaDisciplina) {
							System.out.println("Posição " + provasDaDisciplina.indexOf(p));
							System.out.println(p);

							System.out.println("------------------------------");
							System.out.println("Questões: ");
							
							ArrayList<Questao> questoesDaProvaDaDisciplina = p.getQuestoes();
							
							for (Questao questao : questoesDaProvaDaDisciplina) {
								System.out.println("Posição " + questoesDaProvaDaDisciplina.indexOf(questao));
								System.out.println(questao);
								System.out.println("------------------------------");
							}
						}
						
					}
					else {
						System.out.println("AVISO: Não há provas cadastradas NESSA DISCIPLINA. Impossível continuar operação. Voltando ao menu inicial...");
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
			System.out.println("AVISO: Não há disciplinas cadastradas NO BANCO DE DISCIPLINAS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	private void imprimeDisciplinas(ArrayList<Disciplina> listaDeDisciplinas, TipoRelatorio tipo) {
		System.out.println("...:::::[ LISTA DE DISCIPLINAS ]:::::...");
		
		if(tipo == TipoRelatorio.ANALITICO) {
			for(Disciplina disciplina : listaDeDisciplinas) {
				System.out.println(disciplina);
				System.out.println("------------------------------");
			}
		}
		else {
			for(Disciplina disciplina : listaDeDisciplinas) {
				System.out.println("Código: " + disciplina.getCodigo() + " - Nome: " + disciplina.getNome());
			}
			System.out.println("------------------------------");
		}
	}
}
