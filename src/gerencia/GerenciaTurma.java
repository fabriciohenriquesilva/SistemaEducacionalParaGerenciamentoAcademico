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
			System.out.println("AVISO: Não há cursos cadastrados! Voltando ao menu inicial...");
		}
		else if(professores.isEmpty()) {
			System.out.println("AVISO: Não há professores cadastrados. Voltando ao menu inicial...");
		}
		else if(disciplinas.isEmpty()) {
			System.out.println("AVISO: Não há disciplinas cadastradas. Voltando ao menu inicial...");
		}
		else {
			Turma turma = new Turma();
			
			if(lerDados(turma)) {
				turmaDao.inserir(turma);
			}
			else {
				System.out.println("ERRO: Turma não inserida!");
			}
		}
	}
	
	public void remover() {
		System.out.println("==============================");
		System.out.println("REMOÇÃO DE TURMA");
		
		int codigo;
		boolean achou = false;
		int opcao;
		
		turmas = turmaDao.relatorio();
		
		if(turmas != null && !turmas.isEmpty()) {
			imprimeTurmas(turmas, TipoRelatorio.SINTETICO);
			System.out.println("Qual o código da turma que deseja remover?");
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
				
				System.out.println("Confirma a remoção?");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					turmaDao.excluir(turma);
					System.out.println("SUCESSO: Turma removida!");
				}
				else if(opcao == 2) {
					System.out.println("ERRO: Operação cancelada!");
				}
				else {
					System.out.println("AVISO: Opção inválida!");
				}
			}
			else {
				System.out.println("AVISO: Turma não encontrada!");
			}
		}
		else {
			System.out.println("AVISO: Não há turmas cadastrados.");
		}
	}
	
	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERAÇÃO DE TURMA");
		
		int codigo;
		boolean achou = false;
		int opcao;
		
		turmas = turmaDao.relatorio();
		
		if(turmas != null && !turmas.isEmpty()) {
			imprimeTurmas(turmas, TipoRelatorio.SINTETICO);
			System.out.println("Qual o código da turma que deseja alterar?");
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
				
				System.out.println("Confirma a alteração?");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					if(lerDados(turma)) {
						turmaDao.alterar(turma);
						System.out.println("SUCESSO: Turma alterada!");
					}
					else {
						System.out.println("ERRO: Turma não alterada!");
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
				System.out.println("AVISO: Turma não encontrada!");
			}
		}
		else {
			System.out.println("AVISO: Não há turmas cadastrados.");
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
			System.out.println("Qual o código da turma que deseja consultar?");
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
				System.out.println("AVISO: Turma não encontrada!");
			}
		}
		else {
			System.out.println("AVISO: Não há turmas cadastrados.");
		}
	}
	
	public void gerarRelatorio() {
		System.out.println("==============================");
		System.out.println("RELATÓRIO DE TURMAS");
		
		turmas = turmaDao.relatorio();
		
		if(turmas != null && !turmas.isEmpty()) {
			imprimeTurmas(turmas, TipoRelatorio.ANALITICO);
		}
		else {
			System.out.println("AVISO: Não há turmas cadastrados.");
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
			System.out.println("Código: " + c.getCodigo() + " Nome: " + c.getNome());
		}
		System.out.println("------------------------------");
		System.out.println("3. Digite o código do curso para adicionar na turma: ");
		String codCurso = sc.nextLine();
		Curso curso = cursoDao.consultar(codCurso);
		
		if(curso != null) {
			turma.setCurso(curso);
			System.out.println("SUCESSO: Curso adicionado na turma!");
			
			System.out.println("==============================");
			System.out.println("PROFESSORES CADASTRADOS NO SISTEMA: ");
			professores = professorDao.relatorio();
			
			for (Professor p : professores) {
				System.out.println("Código: " + p.getCodigo() + " Nome: " + p.getNome());
			}
			System.out.println("------------------------------");	
			System.out.println("4. Digite o código do professor para adicionar na turma: ");
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
					System.out.println("Código: " + d.getCodigo() + " Nome: " + d.getNome());
				}
				System.out.println("------------------------------");	
				System.out.println("5. Digite o código da disciplina para adicionar na turma: ");
				
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
				System.out.println("ERRO: Professor não encontrado!");
			}
		}
		else {
			System.out.println("ERRO: Curso não encontrado!");
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
				System.out.println("Código: " + turma.getCodigo() + " - Curso: " + turma.getCurso().getNome() + 
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
				System.out.println("Selecione a turma pelo seu código: ");
				int codigo = sc.nextInt();
				sc.skip("\r\n");
				
				Turma turma = turmaDao.consultar(codigo);
				if(turma != null) {
					System.out.println("------------------------------");
					System.out.println("ALUNOS CADASTRADOS");
					System.out.println("==============================");
					
					for (Aluno a : alunos) {
						System.out.println("Matrícula: " + a.getMatricula() + " - Nome: " + a.getNome());
					}
					System.out.println("------------------------------");
					
					System.out.println("Selecione o aluno pela sua matrícula: ");
					String matricula = sc.nextLine();
					
					Aluno aluno = alunoDao.consultar(matricula);
					
					if(aluno != null) {
						turmaAlunoDao.inserir(turma, aluno);
						System.out.println("SUCESSO: Aluno inserido na turma!");
					}
					else {
						System.out.println("ERRO: Aluno não encontrado!");
					}
				}
				else {
					System.out.println("ERRO: Turma não encontrada!");
				}
			}
			else {
				System.out.println("ERRO: Não há alunos cadastrados!");
			}
		}
		else {
			System.out.println("ERRO: Não há turmas cadastradas!");
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
				System.out.println("Selecione a turma pelo seu código: ");
				int codigo = sc.nextInt();
				sc.skip("\r\n");
				
				Turma turma = turmaDao.consultar(codigo);
				if(turma != null) {
					System.out.println("A turma que deseja remover os alunos é esta?");
					System.out.println("==============================");
					System.out.println(turma);
					System.out.println("==============================");
					System.out.println("[1] Sim");
					System.out.println("[2] Não");
					
					int opcao = sc.nextInt();
					sc.skip("\r\n");
					
					if(opcao == 1) {
						ArrayList<Aluno> alunosDaTurma = turmaAlunoDao.relatorio(turma);
						
						if(alunosDaTurma != null && !alunosDaTurma.isEmpty()) {
							System.out.println("...:::::[ LISTA DOS ALUNOS MATRICULADOS ]:::::...");
							for(Aluno aluno : alunosDaTurma) {
								System.out.println("------------------------------");
								System.out.println("Matrícula: " + aluno.getMatricula() + " - Nome: " + aluno.getNome());
							}
							System.out.println("==============================");
							System.out.println("Selecione o aluno pela sua matrícula: ");
							String matricula = sc.nextLine();
							Aluno aluno = alunoDao.consultar(matricula);
							
							if(aluno != null) {
								turmaAlunoDao.excluir(turma, aluno);
								System.out.println("SUCESSO: Aluno removido da turma!");
							}
							else {
								System.out.println("ERRO: Aluno não encontrado!");
							}	
						}
						else {
							System.out.println("AVISO: Não há alunos cadastrados NESSA TURMA.");
						}
					}
					else if(opcao == 2) {
						System.out.println("AVISO: Operação cancelada!");
					}
					else {
						System.out.println("AVISO: Opção inválida!");
					}
				}
			}
			else {
				System.out.println("ERRO: Não há alunos cadastrados!");
			}
		}
		else {
			System.out.println("ERRO: Não há turmas cadastradas!");
		}
	}
	
	public void consultarAlunos() {
		System.out.println("==============================");
		System.out.println("CONSULTAR ALUNOS DA TURMA");
		
		turmas = turmaDao.relatorio();
		
		if(turmas != null && !turmas.isEmpty()) {
			
			imprimeTurmas(turmas, TipoRelatorio.SINTETICO);
			
			System.out.println("Escolha a turma pelo seu código: ");
			int codigo = sc.nextInt();
			sc.skip("\r\n");
			
			Turma turma = turmaDao.consultar(codigo);
			
			if(turma != null) {
				System.out.println("A turma que deseja consultar os alunos é esta?");
				System.out.println("==============================");
				System.out.println(turma);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					ArrayList<Aluno> alunosDaTurma = turmaAlunoDao.relatorio(turma);
					
					if(alunosDaTurma != null && !alunosDaTurma.isEmpty()) {
						System.out.println("...:::::[ LISTA DOS ALUNOS MATRICULADOS ]:::::...");
						for(Aluno aluno : alunosDaTurma) {
							System.out.println("------------------------------");
							System.out.println("Matrícula: " + aluno.getMatricula() + " - Nome: " + aluno.getNome());
						}
						System.out.println("==============================");
					}
					else {
						System.out.println("AVISO: Não há alunos cadastrados NESSA TURMA.");
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
				System.out.println("ERRO: Turma não encontrada!");
			}
		}
		else {
			System.out.println("AVISO: Não há turmas cadastradas NO BANCO DE TURMAS.");
		}
	}
}
