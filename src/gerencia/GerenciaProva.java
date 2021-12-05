package gerencia;

import java.util.ArrayList;
import java.util.Scanner;

import dao.DisciplinaDAO;
import dao.ProvaDAO;
import dao.ProvaQuestaoDAO;
import dao.QuestaoDAO;
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
	private QuestaoDAO questaoDao;
	private ProvaQuestaoDAO provaQuestaoDao;
	
	public GerenciaProva() {
		sc = new Scanner(System.in);
		
		this.turmaDao = new TurmaDAO();
		this.provaDao = new ProvaDAO();
		this.questaoDao = new QuestaoDAO();
		this.provaQuestaoDao = new ProvaQuestaoDAO();
		this.disciplinaDao = new DisciplinaDAO();
	}

	public void adicionar() {
		System.out.println("==============================");
		System.out.println("CADASTRO DE PROVA");
		
		turmas = turmaDao.relatorio();
		
		if(turmas.isEmpty()) {
			System.out.println("AVISO: N�o h� turmas cadastradas. Necess�rio ter ao menos uma turma para cadastrar uma prova!");
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
				System.out.println("ERRO: Prova n�o inserida!");
			}
		}
	}

	public void remover() {
		System.out.println("==============================");
		System.out.println("REMO��O DE PROVA");
		
		String identificador;
		boolean achou = false;
		int opcao;
		
		provas = provaDao.relatorio();
		
		if(provas != null && !provas.isEmpty()) {
			imprimeProvas(provas, TipoRelatorio.SINTETICO);
			System.out.println("Qual o c�digo da prova que deseja remover?");
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
				
				System.out.println("Confirma a remo��o?");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					provaDao.excluir(prova);
					System.out.println("SUCESSO: Prova removida!");
				}
				else if(opcao == 2) {
					System.out.println("ERRO: Opera��o cancelada!");
				}
				else {
					System.out.println("AVISO: Op��o inv�lida!");
				}
			}
			else {
				System.out.println("AVISO: Prova n�o encontrado!");
			}
		}
		else {
			System.out.println("AVISO: N�o h� provas cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}

	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERA��O DE PROVA");
		
		String identificador;
		boolean achou = false;
		int opcao;
		
		provas = provaDao.relatorio();
		
		if(provas != null && !provas.isEmpty()) {
			imprimeProvas(provas, TipoRelatorio.SINTETICO);
			System.out.println("Qual o c�digo da prova que deseja remover?");
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
				
				System.out.println("Confirma a remo��o?");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {

					if(lerDados(prova)) {
						provaDao.alterar(prova);
						System.out.println("SUCESSO: Prova alterada!");
					}
					else {
						System.out.println("ERRO: Prova n�o alterada!");
					}
				}
				else if(opcao == 2) {
					System.out.println("ERRO: Opera��o cancelada!");
				}
				else {
					System.out.println("AVISO: Op��o inv�lida!");
				}
			}
			else {
				System.out.println("AVISO: Prova n�o encontrado!");
			}
		}
		else {
			System.out.println("AVISO: N�o h� provas cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
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
			System.out.println("Qual o c�digo da prova que deseja remover?");
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
				System.out.println("AVISO: Prova n�o encontrado!");
			}
		}
		else {
			System.out.println("AVISO: N�o h� provas cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}

	public void gerarRelatorio() {
		System.out.println("==============================");
		System.out.println("RELAT�RIO DE PROVAS");
		
		provas = provaDao.relatorio();
		
		if(provas != null && !provas.isEmpty()) {
			imprimeProvas(provas, TipoRelatorio.ANALITICO);
		}
		else {
			System.out.println("AVISO: N�o h� provas cadastradas. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void adicionarQuestao() {
		System.out.println("==============================");
		System.out.println("ADICIONAR QUEST�O NA PROVA");
		
		questoes = questaoDao.relatorio();
		provas = provaDao.relatorio();
		
		if(provas != null && !provas.isEmpty()) {
			if(questoes != null && !questoes.isEmpty()) {
				System.out.println("------------------------------");
				System.out.println("PROVAS CADASTRADAS");
				System.out.println("==============================");
				imprimeProvas(provas, TipoRelatorio.SINTETICO);
				System.out.println("Selecione a prova pelo seu identificador: ");
				String identificador = sc.nextLine();
				
				Prova prova = provaDao.consultar(identificador);
				if(prova != null) {
					System.out.println("------------------------------");
					System.out.println("QUEST�ES CADASTRADAS");
					System.out.println("==============================");
					
					for (Questao q : questoes) {
						System.out.println("C�digo: " + q.getCodigo() + " - Enunciado: " + q.getEnunciado());
					}
					System.out.println("------------------------------");
					
					System.out.println("Selecione a quest�o pelo seu c�digo: ");
					String codigo = sc.nextLine();
					
					Questao questao = questaoDao.consultar(codigo);
					
					if(questao != null) {
						provaQuestaoDao.inserir(prova, questao);
						System.out.println("SUCESSO: Quest�o inserida na prova!");
					}
					else {
						System.out.println("ERRO: Quest�o n�o encontrada!");
					}
				}
				else {
					System.out.println("ERRO: Prova n�o encontrada!");
				}
			}
			else {
				System.out.println("ERRO: N�o h� quest�es cadastrados!");
			}
		}
		else {
			System.out.println("ERRO: N�o h� provas cadastradas!");
		}
	}
	
	public void removerQuestao() {
		System.out.println("==============================");
		System.out.println("REMOVER QUEST�O DA PROVA");
		
		questoes = questaoDao.relatorio();
		provas = provaDao.relatorio();
		
		if(provas != null && !provas.isEmpty()) {
			if(questoes != null && !questoes.isEmpty()) {
				System.out.println("------------------------------");
				System.out.println("PROVAS CADASTRADAS");
				System.out.println("==============================");
				imprimeProvas(provas, TipoRelatorio.SINTETICO);
				System.out.println("Selecione a prova pelo seu identificador: ");
				String identificador = sc.nextLine();
				
				Prova prova = provaDao.consultar(identificador);
				if(prova != null) {
					System.out.println("A prova que deseja remover as quest�es � esta?");
					System.out.println("==============================");
					System.out.println(prova);
					System.out.println("==============================");
					System.out.println("[1] Sim");
					System.out.println("[2] N�o");
					
					int opcao = sc.nextInt();
					sc.skip("\r\n");
					
					if(opcao == 1) {
						ArrayList<Questao> questoesDaProva = provaQuestaoDao.relatorio(prova);
						
						if(questoesDaProva != null && !questoesDaProva.isEmpty()) {
							System.out.println("...:::::[ LISTA DE QUEST�ES DA PROVA ]:::::...");
							for(Questao q : questoesDaProva) {
								System.out.println("C�digo: " + q.getCodigo() + " - Enunciado: " + q.getEnunciado());
							}
							System.out.println("==============================");
							System.out.println("Selecione a quest�o pelo seu c�digo: ");
							String codigo = sc.nextLine();
							Questao questao = questaoDao.consultar(codigo);
							
							if(questao != null) {
								provaQuestaoDao.excluir(prova, questao);
								System.out.println("SUCESSO: Quest�o removida da prova!");
							}
							else {
								System.out.println("ERRO: Quest�o n�o encontrada!");
							}	
						}
						else {
							System.out.println("AVISO: N�o h� quest�es cadastrados NESSA PROVA.");
						}
					}
					else if(opcao == 2) {
						System.out.println("AVISO: Opera��o cancelada!");
					}
					else {
						System.out.println("AVISO: Op��o inv�lida!");
					}
				}
				else {
					System.out.println("ERRO: Prova n�o encontrada!");
				}
			}
			else {
				System.out.println("ERRO: N�o h� quest�es cadastrados!");
			}
		}
		else {
			System.out.println("ERRO: N�o h� provas cadastradas!");
		}
	}
	
	public void consultarQuestoes() {
		System.out.println("==============================");
		System.out.println("CONSULTAR QUEST�ES DA PROVAS");
		questoes = questaoDao.relatorio();
		provas = provaDao.relatorio();
		
		if(provas != null && !provas.isEmpty()) {
			if(questoes != null && !questoes.isEmpty()) {
				System.out.println("------------------------------");
				System.out.println("PROVAS CADASTRADAS");
				System.out.println("==============================");
				imprimeProvas(provas, TipoRelatorio.SINTETICO);
				System.out.println("Selecione a prova pelo seu identificador: ");
				String identificador = sc.nextLine();
				
				Prova prova = provaDao.consultar(identificador);
				if(prova != null) {
					System.out.println("A prova que deseja consultar as quest�es � esta?");
					System.out.println("==============================");
					System.out.println(prova);
					System.out.println("==============================");
					System.out.println("[1] Sim");
					System.out.println("[2] N�o");
					
					int opcao = sc.nextInt();
					sc.skip("\r\n");
					
					if(opcao == 1) {
						ArrayList<Questao> questoesDaProva = provaQuestaoDao.relatorio(prova);
						
						if(questoesDaProva != null && !questoesDaProva.isEmpty()) {
							System.out.println("...:::::[ LISTA DE QUEST�ES DA PROVA ]:::::...");
							for(Questao q : questoesDaProva) {
								System.out.println("C�digo: " + q.getCodigo() + " - Enunciado: " + q.getEnunciado());
							}
						}
						else {
							System.out.println("AVISO: N�o h� quest�es cadastrados NESSA PROVA.");
						}
					}
					else if(opcao == 2) {
						System.out.println("AVISO: Opera��o cancelada!");
					}
					else {
						System.out.println("AVISO: Op��o inv�lida!");
					}
				}
				else {
					System.out.println("ERRO: Prova n�o encontrada!");
				}
			}
			else {
				System.out.println("ERRO: N�o h� quest�es cadastrados!");
			}
		}
		else {
			System.out.println("ERRO: N�o h� provas cadastradas!");
		}
	}
	
	private boolean lerDados(Prova prova) {
		
		System.out.println("==============================");
		System.out.println("TURMAS CADASTRADAS NO SISTEMA: ");
		turmas = turmaDao.relatorio();
		
		for (Turma t : turmas) {
			System.out.println("C�digo: " + t.getCodigo() + " - Curso: " + t.getCurso().getNome() + 
					"\nDisciplina: " + t.getDisciplina().getNome() + " - Semestre/Ano: " + t.getSemestre() + "/" + t.getAno());
		}
		System.out.println("------------------------------");	
		System.out.println("2. Digite o c�digo da turma para adicionar na prova: ");
		
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
				System.out.println("C�digo: " + d.getCodigo() + " Nome: " + d.getNome());
			}
			System.out.println("------------------------------");	
			System.out.println("3. Digite o c�digo da disciplina para adicionar na turma: ");
			
			String codDisciplina = sc.nextLine();
			Disciplina disciplina = disciplinaDao.consultar(codDisciplina);
			
			if(disciplina != null) {
				prova.setDisciplina(disciplina);
				System.out.println("SUCESSO: Disciplina adicionada na prova!");
				
				return true;
			}
			else {
				System.out.println("ERRO: Disciplina n�o encontrada!");
			}
		}
		else {
			System.out.println("ERRO: Turma n�o encontrada!");
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
				System.out.println("C�digo: " + prova.getIdentificador() + " - Nome: " + prova.getDisciplina().getNome() + 
						"C�digo da Turma: " + prova.getTurma().getCodigo());
			}
			System.out.println("------------------------------");
		}
	}
}
