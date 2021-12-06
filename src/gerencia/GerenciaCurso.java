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
	
	public void consultarDisciplinas() {
		System.out.println("==============================");
		System.out.println("CONSULTAR DISCIPLINAS DO CURSO");
		
		cursos = cursoDao.relatorio();
		DisciplinaDAO disciplinaDao = new DisciplinaDAO();
		
		if(cursos != null && !cursos.isEmpty()) {
			imprimeCursos(cursos, TipoRelatorio.SINTETICO);
			
			System.out.println("Escolha o curso pelo seu código: ");
			String codigo = sc.nextLine();
			Curso curso = cursoDao.consultar(codigo);
			
			if(curso != null) {
				System.out.println("O curso que deseja consultar as disciplinas é este?");
				System.out.println("==============================");
				System.out.println(curso);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					ArrayList<Disciplina> disciplinasDoCurso = disciplinaDao.relatorioDeDisciplinasPorCurso(curso);
					
					if(disciplinasDoCurso != null && !disciplinasDoCurso.isEmpty()) {
						System.out.println("...:::::[ DISCIPLINAS CADASTRADAS NO CURSO ]:::::...");
						for(Disciplina disc : disciplinasDoCurso) {
							System.out.println("Código: " + disc.getCodigo() + " - Nome: " + disc.getNome());
						}
					}
					else {
						System.out.println("AVISO: Não há disciplinas cadastradas nesse CURSO!");
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
				System.out.println("ERRO: Curso não encontrado!");
			}
		}
		else {
			System.out.println("AVISO: Não há cursos cadastrados!");
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
				System.out.println("Código: " + curso.getCodigo() + " - Nome: " + curso.getNome());
			}
			System.out.println("------------------------------");
		}
	}
}
