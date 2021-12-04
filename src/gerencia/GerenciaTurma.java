package gerencia;

import java.util.ArrayList;
import java.util.Scanner;

import dao.CursoDAO;
import dao.DisciplinaDAO;
import dao.ProfessorDAO;
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
	
	private Scanner sc;
	
	public GerenciaTurma() {
		this.turmaDao = new TurmaDAO();
		this.disciplinaDao = new DisciplinaDAO();
		this.professorDao = new ProfessorDAO();
		this.cursoDao = new CursoDAO();
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
			System.out.println("AVISO: N�o h� turmas cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
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
			System.out.println("AVISO: N�o h� turmas cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
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
			System.out.println("AVISO: N�o h� turmas cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
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
			System.out.println("AVISO: N�o h� turmas cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
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
		
		if(!turmas.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha a turma pela sua posi��o: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < turmas.size()) {
				Turma turma = turmas.get(pos);
				System.out.println("A turma que deseja adicionar um aluno � esta?");
				System.out.println("==============================");
				System.out.println(turma);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					if(!alunos.isEmpty()) {
						
						System.out.println("==============================");
						System.out.println("BANCO DE ALUNOS");
						for (Aluno a : alunos) {
							System.out.println("Posi��o " + alunos.indexOf(a));
							System.out.println(a);
							System.out.println("------------------------------");
						}
						
						System.out.println("Escolha o aluno pela sua posi��o: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < alunos.size()) {
							Aluno aluno = alunos.get(pos);
							System.out.println("O aluno que deseja adicionar na turma � esta?");
							System.out.println("==============================");
							System.out.println(aluno);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] N�o");
							
							opcao = sc.nextInt();
							sc.skip("\r\n");
							
							if(opcao == 1) {
								turma.adicionarAluno(aluno);
								System.out.println("SUCESSO: Aluno adicionado a turma!");
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
						System.out.println("AVISO: N�o h� alunos cadastradas. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
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
			System.out.println("AVISO: N�o h� turmas cadastrados NO BANCO DE TURMAS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void removerAluno() {
		System.out.println("==============================");
		System.out.println("REMOVER ALUNO DA TURMA");
		
		if(!turmas.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha a turma pela sua posi��o: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < turmas.size()) {
				Turma turma = turmas.get(pos);
				System.out.println("A turma que deseja remover o aluno � essa?");
				System.out.println("==============================");
				System.out.println(turma);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					ArrayList<Aluno> alunosDaTurma = turma.getAlunos();
					
					if(!alunosDaTurma.isEmpty()) {
						
						System.out.println("==============================");
						for (Aluno alu : alunosDaTurma) {
							System.out.println("Posi��o " + alunosDaTurma.indexOf(alu));
							System.out.println(alu);
							System.out.println("------------------------------");
						}
						
						System.out.println("Escolha o aluno pela sua posi��o: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < alunosDaTurma.size()) {
							Aluno aluno = alunosDaTurma.get(pos);
							System.out.println("O aluno que deseja remover da turma � esse?");
							System.out.println("==============================");
							System.out.println(aluno);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] N�o");
							
							opcao = sc.nextInt();
							sc.skip("\r\n");
							
							if(opcao == 1) {
								alunosDaTurma.remove(pos);
								System.out.println("SUCESSO: Aluno removido da turma!");
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
						System.out.println("AVISO: N�o h� alunos cadastrados NESSA TURMA. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
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
			System.out.println("AVISO: N�o h� turmas cadastradas NO BANCO DE TURMAS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void consultarAlunos() {
		System.out.println("==============================");
		System.out.println("CONSULTAR ALUNOS DA TURMA");
		
		if(!turmas.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha a turma pela sua posi��o: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < turmas.size()) {
				Turma turma = turmas.get(pos);
				System.out.println("A turma que deseja consultar os alunos � esta?");
				System.out.println("==============================");
				System.out.println(turma);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					ArrayList<Aluno> alunosDaTurma = turma.getAlunos();
					
					if(!alunosDaTurma.isEmpty()) {
						for(Aluno aluno : alunosDaTurma) {
							System.out.println("------------------------------");
							System.out.println("Posi��o " + alunosDaTurma.indexOf(aluno));
							System.out.println(aluno);
						}
						System.out.println("==============================");
					}
					else {
						System.out.println("AVISO: N�o h� alunos cadastrados NESSA TURMA. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
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
			System.out.println("AVISO: N�o h� turmas cadastradas NO BANCO DE TURMAS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
}
