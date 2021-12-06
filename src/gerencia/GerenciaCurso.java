package gerencia;

import java.util.ArrayList;
import java.util.Scanner;

import dao.CursoDAO;
import dao.DisciplinaDAO;
import model.Curso;
import model.Disciplina;
import model.TipoRelatorio;

public class GerenciaCurso {

	private ArrayList<Curso> cursos;
	private CursoDAO cursoDao;
	private Scanner sc;
	
	public GerenciaCurso() {
		sc = new Scanner(System.in);
		this.cursoDao = new CursoDAO();
	}
	
	public void adicionar() {
		
		System.out.println("==============================");
		System.out.println("CADASTRO DE CURSO");
		
		Curso curso = new Curso();
		
		System.out.println("1. Digite o c�digo do curso: ");
		curso.setCodigo(sc.nextLine());
		
		System.out.println("2. Digite o nome do curso: ");
		curso.setNome(sc.nextLine());
		
		cursoDao.inserir(curso);
	}
	
	public void remover() {
		
		System.out.println("==============================");
		System.out.println("REMO��O DE CURSO");
		
		String codigo;
		boolean achou = false;
		int opcao;
		
		cursos = cursoDao.relatorio();
		
		if(cursos != null && !cursos.isEmpty()) {
			imprimeCursos(cursos, TipoRelatorio.SINTETICO);
			System.out.println("Qual o c�digo do curso que deseja remover?");
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
				
				System.out.println("Confirma a remo��o?");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					cursoDao.excluir(curso);
					System.out.println("SUCESSO: Curso removido!");
				}
				else if(opcao == 2) {
					System.out.println("ERRO: Opera��o cancelada!");
				}
				else {
					System.out.println("AVISO: Op��o inv�lida!");
				}
			}
			else {
				System.out.println("AVISO: Curso n�o encontrado!");
			}
		}
		else {
			System.out.println("AVISO: N�o h� cursos cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERA��O DE CURSO");
		
		String codigo;
		boolean achou = false;
		int opcao;

		cursos = cursoDao.relatorio();
		
		if(cursos != null && !cursos.isEmpty()) {
			imprimeCursos(cursos, TipoRelatorio.SINTETICO);
			System.out.println("Qual o c�digo do curso que deseja alterar?");
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
				
				System.out.println("Confirma a altera��o?");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
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
					System.out.println("ERRO: Opera��o cancelada!");
				}
				else {
					System.out.println("AVISO: Op��o inv�lida!");
				}
			}
			else {
				System.out.println("AVISO: Curso n�o encontrado!");
			}
		}
		else {
			System.out.println("AVISO: N�o h� cursos cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
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
			System.out.println("Qual o c�digo do curso que deseja consultar?");
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
				System.out.println("AVISO: Curso n�o encontrado!");
			}
		}
		else {
			System.out.println("AVISO: N�o h� cursos cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void gerarRelatorio() {
		
		System.out.println("==============================");
		System.out.println("RELAT�RIO DE CURSOS");
		
		cursos = cursoDao.relatorio();
		
		if(cursos != null && !cursos.isEmpty()) {
			imprimeCursos(cursos, TipoRelatorio.ANALITICO);
		}
		else {
			System.out.println("AVISO: N�o h� cursos cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void consultarDisciplinas() {
		System.out.println("==============================");
		System.out.println("CONSULTAR DISCIPLINAS DO CURSO");
		
		cursos = cursoDao.relatorio();
		DisciplinaDAO disciplinaDao = new DisciplinaDAO();
		
		if(cursos != null && !cursos.isEmpty()) {
			imprimeCursos(cursos, TipoRelatorio.SINTETICO);
			
			System.out.println("Escolha o curso pelo seu c�digo: ");
			String codigo = sc.nextLine();
			Curso curso = cursoDao.consultar(codigo);
			
			if(curso != null) {
				System.out.println("O curso que deseja consultar as disciplinas � este?");
				System.out.println("==============================");
				System.out.println(curso);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					ArrayList<Disciplina> disciplinasDoCurso = disciplinaDao.relatorioDeDisciplinasPorCurso(curso);
					
					if(disciplinasDoCurso != null && !disciplinasDoCurso.isEmpty()) {
						System.out.println("...:::::[ DISCIPLINAS CADASTRADAS NO CURSO ]:::::...");
						for(Disciplina disc : disciplinasDoCurso) {
							System.out.println("C�digo: " + disc.getCodigo() + " - Nome: " + disc.getNome());
						}
					}
					else {
						System.out.println("AVISO: N�o h� disciplinas cadastradas nesse CURSO!");
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
				System.out.println("ERRO: Curso n�o encontrado!");
			}
		}
		else {
			System.out.println("AVISO: N�o h� cursos cadastrados!");
		}
	}

	
	private void imprimeCursos(ArrayList<Curso> listaDeCursos, TipoRelatorio tipo) {
		System.out.println("...:::::[ LISTA DE CURSOS ]:::::...");
		
		if(tipo == TipoRelatorio.ANALITICO) {
			for(Curso curso : listaDeCursos) {
				System.out.println(curso);
				System.out.println("------------------------------");
			}
		}
		else {
			for(Curso curso : listaDeCursos) {
				System.out.println("C�digo: " + curso.getCodigo() + " - Nome: " + curso.getNome());
			}
			System.out.println("------------------------------");
		}
	}
}
