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
		
		System.out.println("1. Digite o c�digo da disciplina: ");
		disciplina.setCodigo(sc.nextLine());
		
		System.out.println("2. Digite o nome da disciplina: ");
		disciplina.setNome(sc.nextLine());
		
		System.out.println("3. Digite a ementa da disciplina: ");
		disciplina.setEmenta(sc.nextLine());
		
		System.out.println("4. Digite a carga hor�ria da disciplina (apenas n�meros inteiros): ");
		disciplina.setCargaHoraria(sc.nextInt());
		sc.skip("\r\n");
		
		ArrayList<Curso> cursos = cursoDao.relatorio();
		System.out.println("==============================");
		System.out.println("CURSOS CADASTRADOS");
		
		for (Curso c : cursos) {
			System.out.println("C�digo: " + c.getCodigo() + " Nome: " + c.getNome());
		}
		System.out.println("------------------------------");
		
		System.out.println("5. Digite o c�digo do curso � qual a disciplina pertence: ");
		String codcurso = sc.nextLine();
		
		disciplinaDao.inserir(disciplina, codcurso);
		
	}

	public void remover() {
		System.out.println("==============================");
		System.out.println("REMO��O DE DISCIPLINA");
		
		String codigo;
		boolean achou = false;
		int opcao;
		
		disciplinas = disciplinaDao.relatorio();
		
		if(disciplinas != null && !disciplinas.isEmpty()) {
			imprimeDisciplinas(disciplinas, TipoRelatorio.SINTETICO);
			System.out.println("Qual o c�digo da disciplina que deseja remover?");
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
				
				System.out.println("Confirma a remo��o?");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					disciplinaDao.excluir(disciplina);
					System.out.println("SUCESSO: Disciplina removida!");
				}
				else if(opcao == 2) {
					System.out.println("ERRO: Opera��o cancelada!");
				}
				else {
					System.out.println("AVISO: Op��o inv�lida!");
				}
			}
			else {
				System.out.println("AVISO: Disciplina n�o encontrado!");
			}
		}
		else {
			System.out.println("AVISO: N�o h� disciplinas cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERA��O DE DISCIPLINA");
		
		String codigo;
		boolean achou = false;
		int opcao;

		disciplinas = disciplinaDao.relatorio();
		
		if(disciplinas != null && !disciplinas.isEmpty()) {
			imprimeDisciplinas(disciplinas, TipoRelatorio.SINTETICO);
			System.out.println("Qual o c�digo da disciplina que deseja alterar?");
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
				
				System.out.println("Confirma a altera��o?");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					System.out.println("Digite os novos dados:");
					System.out.println("1. Digite o nome da disciplina: ");
					disciplina.setNome(sc.nextLine());
					
					System.out.println("2. Digite a ementa da disciplina: ");
					disciplina.setEmenta(sc.nextLine());
					
					System.out.println("3. Digite a carga hor�ria da disciplina (apenas n�meros inteiros): ");
					disciplina.setCargaHoraria(sc.nextInt());
					sc.skip("\r\n");
					
					ArrayList<Curso> cursos = cursoDao.relatorio();
					System.out.println("==============================");
					System.out.println("CURSOS CADASTRADOS");
					
					for (Curso c : cursos) {
						System.out.println("C�digo: " + c.getCodigo() + " Nome: " + c.getNome());
					}
					System.out.println("------------------------------");
					
					System.out.println("4. Digite o c�digo do curso � qual a disciplina pertence: ");
					String codcurso = sc.nextLine();
					
					disciplinaDao.alterar(disciplina, codcurso);
					System.out.println("SUCESSO: Disciplina alterado!");
				}
				else if(opcao == 2) {
					System.out.println("ERRO: Opera��o cancelada!");
				}
				else {
					System.out.println("AVISO: Op��o inv�lida!");
				}
			}
			else {
				System.out.println("AVISO: Disciplina n�o encontrado!");
			}
		}
		else {
			System.out.println("AVISO: N�o h� disciplinas cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
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
			System.out.println("Qual o c�digo da disciplina que deseja consultar?");
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
				System.out.println("AVISO: Disciplina n�o encontrado!");
			}
		}
		else {
			System.out.println("AVISO: N�o h� disciplinas cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}

	public void gerarRelatorio() {
		System.out.println("==============================");
		System.out.println("RELAT�RIO DE DISCIPLINAS");
		
		disciplinas = disciplinaDao.relatorio();
		
		if(disciplinas != null && !disciplinas.isEmpty()) {
			imprimeDisciplinas(disciplinas, TipoRelatorio.ANALITICO);
		}
		else {
			System.out.println("AVISO: N�o h� disciplinas cadastradas. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
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
				System.out.println("C�digo: " + disciplina.getCodigo() + " - Nome: " + disciplina.getNome());
			}
			System.out.println("------------------------------");
		}
	}
}
