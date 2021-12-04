package gerencia;

import java.util.ArrayList;
import java.util.Scanner;

import dao.DisciplinaDAO;
import dao.ProvaDAO;
import dao.TurmaDAO;
import model.Disciplina;
import model.Prova;
import model.Questao;
import model.TipoRelatorio;
import model.Turma;

public class GerenciaProva {
	
	private ArrayList<Prova> provas;
	private ArrayList<Questao> questoes;
	private ArrayList<Turma> turmas;
	private ArrayList<Disciplina> disciplinas;
	private Scanner sc;
	private TurmaDAO turmaDao;
	private ProvaDAO provaDao;
	private DisciplinaDAO disciplinaDao;
	
	public GerenciaProva() {
		sc = new Scanner(System.in);
		
		this.turmaDao = new TurmaDAO();
		this.provaDao = new ProvaDAO();
		this.disciplinaDao = new DisciplinaDAO();
	}

	public void adicionar() {
		System.out.println("==============================");
		System.out.println("CADASTRO DE PROVA");
		
		turmas = turmaDao.relatorio();
		
		if(turmas.isEmpty()) {
			System.out.println("AVISO: Não há turmas cadastradas. Necessário ter ao menos uma turma para cadastrar uma prova!");
			System.out.println("AVISO: Voltando ao menu inicial");
		}
		else {
			Prova prova = new Prova();
			
			System.out.println("1. Digite o identificador da prova: ");
			prova.setIdentificador(sc.nextLine());
			
			if(lerDados(prova)) {
				provaDao.inserir(prova);
			}
			else {
				System.out.println("ERRO: Prova não inserida!");
			}
		}
	}

	public void remover() {
		System.out.println("==============================");
		System.out.println("REMOÇÃO DE PROVA");
		
		String identificador;
		boolean achou = false;
		int opcao;
		
		provas = provaDao.relatorio();
		
		if(provas != null && !provas.isEmpty()) {
			imprimeProvas(provas, TipoRelatorio.SINTETICO);
			System.out.println("Qual o código da prova que deseja remover?");
			identificador = sc.nextLine();
			
			Prova prova = null;
			for (Prova p : provas) {
				if(p.getIdentificador().equals(identificador)) {
					achou = true;
					prova = p;
				}
			}
			
			if(achou) {
				System.out.println("==============================");
				System.out.println(prova);
				System.out.println("==============================");
				
				System.out.println("Confirma a remoção?");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					provaDao.excluir(prova);
					System.out.println("SUCESSO: Prova removida!");
				}
				else if(opcao == 2) {
					System.out.println("ERRO: Operação cancelada!");
				}
				else {
					System.out.println("AVISO: Opção inválida!");
				}
			}
			else {
				System.out.println("AVISO: Prova não encontrado!");
			}
		}
		else {
			System.out.println("AVISO: Não há provas cadastrados. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}

	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERAÇÃO DE PROVA");
		
		String identificador;
		boolean achou = false;
		int opcao;
		
		provas = provaDao.relatorio();
		
		if(provas != null && !provas.isEmpty()) {
			imprimeProvas(provas, TipoRelatorio.SINTETICO);
			System.out.println("Qual o código da prova que deseja remover?");
			identificador = sc.nextLine();
			
			Prova prova = null;
			for (Prova p : provas) {
				if(p.getIdentificador().equals(identificador)) {
					achou = true;
					prova = p;
				}
			}
			
			if(achou) {
				System.out.println("==============================");
				System.out.println(prova);
				System.out.println("==============================");
				
				System.out.println("Confirma a remoção?");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {

					if(lerDados(prova)) {
						provaDao.alterar(prova);
						System.out.println("SUCESSO: Prova alterada!");
					}
					else {
						System.out.println("ERRO: Prova não alterada!");
					}
				}
				else if(opcao == 2) {
					System.out.println("ERRO: Operação cancelada!");
				}
				else {
					System.out.println("AVISO: Opção inválida!");
				}
			}
			else {
				System.out.println("AVISO: Prova não encontrado!");
			}
		}
		else {
			System.out.println("AVISO: Não há provas cadastrados. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}

	public void consultar() {
		System.out.println("==============================");
		System.out.println("CONSULTA DE PROVA");
		
		String identificador;
		boolean achou = false;
		
		provas = provaDao.relatorio();
		
		if(provas != null && !provas.isEmpty()) {
			imprimeProvas(provas, TipoRelatorio.SINTETICO);
			System.out.println("Qual o código da prova que deseja remover?");
			identificador = sc.nextLine();
			
			Prova prova = null;
			for (Prova p : provas) {
				if(p.getIdentificador().equals(identificador)) {
					achou = true;
					prova = p;
				}
			}
			
			if(achou) {
				System.out.println("==============================");
				System.out.println(prova);
				System.out.println("==============================");
			}
			else {
				System.out.println("AVISO: Prova não encontrado!");
			}
		}
		else {
			System.out.println("AVISO: Não há provas cadastrados. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}

	public void gerarRelatorio() {
		System.out.println("==============================");
		System.out.println("RELATÓRIO DE PROVAS");
		
		provas = provaDao.relatorio();
		
		if(provas != null && !provas.isEmpty()) {
			imprimeProvas(provas, TipoRelatorio.ANALITICO);
		}
		else {
			System.out.println("AVISO: Não há provas cadastradas. Impossível continuar operação. Voltando ao menu inicial...");
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
	
	private boolean lerDados(Prova prova) {
		
		System.out.println("==============================");
		System.out.println("TURMAS CADASTRADAS NO SISTEMA: ");
		turmas = turmaDao.relatorio();
		
		for (Turma t : turmas) {
			System.out.println("Código: " + t.getCodigo() + " - Curso: " + t.getCurso().getNome() + 
					"\nDisciplina: " + t.getDisciplina().getNome() + " - Semestre/Ano: " + t.getSemestre() + "/" + t.getAno());
		}
		System.out.println("------------------------------");	
		System.out.println("2. Digite o código da turma para adicionar na prova: ");
		
		int codTurma = sc.nextInt();
		sc.skip("\r\n");
		Turma turma = turmaDao.consultar(codTurma);
		
		if(turma != null) {
			prova.setTurma(turma);
			System.out.println("SUCESSO: Turma adicionada na prova!");
			
			System.out.println("==============================");
			System.out.println("DISCIPLINAS CADASTRADOS NO SISTEMA: ");
			disciplinas = disciplinaDao.relatorio();
			
			for (Disciplina d : disciplinas) {
				System.out.println("Código: " + d.getCodigo() + " Nome: " + d.getNome());
			}
			System.out.println("------------------------------");	
			System.out.println("3. Digite o código da disciplina para adicionar na turma: ");
			
			String codDisciplina = sc.nextLine();
			Disciplina disciplina = disciplinaDao.consultar(codDisciplina);
			
			if(disciplina != null) {
				turma.setDisciplina(disciplina);
				System.out.println("SUCESSO: Disciplina adicionada na turma!");
				
				return true;
			}
			else {
				System.out.println("ERRO: Disciplina não encontrada!");
			}
		}
		else {
			System.out.println("ERRO: Turma não encontrada!");
		}
		return false;
	}
	
	private void imprimeProvas(ArrayList<Prova> listaDeProvas, TipoRelatorio tipo) {
		System.out.println("...:::::[ LISTA DE DISCIPLINAS ]:::::...");
		
		if(tipo == TipoRelatorio.ANALITICO) {
			for(Prova prova : listaDeProvas) {
				System.out.println(prova);
				System.out.println("------------------------------");
			}
		}
		else {
			for(Prova prova : listaDeProvas) {
				System.out.println("Código: " + prova.getIdentificador() + " - Nome: " + prova.getDisciplina().getNome() + 
						"Código da Turma: " + prova.getTurma().getCodigo());
			}
			System.out.println("------------------------------");
		}
	}
}
