package gerencia;

import java.util.ArrayList;
import java.util.Scanner;

import dao.DisciplinaDAO;
import dao.QuestaoDAO;
import model.Disciplina;
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
		System.out.println("CADASTRO DE QUESTÃO");
		
		Questao questao = new Questao();
		
		System.out.println("1. Digite o código da questão: ");
		questao.setCodigo(sc.nextLine());
		
		System.out.println("2. Digite o enunciado da questão: ");
		questao.setEnunciado(sc.nextLine());
		
		System.out.println("3. Digite o valor da questão: ");
		questao.setValor(sc.nextFloat());
		sc.skip("\r\n");
		
		ArrayList<Disciplina> disciplinas = disciplinaDao.relatorio();
		System.out.println("==============================");
		System.out.println("DISCIPLINAS CADASTRADAS");
		
		for (Disciplina d : disciplinas) {
			System.out.println("Código: " + d.getCodigo() + " Nome: " + d.getNome());
		}
		System.out.println("------------------------------");
		
		System.out.println("4. Digite o código da disciplina a que essa questão pertence: ");
		String codDisciplina = sc.nextLine();
		
		Disciplina d = disciplinaDao.consultar(codDisciplina);
		questao.setDisciplina(d);
		
		questaoDao.inserir(questao); 
		
	}
	
	public void remover() {
		
		System.out.println("==============================");
		System.out.println("REMOÇÃO DE QUESTÃO");
		
		String codigo;
		boolean achou = false;
		int opcao;
		
		questoes = questaoDao.relatorio();
		
		if(questoes != null && !questoes.isEmpty()) {
			imprimeDisciplinas(questoes, TipoRelatorio.SINTETICO);
			System.out.println("Qual o código da questão que deseja remover?");
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
				
				System.out.println("Confirma a remoção?");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					questaoDao.excluir(questao);
					System.out.println("SUCESSO: Questão removida!");
				}
				else if(opcao == 2) {
					System.out.println("ERRO: Operação cancelada!");
				}
				else {
					System.out.println("AVISO: Opção inválida!");
				}
			}
			else {
				System.out.println("AVISO: Questão não encontrado!");
			}
		}
		else {
			System.out.println("AVISO: Não há questões cadastrados. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERAÇÃO DE QUESTÕES");
		
		String codigo;
		boolean achou = false;
		int opcao;

		questoes = questaoDao.relatorio();
		
		if(questoes != null && !questoes.isEmpty()) {
			imprimeDisciplinas(questoes, TipoRelatorio.SINTETICO);
			System.out.println("Qual o código da questão que deseja alterar?");
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
				
				System.out.println("Confirma a alteração?");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					System.out.println("Digite os novos dados:");
					
					System.out.println("1. Digite o enunciado da questão: ");
					questao.setEnunciado(sc.nextLine());
					
					System.out.println("2. Digite o valor da questão: ");
					questao.setValor(sc.nextFloat());
					sc.skip("\r\n");
					
					ArrayList<Disciplina> disciplinas = disciplinaDao.relatorio();
					System.out.println("==============================");
					System.out.println("DISCIPLINAS CADASTRADAS");
					
					for (Disciplina d : disciplinas) {
						System.out.println("Código: " + d.getCodigo() + " Nome: " + d.getNome());
					}
					System.out.println("------------------------------");
					
					System.out.println("3. Digite o código da disciplina a que essa questão pertence: ");
					questao.getDisciplina().setCodigo(sc.nextLine());
					
					questaoDao.alterar(questao);
					System.out.println("SUCESSO: Questão alterada!");
				}
				else if(opcao == 2) {
					System.out.println("ERRO: Operação cancelada!");
				}
				else {
					System.out.println("AVISO: Opção inválida!");
				}
			}
			else {
				System.out.println("AVISO: Questão não encontrado!");
			}
		}
		else {
			System.out.println("AVISO: Não há questões cadastrados. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void consultar() {
		System.out.println("==============================");
		System.out.println("CONSULTA DE QUESTÃO");
		
		String codigo;
		boolean achou = false;

		questoes = questaoDao.relatorio();
		
		if(questoes != null && !questoes.isEmpty()) {
			imprimeDisciplinas(questoes, TipoRelatorio.SINTETICO);
			System.out.println("Qual o código da questão que deseja consultar?");
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
				System.out.println("AVISO: Questão não encontrada!");
			}
		}
		else {
			System.out.println("AVISO: Não há questões cadastradas. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void gerarRelatorio() {
		
		System.out.println("==============================");
		System.out.println("RELATÓRIO DE QUESTÕES");
		
		questoes = questaoDao.relatorio();
		
		if(questoes != null && !questoes.isEmpty()) {
			imprimeDisciplinas(questoes, TipoRelatorio.ANALITICO);
		}
		else {
			System.out.println("AVISO: Não há questões cadastradas. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	private void imprimeDisciplinas(ArrayList<Questao> listaDeQuestoes, TipoRelatorio tipo) {
		System.out.println("...:::::[ LISTA DE QUESTÕES ]:::::...");
		
		if(tipo == TipoRelatorio.ANALITICO) {
			for(Questao questao : listaDeQuestoes) {
				System.out.println(questao);
				System.out.println("------------------------------");
			}
		}
		else {
			for(Questao questao : listaDeQuestoes) {
				System.out.println("Código: " + questao.getCodigo() + " - Enunciado: " + questao.getEnunciado());
			}
			System.out.println("------------------------------");
		}
	}
}
