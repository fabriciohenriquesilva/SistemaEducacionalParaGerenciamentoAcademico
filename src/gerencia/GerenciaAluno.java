package gerencia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import dao.AlunoDAO;
import model.Aluno;
import model.Prova;
import model.Questao;

public class GerenciaAluno {

	private ArrayList<Aluno> alunos;
	private AlunoDAO alunoDao;
	
	private ArrayList<Prova> provas;
	private Scanner sc;
	
	public GerenciaAluno(ArrayList<Prova> provas) {
		this.provas = provas;
		alunos = null;
		alunoDao = new AlunoDAO();
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
		
		System.out.println("3. Digite a matrícula do aluno (somente números): ");
		aluno.setMatricula(sc.nextLine());
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("4. Digite a data da matrícula (formato: dd/mm/yyyy): ");
		aluno.setDataMatricula(LocalDate.parse(sc.nextLine(), formato));
		
		alunoDao.inserir(aluno);
		
		System.out.println("SUCESSO: Aluno adicionado!");
	}
	
	public void remover() {
		String matricula;
		boolean achou = false;
		int opcao;
		
		System.out.println("==============================");
		System.out.println("REMOÇÃO DE ALUNOS");
		
		alunos = alunoDao.relatorio();
		
		if(alunos != null && !alunos.isEmpty()) {
			imprimeAlunos(alunos);
			System.out.println("Qual a matrícula do aluno que deseja remover?");
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
				
				System.out.println("Confirma a remoção?");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					alunoDao.excluir(alu);
					System.out.println("SUCESSO: Aluno removido!");
				}
				else if(opcao == 2) {
					System.out.println("ERRO: Operação cancelada!");
				}
				else {
					System.out.println("AVISO: Operação de remoção de aluno cancelada!");
				}
			}
			else {
				System.out.println("AVISO: Aluno não encontrado!");
			}
		}
		else {
			System.out.println("AVISO: Não há alunos cadastrados NO BANCO DE ALUNOS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void alterar() {
			
		String matricula;
		boolean achou = false;
		int opcao;
	
		System.out.println("==============================");
		System.out.println("ALTERAÇÃO DE ALUNOS");
		
		alunos = alunoDao.relatorio();
		
		if(alunos != null && !alunos.isEmpty()) {
			imprimeAlunos(alunos);
			System.out.println("Qual a matrícula do aluno que deseja alterar?");
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
				
				System.out.println("Confirma a alteração?");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					System.out.println("Digite os novos dados:");
					System.out.println("1. Digite o nome do aluno: ");
					alu.setNome(sc.nextLine());
		
					System.out.println("2. Digite o CPF do aluno: ");
					alu.setCpf(sc.nextLine());
					
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					
					System.out.println("3. Digite a data da matrícula (formato: dd/mm/yyyy): ");
					alu.setDataMatricula(LocalDate.parse(sc.nextLine(), formato));
					
					alunoDao.alterar(alu);
					System.out.println("SUCESSO: Aluno alterado!");
				}
				else if(opcao == 2) {
					System.out.println("ERRO: Operação cancelada!");
				}
				else {
					System.out.println("AVISO: Operação de remoção de aluno cancelada!");
				}
			}
			else {
				System.out.println("AVISO: Aluno não encontrado!");
			}
		}
		else {
			System.out.println("AVISO: Não há alunos cadastrados NO BANCO DE ALUNOS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void consultar() {
		String matricula;
		boolean achou = false;
		
		System.out.println("==============================");
		System.out.println("CONSULTA DE ALUNOS");
		
		alunos = alunoDao.relatorio();
		
		if(alunos != null && !alunos.isEmpty()) {
			imprimeAlunos(alunos);
			System.out.println("Qual a matrícula do aluno que deseja consultar?");
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
				System.out.println("AVISO: Aluno não encontrado!");
			}
		}
		else {
			System.out.println("AVISO: Não há alunos cadastrados NO BANCO DE ALUNOS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void gerarRelatorio() {
		
		System.out.println("==============================");
		System.out.println("RELATÓRIO DE ALUNOS");
		
		alunos = alunoDao.relatorio();
		
		if(alunos != null && !alunos.isEmpty()) {
			imprimeAlunos(alunos);
		}
		else {
			System.out.println("AVISO: Não há alunos cadastrados NO BANCO DE ALUNOS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void adicionarProva() {
		System.out.println("==============================");
		System.out.println("ADICIONAR PROVA DO ALUNO");
		
		if(!alunos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha o aluno pela sua posição: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < alunos.size()) {
				Aluno aluno = alunos.get(pos);
				System.out.println("O aluno que deseja adicionar a prova é este?");
				System.out.println("==============================");
				System.out.println(aluno);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					if(!provas.isEmpty()) {
						
						System.out.println("==============================");
						System.out.println("BANCO DE PROVAS");
						for (Prova prova : provas) {
							System.out.println("Posição: #" + provas.indexOf(prova));
							System.out.println(prova);
							System.out.println("------------------------------");
						}
						
						System.out.println("Escolha a prova pela sua posição: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < provas.size()) {
							Prova prova = provas.get(pos);
							System.out.println("A prova que deseja adicionar no aluno é esta?");
							System.out.println("==============================");
							System.out.println(prova);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] Não");
							
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
								System.out.println("AVISO: Opção invpalida!");
							}
						}
						else {
							System.out.println("ERRO: Posição informada não é válida. Voltando ao menu inicial...");
						}
					}
					else {
						System.out.println("AVISO: Não há provas cadastradas no BANCO DE PROVAS. Impossível continuar operação. Voltando ao menu inicial...");
					}
				}
				else if(opcao == 2) {
					System.out.println("AVISO: Voltando ao menu inicial...");
				}
				else {
					System.out.println("AVISO: Opção inválida!");
				}
			}
			else {
				System.out.println("ERRO: Posição informada não é válida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: Não há alunos cadastrados NO BANCO DE ALUNOS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void removerProva() {
		System.out.println("==============================");
		System.out.println("REMOVER PROVA DO ALUNO");
		
		if(!alunos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha o aluno pela sua posição: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < alunos.size()) {
				Aluno aluno = alunos.get(pos);
				System.out.println("O aluno que deseja remover a prova é este?");
				System.out.println("==============================");
				System.out.println(aluno);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					ArrayList<Prova> provasDoAluno = aluno.getProvas();
					
					if(!provasDoAluno.isEmpty()) {
					
						System.out.println("==============================");
						for (Prova prova : provasDoAluno) {
							System.out.println("Posição: #" + provasDoAluno.indexOf(prova));
							System.out.println(prova);
							System.out.println("------------------------------");
						}
						
						System.out.println("Escolha a prova pela sua posição: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < provasDoAluno.size()) {
							Prova prova = provasDoAluno.get(pos);
							System.out.println("A prova que deseja remover do aluno é esta?");
							System.out.println("==============================");
							System.out.println(prova);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] Não");
							
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
								System.out.println("AVISO: Opção inválida!");
							}
						}
						else {
							System.out.println("ERRO: Posição informada não é válida. Voltando ao menu inicial...");
						}
					}
					else {
						System.out.println("AVISO: Não há provas cadastradas para ESSE ALUNO. Impossível continuar operação. Voltando ao menu inicial...");
					}
				}
				else if(opcao == 2) {
					System.out.println("AVISO: Voltando ao menu inicial...");
				}
				else {
					System.out.println("AVISO: Opção inválida!");
				}
			}
			else {
				System.out.println("ERRO: Posição informada não é válida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: Não há alunos cadastrados NO BANCO DE ALUNOS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void consultarProvas() {
		System.out.println("==============================");
		System.out.println("CONSULTAR PROVAS DO ALUNO");
		
		if(!alunos.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha o aluno pela sua posição: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < alunos.size()) {
				Aluno aluno = alunos.get(pos);
				System.out.println("O aluno que deseja consultar as provas é este?");
				System.out.println("==============================");
				System.out.println(aluno);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					ArrayList<Prova> provasDoAluno = aluno.getProvas();
					
					if(!provasDoAluno.isEmpty()) {
						for(Prova p : provasDoAluno) {
							System.out.println("Posição " + provasDoAluno.indexOf(p));
							System.out.println(p);

							System.out.println("------------------------------");
							System.out.println("Questões: ");
							
							ArrayList<Questao> questoesDaProvaDoAluno = p.getQuestoes();
							
							for (Questao questao : questoesDaProvaDoAluno) {
								System.out.println("Posição " + questoesDaProvaDoAluno.indexOf(questao));
								System.out.println(questao);
								System.out.println("------------------------------");
							}
						}
						System.out.println("==============================");
					}
					else {
						System.out.println("AVISO: Não há provas cadastradas NESSE ALUNO. Impossível continuar operação. Voltando ao menu inicial...");
					}
				}
				else if(opcao == 2) {
					System.out.println("AVISO: Voltando ao menu inicial...");
				}
				else {
					System.out.println("AVISO: Opção inválida!");
				}
			}
			else {
				System.out.println("ERRO: Posição informada não é válida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: Não há alunos cadastradas NO BANCO DE ALUNOS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	private void imprimeAlunos(ArrayList<Aluno> listaDeAlunos) {
		System.out.println("...:::::[ LISTA DE ALUNOS ]:::::...");
		
		System.out.println("------------------------------");		
		for(Aluno a : listaDeAlunos) {
			System.out.println("Matricula: " + a.getMatricula() + " - Nome: " + a.getNome());
		}
		System.out.println("------------------------------");
	}
}
