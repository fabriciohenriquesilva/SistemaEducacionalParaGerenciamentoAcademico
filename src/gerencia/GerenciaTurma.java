package gerencia;

import java.util.ArrayList;
import java.util.Scanner;

import dao.AlunoDAO;
import dao.CursoDAO;
import dao.DisciplinaDAO;
import dao.ProfessorDAO;
import dao.TurmaAlunoDAO;
import dao.TurmaDAO;
import model.Aluno;
import model.Curso;
import model.Disciplina;
import model.Professor;
import model.TipoRelatorio;
import model.Turma;

public class GerenciaTurma {
	
	private ArrayList<Turma> turmas;
	private ArrayList<Aluno> alunos;
	private ArrayList<Professor> professores;
	private ArrayList<Disciplina> disciplinas;
	private ArrayList<Curso> cursos;
	
	private TurmaDAO turmaDao;
	private DisciplinaDAO disciplinaDao;
	private ProfessorDAO professorDao;
	private CursoDAO cursoDao;
	private TurmaAlunoDAO turmaAlunoDao;
	private AlunoDAO alunoDao;
	
	private Scanner sc;
	
	public GerenciaTurma() {
		this.turmaDao = new TurmaDAO();
		this.disciplinaDao = new DisciplinaDAO();
		this.professorDao = new ProfessorDAO();
		this.cursoDao = new CursoDAO();
		this.turmaAlunoDao = new TurmaAlunoDAO();
		this.alunoDao = new AlunoDAO();
		sc = new Scanner(System.in);
	}

	public void adicionar() {
		System.out.println("==============================");
		System.out.println("CADASTRO DE TURMA");
		
		cursos = cursoDao.relatorio();
		professores = professorDao.relatorio();
		disciplinas = disciplinaDao.relatorio();
		
		if(cursos.isEmpty()) {
			System.out.println("AVISO: N�o h� cursos cadastrados! Voltando ao menu inicial...");
		}
		else if(professores.isEmpty()) {
			System.out.println("AVISO: N�o h� professores cadastrados. Voltando ao menu inicial...");
		}
		else if(disciplinas.isEmpty()) {
			System.out.println("AVISO: N�o h� disciplinas cadastradas. Voltando ao menu inicial...");
		}
		else {
			Turma turma = new Turma();
			
			if(lerDados(turma)) {
				turmaDao.inserir(turma);
			}
			else {
				System.out.println("ERRO: Turma n�o inserida!");
			}
		}
	}
	
	public void remover() {
		System.out.println("==============================");
		System.out.println("REMO��O DE TURMA");
		
		int codigo;
		boolean achou = false;
		int opcao;
		
		turmas = turmaDao.relatorio();
		
		if(turmas != null && !turmas.isEmpty()) {
			imprimeTurmas(turmas, TipoRelatorio.SINTETICO);
			System.out.println("Qual o c�digo da turma que deseja remover?");
			codigo = sc.nextInt();
			sc.skip("\r\n");
			
			Turma turma = null;
			for (Turma t : turmas) {
				if(t.getCodigo() == codigo) {
					achou = true;
					turma = t;
				}
			}
			
			if(achou) {
				System.out.println("==============================");
				System.out.println(turma);
				System.out.println("==============================");
				
				System.out.println("Confirma a remo��o?");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					turmaDao.excluir(turma);
					System.out.println("SUCESSO: Turma removida!");
				}
				else if(opcao == 2) {
					System.out.println("ERRO: Opera��o cancelada!");
				}
				else {
					System.out.println("AVISO: Op��o inv�lida!");
				}
			}
			else {
				System.out.println("AVISO: Turma n�o encontrada!");
			}
		}
		else {
			System.out.println("AVISO: N�o h� turmas cadastrados.");
		}
	}
	
	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERA��O DE TURMA");
		
		int codigo;
		boolean achou = false;
		int opcao;
		
		turmas = turmaDao.relatorio();
		
		if(turmas != null && !turmas.isEmpty()) {
			imprimeTurmas(turmas, TipoRelatorio.SINTETICO);
			System.out.println("Qual o c�digo da turma que deseja alterar?");
			codigo = sc.nextInt();
			sc.skip("\r\n");
			
			Turma turma = null;
			for (Turma t : turmas) {
				if(t.getCodigo() == codigo) {
					achou = true;
					turma = t;
				}
			}
			
			if(achou) {
				System.out.println("==============================");
				System.out.println(turma);
				System.out.println("==============================");
				
				System.out.println("Confirma a altera��o?");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					if(lerDados(turma)) {
						turmaDao.alterar(turma);
						System.out.println("SUCESSO: Turma alterada!");
					}
					else {
						System.out.println("ERRO: Turma n�o alterada!");
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
				System.out.println("AVISO: Turma n�o encontrada!");
			}
		}
		else {
			System.out.println("AVISO: N�o h� turmas cadastrados.");
		}
	}
	
	public void consultar() {
		System.out.println("==============================");
		System.out.println("CONSULTA DE TURMAS");
		
		int codigo;
		boolean achou = false;
		
		turmas = turmaDao.relatorio();
		
		if(turmas != null && !turmas.isEmpty()) {
			imprimeTurmas(turmas, TipoRelatorio.SINTETICO);
			System.out.println("Qual o c�digo da turma que deseja consultar?");
			codigo = sc.nextInt();
			sc.skip("\r\n");
			
			Turma turma = null;
			for (Turma t : turmas) {
				if(t.getCodigo() == codigo) {
					achou = true;
					turma = t;
				}
			}
			
			if(achou) {
				System.out.println("==============================");
				System.out.println(turma);
				System.out.println("==============================");
			}
			else {
				System.out.println("AVISO: Turma n�o encontrada!");
			}
		}
		else {
			System.out.println("AVISO: N�o h� turmas cadastrados.");
		}
	}
	
	public void gerarRelatorio() {
		System.out.println("==============================");
		System.out.println("RELAT�RIO DE TURMAS");
		
		turmas = turmaDao.relatorio();
		
		if(turmas != null && !turmas.isEmpty()) {
			imprimeTurmas(turmas, TipoRelatorio.ANALITICO);
		}
		else {
			System.out.println("AVISO: N�o h� turmas cadastrados.");
		}
	}
	
	private boolean lerDados(Turma turma) {
		
		System.out.println("1. Digite o ano: ");
		turma.setAno(sc.nextInt());
		sc.skip("\r\n");
		
		System.out.println("2. Digite o semestre: ");
		turma.setSemestre(sc.nextInt());
		sc.skip("\r\n");
		
		System.out.println("==============================");
		System.out.println("CURSOS CADASTRADOS NO SISTEMA: ");
		cursos = cursoDao.relatorio();
		
		for (Curso c : cursos) {
			System.out.println("C�digo: " + c.getCodigo() + " Nome: " + c.getNome());
		}
		System.out.println("------------------------------");
		System.out.println("3. Digite o c�digo do curso para adicionar na turma: ");
		String codCurso = sc.nextLine();
		Curso curso = cursoDao.consultar(codCurso);
		
		if(curso != null) {
			turma.setCurso(curso);
			System.out.println("SUCESSO: Curso adicionado na turma!");
			
			System.out.println("==============================");
			System.out.println("PROFESSORES CADASTRADOS NO SISTEMA: ");
			professores = professorDao.relatorio();
			
			for (Professor p : professores) {
				System.out.println("C�digo: " + p.getCodigo() + " Nome: " + p.getNome());
			}
			System.out.println("------------------------------");	
			System.out.println("4. Digite o c�digo do professor para adicionar na turma: ");
			int codProfessor = sc.nextInt();
			sc.skip("\r\n");
			Professor professor = professorDao.consultar(codProfessor);

			if(professor != null) {
				turma.setProfessor(professor);
				System.out.println("SUCESSO: Professor adicionado na turma!");
				
				System.out.println("==============================");
				System.out.println("DISCIPLINAS CADASTRADOS NO SISTEMA: ");
				disciplinas = disciplinaDao.relatorio();
				
				for (Disciplina d : disciplinas) {
					System.out.println("C�digo: " + d.getCodigo() + " Nome: " + d.getNome());
				}
				System.out.println("------------------------------");	
				System.out.println("5. Digite o c�digo da disciplina para adicionar na turma: ");
				
				String codDisciplina = sc.nextLine();
				Disciplina disciplina = disciplinaDao.consultar(codDisciplina);
				
				if(disciplina != null) {
					turma.setDisciplina(disciplina);
					System.out.println("SUCESSO: Disciplina adicionada na turma!");
					
					return true;
				}
				else {
					System.out.println("ERRO: Disciplina n�o encontrada!");
				}
			}
			else {
				System.out.println("ERRO: Professor n�o encontrado!");
			}
		}
		else {
			System.out.println("ERRO: Curso n�o encontrado!");
		}
		return false;
	}
	
	private void imprimeTurmas(ArrayList<Turma> listaDeTurmas, TipoRelatorio tipo) {
		System.out.println("...:::::[ LISTA DE TURMAS ]:::::...");
		
		if(tipo == TipoRelatorio.ANALITICO) {
			for(Turma turma : listaDeTurmas) {
				System.out.println(turma);
				System.out.println("------------------------------");
			}
		}
		else {
			for(Turma turma : listaDeTurmas) {
				System.out.println("C�digo: " + turma.getCodigo() + " - Curso: " + turma.getCurso().getNome() + 
						"\nDisciplina: " + turma.getDisciplina().getNome() + " - Semestre/Ano: " + turma.getSemestre() + "/" + turma.getAno());
			}
			System.out.println("------------------------------");
		}
	}
	
	public void adicionarAluno() {
		System.out.println("==============================");
		System.out.println("ADICIONAR ALUNO NA TURMA");
		
		alunos = alunoDao.relatorio();
		turmas = turmaDao.relatorio();
		
		if(turmas != null && !turmas.isEmpty()) {
			if(alunos != null && !alunos.isEmpty()) {
				System.out.println("------------------------------");
				System.out.println("TURMAS CADASTRADAS");
				System.out.println("==============================");
				imprimeTurmas(turmas, TipoRelatorio.SINTETICO);
				System.out.println("Selecione a turma pelo seu c�digo: ");
				int codigo = sc.nextInt();
				sc.skip("\r\n");
				
				Turma turma = turmaDao.consultar(codigo);
				if(turma != null) {
					System.out.println("------------------------------");
					System.out.println("ALUNOS CADASTRADOS");
					System.out.println("==============================");
					
					for (Aluno a : alunos) {
						System.out.println("Matr�cula: " + a.getMatricula() + " - Nome: " + a.getNome());
					}
					System.out.println("------------------------------");
					
					System.out.println("Selecione o aluno pela sua matr�cula: ");
					String matricula = sc.nextLine();
					
					Aluno aluno = alunoDao.consultar(matricula);
					
					if(aluno != null) {
						turmaAlunoDao.inserir(turma, aluno);
						System.out.println("SUCESSO: Aluno inserido na turma!");
					}
					else {
						System.out.println("ERRO: Aluno n�o encontrado!");
					}
				}
				else {
					System.out.println("ERRO: Turma n�o encontrada!");
				}
			}
			else {
				System.out.println("ERRO: N�o h� alunos cadastrados!");
			}
		}
		else {
			System.out.println("ERRO: N�o h� turmas cadastradas!");
		}
	}
	
	public void removerAluno() {
		System.out.println("==============================");
		System.out.println("REMOVER ALUNO DA TURMA");
		
		alunos = alunoDao.relatorio();
		turmas = turmaDao.relatorio();
		
		if(turmas != null && !turmas.isEmpty()) {
			if(alunos != null && !alunos.isEmpty()) {
				System.out.println("------------------------------");
				System.out.println("TURMAS CADASTRADAS");
				System.out.println("==============================");
				imprimeTurmas(turmas, TipoRelatorio.SINTETICO);
				System.out.println("Selecione a turma pelo seu c�digo: ");
				int codigo = sc.nextInt();
				sc.skip("\r\n");
				
				Turma turma = turmaDao.consultar(codigo);
				if(turma != null) {
					System.out.println("A turma que deseja remover os alunos � esta?");
					System.out.println("==============================");
					System.out.println(turma);
					System.out.println("==============================");
					System.out.println("[1] Sim");
					System.out.println("[2] N�o");
					
					int opcao = sc.nextInt();
					sc.skip("\r\n");
					
					if(opcao == 1) {
						ArrayList<Aluno> alunosDaTurma = turmaAlunoDao.relatorio(turma);
						
						if(alunosDaTurma != null && !alunosDaTurma.isEmpty()) {
							System.out.println("...:::::[ LISTA DOS ALUNOS MATRICULADOS ]:::::...");
							for(Aluno aluno : alunosDaTurma) {
								System.out.println("------------------------------");
								System.out.println("Matr�cula: " + aluno.getMatricula() + " - Nome: " + aluno.getNome());
							}
							System.out.println("==============================");
							System.out.println("Selecione o aluno pela sua matr�cula: ");
							String matricula = sc.nextLine();
							Aluno aluno = alunoDao.consultar(matricula);
							
							if(aluno != null) {
								turmaAlunoDao.excluir(turma, aluno);
								System.out.println("SUCESSO: Aluno removido da turma!");
							}
							else {
								System.out.println("ERRO: Aluno n�o encontrado!");
							}	
						}
						else {
							System.out.println("AVISO: N�o h� alunos cadastrados NESSA TURMA.");
						}
					}
					else if(opcao == 2) {
						System.out.println("AVISO: Opera��o cancelada!");
					}
					else {
						System.out.println("AVISO: Op��o inv�lida!");
					}
				}
			}
			else {
				System.out.println("ERRO: N�o h� alunos cadastrados!");
			}
		}
		else {
			System.out.println("ERRO: N�o h� turmas cadastradas!");
		}
	}
	
	public void consultarAlunos() {
		System.out.println("==============================");
		System.out.println("CONSULTAR ALUNOS DA TURMA");
		
		turmas = turmaDao.relatorio();
		
		if(!turmas.isEmpty()) {
			
			imprimeTurmas(turmas, TipoRelatorio.SINTETICO);
			
			System.out.println("Escolha a turma pelo seu c�digo: ");
			int codigo = sc.nextInt();
			sc.skip("\r\n");
			
			Turma turma = turmaDao.consultar(codigo);
			
			if(turma != null) {
				System.out.println("A turma que deseja consultar os alunos � esta?");
				System.out.println("==============================");
				System.out.println(turma);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					ArrayList<Aluno> alunosDaTurma = turmaAlunoDao.relatorio(turma);
					
					if(alunosDaTurma != null && !alunosDaTurma.isEmpty()) {
						System.out.println("...:::::[ LISTA DOS ALUNOS MATRICULADOS ]:::::...");
						for(Aluno aluno : alunosDaTurma) {
							System.out.println("------------------------------");
							System.out.println("Matr�cula: " + aluno.getMatricula() + " - Nome: " + aluno.getNome());
						}
						System.out.println("==============================");
					}
					else {
						System.out.println("AVISO: N�o h� alunos cadastrados NESSA TURMA.");
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
				System.out.println("ERRO: Turma n�o encontrada!");
			}
		}
		else {
			System.out.println("AVISO: N�o h� turmas cadastradas NO BANCO DE TURMAS.");
		}
	}
}
