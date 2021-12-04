package gerencia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import dao.AlunoDAO;
import model.Aluno;
import model.Prova;
import model.Questao;
import model.TipoRelatorio;

public class GerenciaAluno {

	private ArrayList<Aluno> alunos;
	private AlunoDAO alunoDao;
	
	private ArrayList<Prova> provas;
	private Scanner sc;
	
	public GerenciaAluno() {
		this.alunoDao = new AlunoDAO();
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
		
		if(!alunos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha o aluno pela sua posi��o: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < alunos.size()) {
				Aluno aluno = alunos.get(pos);
				System.out.println("O aluno que deseja adicionar a prova � este?");
				System.out.println("==============================");
				System.out.println(aluno);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					if(!provas.isEmpty()) {
						
						System.out.println("==============================");
						System.out.println("BANCO DE PROVAS");
						for (Prova prova : provas) {
							System.out.println("Posi��o: #" + provas.indexOf(prova));
							System.out.println(prova);
							System.out.println("------------------------------");
						}
						
						System.out.println("Escolha a prova pela sua posi��o: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < provas.size()) {
							Prova prova = provas.get(pos);
							System.out.println("A prova que deseja adicionar no aluno � esta?");
							System.out.println("==============================");
							System.out.println(prova);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] N�o");
							
							opcao = sc.nextInt();
							sc.skip("\r\n");
							
							if(opcao == 1) {
								aluno.adicionarProva(prova);
								System.out.println("SUCESSO: Prova adicionada ao aluno!");
							}
							else if(opcao == 2) {
								System.out.println("AVISO: Voltando ao menu inicial...");
							}
							else {
								System.out.println("AVISO: Op��o invpalida!");
							}
						}
						else {
							System.out.println("ERRO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
						}
					}
					else {
						System.out.println("AVISO: N�o h� provas cadastradas no BANCO DE PROVAS. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
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
			System.out.println("AVISO: N�o h� alunos cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void removerProva() {
		System.out.println("==============================");
		System.out.println("REMOVER PROVA DO ALUNO");
		
		if(!alunos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha o aluno pela sua posi��o: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < alunos.size()) {
				Aluno aluno = alunos.get(pos);
				System.out.println("O aluno que deseja remover a prova � este?");
				System.out.println("==============================");
				System.out.println(aluno);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					ArrayList<Prova> provasDoAluno = aluno.getProvas();
					
					if(!provasDoAluno.isEmpty()) {
					
						System.out.println("==============================");
						for (Prova prova : provasDoAluno) {
							System.out.println("Posi��o: #" + provasDoAluno.indexOf(prova));
							System.out.println(prova);
							System.out.println("------------------------------");
						}
						
						System.out.println("Escolha a prova pela sua posi��o: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < provasDoAluno.size()) {
							Prova prova = provasDoAluno.get(pos);
							System.out.println("A prova que deseja remover do aluno � esta?");
							System.out.println("==============================");
							System.out.println(prova);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] N�o");
							
							opcao = sc.nextInt();
							sc.skip("\r\n");
							
							if(opcao == 1) {
								aluno.getProvas().remove(pos);
								System.out.println("SUCESSO: Prova removida do aluno!");
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
						System.out.println("AVISO: N�o h� provas cadastradas para ESSE ALUNO. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
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
			System.out.println("AVISO: N�o h� alunos cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void consultarProvas() {
		System.out.println("==============================");
		System.out.println("CONSULTAR PROVAS DO ALUNO");
		
		if(!alunos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha o aluno pela sua posi��o: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < alunos.size()) {
				Aluno aluno = alunos.get(pos);
				System.out.println("O aluno que deseja consultar as provas � este?");
				System.out.println("==============================");
				System.out.println(aluno);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					ArrayList<Prova> provasDoAluno = aluno.getProvas();
					
					if(!provasDoAluno.isEmpty()) {
						for(Prova p : provasDoAluno) {
							System.out.println("Posi��o " + provasDoAluno.indexOf(p));
							System.out.println(p);

							System.out.println("------------------------------");
							System.out.println("Quest�es: ");
							
							ArrayList<Questao> questoesDaProvaDoAluno = p.getQuestoes();
							
							for (Questao questao : questoesDaProvaDoAluno) {
								System.out.println("Posi��o " + questoesDaProvaDoAluno.indexOf(questao));
								System.out.println(questao);
								System.out.println("------------------------------");
							}
						}
						System.out.println("==============================");
					}
					else {
						System.out.println("AVISO: N�o h� provas cadastradas NESSE ALUNO. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
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
			System.out.println("AVISO: N�o h� alunos cadastradas. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
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
}
