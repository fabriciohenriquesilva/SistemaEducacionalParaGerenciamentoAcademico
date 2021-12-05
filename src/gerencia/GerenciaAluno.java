package gerencia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import dao.AlunoDAO;
import dao.ProvaAlunoDAO;
import dao.ProvaDAO;
import dao.TurmaAlunoDAO;
import model.Aluno;
import model.Prova;
import model.TipoRelatorio;
import model.Turma;

public class GerenciaAluno {

	private ArrayList<Aluno> alunos;
	private AlunoDAO alunoDao;
	private TurmaAlunoDAO turmaAlunoDao;
	private ProvaDAO provaDao;
	private ProvaAlunoDAO provaAlunoDao;
	
	private ArrayList<Prova> provas;
	private Scanner sc;
	
	public GerenciaAluno() {
		this.alunoDao = new AlunoDAO();
		this.turmaAlunoDao = new TurmaAlunoDAO();
		this.provaDao = new ProvaDAO();
		this.provaAlunoDao = new ProvaAlunoDAO();
		sc = new Scanner(System.in);
	}
	
	public void adicionar() {
		
		System.out.println("==============================");
		System.out.println("CADASTRO DE ALUNOS");
		
		Aluno aluno = new Aluno();
		
		System.out.println("1. Digite o nome do aluno: ");
		aluno.setNome(sc.nextLine());
		
		System.out.println("2. Digite o CPF do aluno: ");
		aluno.setCpf(sc.nextLine());
		
		System.out.println("3. Digite a matr�cula do aluno (somente n�meros): ");
		aluno.setMatricula(sc.nextLine());
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("4. Digite a data da matr�cula (formato: dd/mm/yyyy): ");
		aluno.setDataMatricula(LocalDate.parse(sc.nextLine(), formato));
		
		alunoDao.inserir(aluno);
	}
	
	public void remover() {
		String matricula;
		boolean achou = false;
		int opcao;
		
		System.out.println("==============================");
		System.out.println("REMO��O DE ALUNOS");
		
		alunos = alunoDao.relatorio();
		
		if(alunos != null && !alunos.isEmpty()) {
			imprimeAlunos(alunos, TipoRelatorio.ANALITICO);
			System.out.println("Qual a matr�cula do aluno que deseja remover?");
			matricula = sc.nextLine();
			
			Aluno alu = null;
			for (Aluno a : alunos) {
				if(a.getMatricula().equals(matricula)) {
					achou = true;
					alu = a;
				}
			}
			
			if(achou) {
				System.out.println("==============================");
				System.out.println(alu);
				System.out.println("==============================");
				
				System.out.println("Confirma a remo��o?");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					alunoDao.excluir(alu);
					System.out.println("SUCESSO: Aluno removido!");
				}
				else if(opcao == 2) {
					System.out.println("ERRO: Opera��o cancelada!");
				}
				else {
					System.out.println("AVISO: Op��o inv�lida!");
				}
			}
			else {
				System.out.println("AVISO: Aluno n�o encontrado!");
			}
		}
		else {
			System.out.println("AVISO: N�o h� alunos cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void alterar() {
			
		String matricula;
		boolean achou = false;
		int opcao;
	
		System.out.println("==============================");
		System.out.println("ALTERA��O DE ALUNOS");
		
		alunos = alunoDao.relatorio();
		
		if(alunos != null && !alunos.isEmpty()) {
			imprimeAlunos(alunos, TipoRelatorio.ANALITICO);
			System.out.println("Qual a matr�cula do aluno que deseja alterar?");
			matricula = sc.nextLine();
			
			Aluno alu = null;
			for (Aluno a : alunos) {
				if(a.getMatricula().equals(matricula)) {
					achou = true;
					alu = a;
				}
			}
			
			if(achou) {
				System.out.println("==============================");
				System.out.println(alu);
				System.out.println("==============================");
				
				System.out.println("Confirma a altera��o?");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					System.out.println("Digite os novos dados:");
					System.out.println("1. Digite o nome do aluno: ");
					alu.setNome(sc.nextLine());
		
					System.out.println("2. Digite o CPF do aluno: ");
					alu.setCpf(sc.nextLine());
					
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					
					System.out.println("3. Digite a data da matr�cula (formato: dd/mm/yyyy): ");
					alu.setDataMatricula(LocalDate.parse(sc.nextLine(), formato));
					
					alunoDao.alterar(alu);
					System.out.println("SUCESSO: Aluno alterado!");
				}
				else if(opcao == 2) {
					System.out.println("ERRO: Opera��o cancelada!");
				}
				else {
					System.out.println("AVISO: Op��o inv�lida!");
				}
			}
			else {
				System.out.println("AVISO: Aluno n�o encontrado!");
			}
		}
		else {
			System.out.println("AVISO: N�o h� alunos cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void consultar() {
		String matricula;
		boolean achou = false;
		
		System.out.println("==============================");
		System.out.println("CONSULTA DE ALUNOS");
		
		alunos = alunoDao.relatorio();
		
		if(alunos != null && !alunos.isEmpty()) {
			imprimeAlunos(alunos, TipoRelatorio.ANALITICO);
			System.out.println("Qual a matr�cula do aluno que deseja consultar?");
			matricula = sc.nextLine();
			
			Aluno alu = null;
			for (Aluno a : alunos) {
				if(a.getMatricula().equals(matricula)) {
					achou = true;
					alu = a;
				}
			}
			
			if(achou) {
				System.out.println("==============================");
				System.out.println(alu);
				System.out.println("==============================");
			}
			else {
				System.out.println("AVISO: Aluno n�o encontrado!");
			}
		}
		else {
			System.out.println("AVISO: N�o h� alunos cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void gerarRelatorio() {
		
		System.out.println("==============================");
		System.out.println("RELAT�RIO DE ALUNOS");
		
		alunos = alunoDao.relatorio();
		
		if(alunos != null && !alunos.isEmpty()) {
			imprimeAlunos(alunos, TipoRelatorio.ANALITICO);
		}
		else {
			System.out.println("AVISO: N�o h� alunos cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void adicionarProva() {
		System.out.println("==============================");
		System.out.println("ADICIONAR PROVA DO ALUNO");
		
		alunos = alunoDao.relatorio();
		provas = provaDao.relatorio();
		
		if(alunos != null && !alunos.isEmpty()) {
			if(provas != null && !provas.isEmpty()) {
				System.out.println("------------------------------");
				System.out.println("ALUNOS CADASTRADOS");
				System.out.println("==============================");
				imprimeAlunos(alunos, TipoRelatorio.SINTETICO);
				System.out.println("Selecione o aluno pela sua matricula: ");
				String matricula = sc.nextLine();
				
				Aluno aluno = alunoDao.consultar(matricula);
				if(aluno != null) {
					System.out.println("------------------------------");
					System.out.println("PROVAS CADASTRADAS");
					System.out.println("==============================");
					
					for (Prova p : provas) {
						System.out.println("C�digo: " + p.getIdentificador() + " - Nome: " + p.getDisciplina().getNome() + 
								" C�digo da Turma: " + p.getTurma().getCodigo());
					}
					System.out.println("------------------------------");
					
					System.out.println("Selecione a prova pelo seu identificador: ");
					String identificador = sc.nextLine();
					
					Prova prova = provaDao.consultar(identificador);
					
					if(prova != null) {
						System.out.println("Qual foi a nota obtida por esse aluno nessa prova?");
						
						try {
							float nota = sc.nextFloat();
							sc.skip("\r\n");
							
							provaAlunoDao.inserir(aluno, prova, nota);
							System.out.println("SUCESSO: Prova cadastrada para o aluno!");
						}
						catch (Exception e) {
							System.out.println("ERRO: Leitura do teclado " + e.getMessage());
						}
					}
					else {
						System.out.println("ERRO: Prova n�o encontrada!");
					}
				}
				else {
					System.out.println("ERRO: Aluno n�o encontrado!");
				}
			}
			else {
				System.out.println("ERRO: N�o h� provas cadastradas!");
			}
		}
		else {
			System.out.println("ERRO: N�o h� alunos cadastrados!");
		}
	}
	
	public void removerProva() {
		System.out.println("==============================");
		System.out.println("REMOVER PROVA DO ALUNO");
		
		alunos = alunoDao.relatorio();
		provas = provaDao.relatorio();
		
		if(alunos != null && !alunos.isEmpty()) {
			if(provas != null && !provas.isEmpty()) {
				System.out.println("------------------------------");
				System.out.println("ALUNOS CADASTRADOS");
				System.out.println("==============================");
				imprimeAlunos(alunos, TipoRelatorio.SINTETICO);
				System.out.println("Selecione o aluno pela sua matricula: ");
				String matricula = sc.nextLine();
				
				Aluno aluno = alunoDao.consultar(matricula);
				if(aluno != null) {
					System.out.println("O aluno que deseja a prova � este?");
					System.out.println("==============================");
					System.out.println(aluno);
					System.out.println("==============================");
					System.out.println("[1] Sim");
					System.out.println("[2] N�o");
					
					int opcao = sc.nextInt();
					sc.skip("\r\n");
					
					if(opcao == 1) {
						ArrayList<Prova> provasDoAluno = provaAlunoDao.relatorio(aluno);
						
						if(provasDoAluno != null && !provasDoAluno.isEmpty()) {
							System.out.println("...:::::[ LISTA DE PROVAS DO ALUNO ]:::::...");
							for(Prova p : provasDoAluno) {
								System.out.println("C�digo: " + p.getIdentificador() + " - Nome: " + p.getDisciplina().getNome() + 
										"C�digo da Turma: " + p.getTurma().getCodigo());
							}
							System.out.println("==============================");
							System.out.println("Selecione a prova pelo seu identificador: ");
							String identificador = sc.nextLine();
							Prova prova = provaDao.consultar(identificador);
							
							if(prova != null) {
								provaAlunoDao.excluir(aluno, prova);
								System.out.println("SUCESSO: Prova removida do aluno!");
							}
							else {
								System.out.println("ERRO: Prova n�o encontrada!");
							}	
						}
						else {
							System.out.println("AVISO: N�o h� provas cadastrados para esse ALUNO!");
						}
					}
					else {
						System.out.println("ERRO: Prova n�o encontrada!");
					}
				}
				else {
					System.out.println("ERRO: Aluno n�o encontrado!");
				}
			}
			else {
				System.out.println("ERRO: N�o h� provas cadastradas!");
			}
		}
		else {
			System.out.println("ERRO: N�o h� alunos cadastrados!");
		}
	}
	
	public void consultarProvas() {
		System.out.println("==============================");
		System.out.println("CONSULTAR PROVAS DO ALUNO");
		
		alunos = alunoDao.relatorio();
		provas = provaDao.relatorio();
		
		if(alunos != null && !alunos.isEmpty()) {
			if(provas != null && !provas.isEmpty()) {
				System.out.println("------------------------------");
				System.out.println("ALUNOS CADASTRADOS");
				System.out.println("==============================");
				imprimeAlunos(alunos, TipoRelatorio.SINTETICO);
				System.out.println("Selecione o aluno pela sua matricula: ");
				String matricula = sc.nextLine();
				
				Aluno aluno = alunoDao.consultar(matricula);
				if(aluno != null) {
					System.out.println("O aluno que deseja a prova � este?");
					System.out.println("==============================");
					System.out.println(aluno);
					System.out.println("==============================");
					System.out.println("[1] Sim");
					System.out.println("[2] N�o");
					
					int opcao = sc.nextInt();
					sc.skip("\r\n");
					
					if(opcao == 1) {
						ArrayList<Prova> provasDoAluno = provaAlunoDao.relatorio(aluno);
						
						if(provasDoAluno != null && !provasDoAluno.isEmpty()) {
							System.out.println("...:::::[ LISTA DE PROVAS DO ALUNO ]:::::...");
							for(Prova p : provasDoAluno) {
								System.out.println("C�digo: " + p.getIdentificador() + " - Nome: " + p.getDisciplina().getNome() + 
										"C�digo da Turma: " + p.getTurma().getCodigo());
							}
						}
						else {
							System.out.println("AVISO: N�o h� provas cadastrados para esse ALUNO!");
						}
					}
					else {
						System.out.println("ERRO: Prova n�o encontrada!");
					}
				}
				else {
					System.out.println("ERRO: Aluno n�o encontrado!");
				}
			}
			else {
				System.out.println("ERRO: N�o h� provas cadastradas!");
			}
		}
		else {
			System.out.println("ERRO: N�o h� alunos cadastrados!");
		}
	}
	
	private void imprimeAlunos(ArrayList<Aluno> listaDeAlunos, TipoRelatorio tipo) {
		System.out.println("...:::::[ LISTA DE ALUNOS ]:::::...");
		
		if(tipo == TipoRelatorio.ANALITICO) {
			for(Aluno a : listaDeAlunos) {
				System.out.println(a);
				System.out.println("------------------------------");
			}
		}
		else {
			for(Aluno a : listaDeAlunos) {
				System.out.println("Matricula: " + a.getMatricula() + " - Nome: " + a.getNome());
			}
			System.out.println("------------------------------");
		}
	}
	
	public void consultarTurmas() {
		System.out.println("==============================");
		System.out.println("CONSULTAR TURMAS DO ALUNO");
		
		ArrayList<Aluno> alunos = alunoDao.relatorio();
		
		if(alunos != null && !alunos.isEmpty()) {
			imprimeAlunos(alunos, TipoRelatorio.SINTETICO);
			System.out.println("Escolha o aluno pela sua matr�cula: ");
			String matricula = sc.nextLine();
			
			Aluno aluno = alunoDao.consultar(matricula);
			
			if(aluno != null) {
				System.out.println("O aluno que deseja consultar as turmas � este?");
				System.out.println("==============================");
				System.out.println(aluno);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					ArrayList<Turma> turmasDoAluno = turmaAlunoDao.relatorioDeTumas(aluno);
					
					if(turmasDoAluno != null && !turmasDoAluno.isEmpty()) {
						System.out.println("...:::::[ ALUNO MATRICULADO EM ]:::::...");
						for(Turma turma : turmasDoAluno) {
							System.out.println("C�digo: " + turma.getCodigo() + " - Curso: " + turma.getCurso().getNome() + 
									"\nDisciplina: " + turma.getDisciplina().getNome() + " - Semestre/Ano: " + turma.getSemestre() + "/" + turma.getAno());
						
							System.out.println("------------------------------");
						}
						System.out.println("==============================");
					}
					else {
						System.out.println("AVISO: N�o h� turmas em que esse aluno esteja matriculado!");
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
				System.out.println("ERRO: Aluno n�o encontrado!");
			}
		}
		else {
			System.out.println("AVISO: N�o h� alunos cadastradas!");
		}
	}
}
