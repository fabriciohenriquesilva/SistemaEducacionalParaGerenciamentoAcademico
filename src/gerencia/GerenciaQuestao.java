package gerencia;

import java.util.ArrayList;
import java.util.Scanner;

import dao.DisciplinaDAO;
import dao.ProvaQuestaoDAO;
import dao.QuestaoDAO;
import model.Disciplina;
import model.Prova;
import model.Questao;
import model.TipoRelatorio;

public class GerenciaQuestao {
	
	private ArrayList<Questao> questoes;
	private QuestaoDAO questaoDao;
	private DisciplinaDAO disciplinaDao;
	private Scanner sc;
	
	public GerenciaQuestao() {
		sc = new Scanner(System.in);
		this.questaoDao = new QuestaoDAO();
		this.disciplinaDao = new DisciplinaDAO();
	}
	
	public void adicionar() {
		
		System.out.println("==============================");
		System.out.println("CADASTRO DE QUEST�O");
		
		Questao questao = new Questao();
		
		System.out.println("1. Digite o c�digo da quest�o: ");
		questao.setCodigo(sc.nextLine());
		
		System.out.println("2. Digite o enunciado da quest�o: ");
		questao.setEnunciado(sc.nextLine());
		
		System.out.println("3. Digite o valor da quest�o: ");
		questao.setValor(sc.nextFloat());
		sc.skip("\r\n");
		
		ArrayList<Disciplina> disciplinas = disciplinaDao.relatorio();
		System.out.println("==============================");
		System.out.println("DISCIPLINAS CADASTRADAS");
		
		for (Disciplina d : disciplinas) {
			System.out.println("C�digo: " + d.getCodigo() + " Nome: " + d.getNome());
		}
		System.out.println("------------------------------");
		
		System.out.println("4. Digite o c�digo da disciplina a que essa quest�o pertence: ");
		String codDisciplina = sc.nextLine();
		
		Disciplina d = disciplinaDao.consultar(codDisciplina);
		questao.setDisciplina(d);
		
		questaoDao.inserir(questao); 
		
	}
	
	public void remover() {
		
		System.out.println("==============================");
		System.out.println("REMO��O DE QUEST�O");
		
		String codigo;
		boolean achou = false;
		int opcao;
		
		questoes = questaoDao.relatorio();
		
		if(questoes != null && !questoes.isEmpty()) {
			imprimeQuestoes(questoes, TipoRelatorio.SINTETICO);
			System.out.println("Qual o c�digo da quest�o que deseja remover?");
			codigo = sc.nextLine();
			
			Questao questao = null;
			for (Questao q : questoes) {
				if(q.getCodigo().equals(codigo)) {
					achou = true;
					questao = q;
				}
			}
			
			if(achou) {
				System.out.println("==============================");
				System.out.println(questao);
				System.out.println("==============================");
				
				System.out.println("Confirma a remo��o?");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					questaoDao.excluir(questao);
					System.out.println("SUCESSO: Quest�o removida!");
				}
				else if(opcao == 2) {
					System.out.println("ERRO: Opera��o cancelada!");
				}
				else {
					System.out.println("AVISO: Op��o inv�lida!");
				}
			}
			else {
				System.out.println("AVISO: Quest�o n�o encontrado!");
			}
		}
		else {
			System.out.println("AVISO: N�o h� quest�es cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERA��O DE QUEST�ES");
		
		String codigo;
		boolean achou = false;
		int opcao;

		questoes = questaoDao.relatorio();
		
		if(questoes != null && !questoes.isEmpty()) {
			imprimeQuestoes(questoes, TipoRelatorio.SINTETICO);
			System.out.println("Qual o c�digo da quest�o que deseja alterar?");
			codigo = sc.nextLine();
			
			Questao questao = null;
			for (Questao q : questoes) {
				if(q.getCodigo().equals(codigo)) {
					achou = true;
					questao = q;
				}
			}
			
			if(achou) {
				System.out.println("==============================");
				System.out.println(questao);
				System.out.println("==============================");
				
				System.out.println("Confirma a altera��o?");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					System.out.println("Digite os novos dados:");
					
					System.out.println("1. Digite o enunciado da quest�o: ");
					questao.setEnunciado(sc.nextLine());
					
					System.out.println("2. Digite o valor da quest�o: ");
					questao.setValor(sc.nextFloat());
					sc.skip("\r\n");
					
					ArrayList<Disciplina> disciplinas = disciplinaDao.relatorio();
					System.out.println("==============================");
					System.out.println("DISCIPLINAS CADASTRADAS");
					
					for (Disciplina d : disciplinas) {
						System.out.println("C�digo: " + d.getCodigo() + " Nome: " + d.getNome());
					}
					System.out.println("------------------------------");
					
					System.out.println("3. Digite o c�digo da disciplina a que essa quest�o pertence: ");
					questao.getDisciplina().setCodigo(sc.nextLine());
					
					questaoDao.alterar(questao);
					System.out.println("SUCESSO: Quest�o alterada!");
				}
				else if(opcao == 2) {
					System.out.println("ERRO: Opera��o cancelada!");
				}
				else {
					System.out.println("AVISO: Op��o inv�lida!");
				}
			}
			else {
				System.out.println("AVISO: Quest�o n�o encontrado!");
			}
		}
		else {
			System.out.println("AVISO: N�o h� quest�es cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void consultar() {
		System.out.println("==============================");
		System.out.println("CONSULTA DE QUEST�O");
		
		String codigo;
		boolean achou = false;

		questoes = questaoDao.relatorio();
		
		if(questoes != null && !questoes.isEmpty()) {
			imprimeQuestoes(questoes, TipoRelatorio.SINTETICO);
			System.out.println("Qual o c�digo da quest�o que deseja consultar?");
			codigo = sc.nextLine();
			
			Questao questao = null;
			for (Questao q : questoes) {
				if(q.getCodigo().equals(codigo)) {
					achou = true;
					questao = q;
				}
			}
			
			if(achou) {
				System.out.println("==============================");
				System.out.println(questao);
				System.out.println(disciplinaDao.consultar(questao.getDisciplina().getCodigo()));
				System.out.println("==============================");
			}
			else {
				System.out.println("AVISO: Quest�o n�o encontrada!");
			}
		}
		else {
			System.out.println("AVISO: N�o h� quest�es cadastradas. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void gerarRelatorio() {
		
		System.out.println("==============================");
		System.out.println("RELAT�RIO DE QUEST�ES");
		
		questoes = questaoDao.relatorio();
		
		if(questoes != null && !questoes.isEmpty()) {
			imprimeQuestoes(questoes, TipoRelatorio.ANALITICO);
		}
		else {
			System.out.println("AVISO: N�o h� quest�es cadastradas. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	private void imprimeQuestoes(ArrayList<Questao> listaDeQuestoes, TipoRelatorio tipo) {
		System.out.println("...:::::[ LISTA DE QUEST�ES ]:::::...");
		
		if(tipo == TipoRelatorio.ANALITICO) {
			for(Questao questao : listaDeQuestoes) {
				System.out.println(questao);
				System.out.println("------------------------------");
			}
		}
		else {
			for(Questao questao : listaDeQuestoes) {
				System.out.println("C�digo: " + questao.getCodigo() + " - Enunciado: " + questao.getEnunciado());
			}
			System.out.println("------------------------------");
		}
	}
	
	public void consultarProvas() {
		System.out.println("==============================");
		System.out.println("CONSULTAR PROVAS DE UMA QUEST�O");
		
		ProvaQuestaoDAO provaQuestaoDao = new ProvaQuestaoDAO();
		questoes = questaoDao.relatorio();
		
		if(questoes != null && !questoes.isEmpty()) {
			imprimeQuestoes(questoes, TipoRelatorio.SINTETICO);
			System.out.println("Escolha a quest�o pelo seu c�digo: ");
			String codigo = sc.nextLine();
			
			Questao questao = questaoDao.consultar(codigo);
			
			if(questao != null) {
				System.out.println("A quest�o que deseja consultar as provas � esta?");
				System.out.println("==============================");
				System.out.println(questao);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					ArrayList<Prova> provasDaQuestao = provaQuestaoDao.relatorioDeProvas(questao);
					
					if(provasDaQuestao != null && !provasDaQuestao.isEmpty()) {
						System.out.println("...:::::[ QUEST�O APARECE NAS PROVAS ]:::::...");
						for(Prova prova : provasDaQuestao) {
							System.out.println("C�digo: " + prova.getIdentificador() + " - Nome: " + prova.getDisciplina().getNome() + 
									" - C�digo da Turma: " + prova.getTurma().getCodigo());
						
							System.out.println("------------------------------");
						}
						System.out.println("==============================");
					}
					else {
						System.out.println("AVISO: N�o h� provas em que essa quest�o esteja cadastrada!");
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
				System.out.println("ERRO: Quest�o n�o encontrada!");
			}
		}
		else {
			System.out.println("AVISO: N�o h� quest�es cadastradas!");
		}
	}
}
