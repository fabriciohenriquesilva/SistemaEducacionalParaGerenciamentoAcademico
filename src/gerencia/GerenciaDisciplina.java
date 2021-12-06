package gerencia;

import java.util.ArrayList;
import java.util.Scanner;

import dao.CursoDAO;
import dao.DisciplinaDAO;
import dao.ProvaDAO;
import dao.QuestaoDAO;
import model.Curso;
import model.Disciplina;
import model.Prova;
import model.Questao;
import model.TipoRelatorio;

public class GerenciaDisciplina {
	
	private ArrayList<Disciplina> disciplinas;
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
	
	public void consultarProvas() {
		System.out.println("==============================");
		System.out.println("CONSULTAR PROVAS DA DISCIPLINA");
		
		ProvaDAO provaDao = new ProvaDAO();
		disciplinas = disciplinaDao.relatorio();
		
		if(disciplinas != null && !disciplinas.isEmpty()) {
			imprimeDisciplinas(disciplinas, TipoRelatorio.SINTETICO);
			System.out.println("Qual o código da disciplina que deseja consultar as provas?");
			String codigo = sc.nextLine();
			
			Disciplina disciplina = disciplinaDao.consultar(codigo);
			
			if(disciplina != null) {
				System.out.println("A disciplina que deseja consultar as provas é esta?");
				System.out.println("==============================");
				System.out.println(disciplina);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					ArrayList<Prova> provasDaDisciplina = provaDao.relatorioDeProvasPorDisciplina(disciplina);
					
					if(provasDaDisciplina != null && !provasDaDisciplina.isEmpty()) {
						System.out.println("...:::::[ PROVAS CADASTRADAS NA DISCIPLINA ]:::::...");
						for(Prova prova : provasDaDisciplina) {
							System.out.println("Código: " + prova.getIdentificador() + " - Nome: " + prova.getDisciplina().getNome() + 
									" - Código da Turma: " + prova.getTurma().getCodigo());
							System.out.println("------------------------------");
						}
						System.out.println("==============================");
					}
					else {
						System.out.println("AVISO: Não há provas cadastradas nessa disciplina!");
					}
				}
				else if(opcao == 2) {
					System.out.println("AVISO: Operação cancelada!");
				}
				else {
					System.out.println("AVISO: Opção inválida!");
				}
			}
			else {
				System.out.println("AVISO: Disciplina não encontrada!");
			}
		}
		else {
			System.out.println("AVISO: Não há disciplinas cadastrados. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void consultarQuestoes() {
		System.out.println("==============================");
		System.out.println("CONSULTAR QUESTÕES DA DISCIPLINA");
		
		QuestaoDAO questaoDao = new QuestaoDAO();
		disciplinas = disciplinaDao.relatorio();
		
		if(disciplinas != null && !disciplinas.isEmpty()) {
			imprimeDisciplinas(disciplinas, TipoRelatorio.SINTETICO);
			System.out.println("Qual o código da disciplina que deseja consultar as questões?");
			String codigo = sc.nextLine();
			
			Disciplina disciplina = disciplinaDao.consultar(codigo);
			
			if(disciplina != null) {
				System.out.println("A disciplina que deseja consultar as questões é esta?");
				System.out.println("==============================");
				System.out.println(disciplina);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					ArrayList<Questao> questoesDaDisciplina = questaoDao.relatorioDeQuestoesPorDisciplina(disciplina);
					
					if(questoesDaDisciplina != null && !questoesDaDisciplina.isEmpty()) {
						System.out.println("...:::::[ QUESTÕES CADASTRADAS NA DISCIPLINA ]:::::...");
						for(Questao questao : questoesDaDisciplina) {
							System.out.println("Código: " + questao.getCodigo() + " - Enunciado: " + questao.getEnunciado());
						}
						System.out.println("==============================");
					}
					else {
						System.out.println("AVISO: Não há questões cadastradas nessa disciplina!");
					}
				}
				else if(opcao == 2) {
					System.out.println("AVISO: Operação cancelada!");
				}
				else {
					System.out.println("AVISO: Opção inválida!");
				}
			}
			else {
				System.out.println("AVISO: Disciplina não encontrada!");
			}
		}
		else {
			System.out.println("AVISO: Não há disciplinas cadastrados. Impossível continuar operação. Voltando ao menu inicial...");
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
