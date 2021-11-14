package gerencia;

import java.util.ArrayList;
import java.util.Scanner;

import model.Aluno;
import model.Curso;
import model.Disciplina;
import model.Professor;
import model.Turma;

public class GerenciaTurma {
	
	private ArrayList<Turma> turmas;
	private ArrayList<Aluno> alunos;
	private ArrayList<Professor> professores;
	private ArrayList<Disciplina> disciplinas;
	private ArrayList<Curso> cursos;
	private Scanner sc;
	
	public GerenciaTurma(ArrayList<Turma> turmas, ArrayList<Aluno> alunos, ArrayList<Professor> professores,
			ArrayList<Disciplina> disciplinas, ArrayList<Curso> cursos) {
		this.turmas = turmas;
		this.alunos = alunos;
		this.professores = professores;
		this.disciplinas = disciplinas;
		this.cursos = cursos;
		
		sc = new Scanner(System.in);
	}

	public void adicionar() {
		System.out.println("==============================");
		System.out.println("CADASTRO DE TURMA");
		
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
			
			lerDados(turma);	
			
			if(turmas.add(turma)) {
				System.out.println("SUCESSO: Turma cadastrada!");
			}
			else {
				System.out.println("ERRO: Não foi possível adicionar a disciplina!");
			}
		}
	}
	
	public void remover() {
		System.out.println("==============================");
		System.out.println("REMOÇÃO DE TURMA");
		
		if(!turmas.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Digite a posição da turma a ser removida: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < turmas.size()) {
				System.out.println("A turma que deseja remover é essa?");
				System.out.println("==============================");
				System.out.println(turmas.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					turmas.remove(pos);
					System.out.println("SUCESSO: Turma removida!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Operação de remoção de turma cancelada!");
				}
				else {
					System.out.println("ERRO: Opção inválida!");
				}
			}
			else {
				System.out.println("AVISO: Posição informada não é válida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: Não há turmas cadastradas. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERAÇÃO DE TURMA");
		
		if(!turmas.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Digite a posição da turma a ser alterada: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < turmas.size()) {
				System.out.println("A turma que deseja alterar é esta?");
				System.out.println("==============================");
				System.out.println(turmas.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					Turma turma = turmas.get(pos);
					
					lerDados(turma);
					
					System.out.println("SUCESSO: Turma alterada!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Operação de alteração de dados da turma cancelada! Voltando ao menu inicial...");
				}
				else {
					System.out.println("ERRO: Opção inválida!");
				}
			}
			else {
				System.out.println("AVISO: Posição informada não é válida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: Não há turmas cadastradas. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void consultar() {
		System.out.println("==============================");
		System.out.println("CONSULTA DE TURMAS");
		
		if(!turmas.isEmpty()) {
			System.out.println("Digite a posição da turma a ser consultada: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < turmas.size()) {
				System.out.println("==============================");
				System.out.println(turmas.get(pos));
				System.out.println("==============================");
			}
			else {
				System.out.println("AVISO: Posição informada não é válida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: Não há turmas cadastradas. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void gerarRelatorio() {
		System.out.println("==============================");
		System.out.println("RELATÓRIO DE TURMAS");
		
		if(!turmas.isEmpty()) {
			System.out.println("==============================");
			for (Turma turma : turmas) {
				System.out.println("Posição: #" + turmas.indexOf(turma));
				System.out.println(turma);
				System.out.println("------------------------------");
			}
		}
		else {
			System.out.println("AVISO: Não há turmas cadastradas. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	private void lerDados(Turma turma) {
		
		System.out.println("1. Digite o ano: ");
		turma.setAno(sc.nextInt());
		sc.skip("\r\n");
		
		System.out.println("2. Digite o semestre: ");
		turma.setSemestre(sc.nextInt());
		sc.skip("\r\n");
		
		System.out.println("==============================");
		System.out.println("CURSOS CADASTRADOS NO SISTEMA: ");
		for (Curso curso : cursos) {
			System.out.println("Posição #" + cursos.indexOf(curso));
			System.out.println(curso);
			System.out.println("------------------------------");	
		}
		
		System.out.println("3. Digite a posição do curso para adicionar na turma: ");
		int pos = sc.nextInt();
		sc.skip("\r\n");
		
		if(pos >= 0 && pos < cursos.size()) {
			turma.setCurso(cursos.get(pos));
			System.out.println("SUCESSO: Curso adicionado na turma!");
			
			System.out.println("==============================");
			System.out.println("PROFESSORES CADASTRADOS NO SISTEMA: ");
			for (Professor professor : professores) {
				System.out.println("Posição #" + professores.indexOf(professor));
				System.out.println(professor);
				System.out.println("------------------------------");	
			}
			
			System.out.println("4. Digite a posição do professor para adicionar na turma: ");
			pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < professores.size()) {
				turma.setProfessor(professores.get(pos));
				System.out.println("SUCESSO: Professor adicionado na turma!");
				
				System.out.println("==============================");
				System.out.println("DISCIPLINAS CADASTRADOS NO SISTEMA: ");
				for (Disciplina disciplina : disciplinas) {
					System.out.println("Posição #" + disciplinas.indexOf(disciplina));
					System.out.println(disciplina);
					System.out.println("------------------------------");	
				}
				
				System.out.println("5. Digite a posição da disciplina para adicionar na turma: ");
				pos = sc.nextInt();
				sc.skip("\r\n");
				
				if(pos >= 0 && pos < disciplinas.size()) {
					turma.setDisciplina(disciplinas.get(pos));
					System.out.println("SUCESSO: Disciplina adicionada na turma!");
					System.out.println("------------------------------");
				}
				else {
					System.out.println("ERRO: Posição informada não é válida. Voltando ao menu inicial...");
				}
			}
			else {
				System.out.println("ERRO: Posição informada não é válida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("ERRO: Posição informada não é válida. Voltando ao menu inicial...");
		}
	}
	
	public void adicionarAluno() {
		System.out.println("==============================");
		System.out.println("ADICIONAR ALUNO NA TURMA");
		
		if(!turmas.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha a turma pela sua posição: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < turmas.size()) {
				Turma turma = turmas.get(pos);
				System.out.println("A turma que deseja adicionar um aluno é esta?");
				System.out.println("==============================");
				System.out.println(turma);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					if(!alunos.isEmpty()) {
						
						System.out.println("==============================");
						System.out.println("BANCO DE ALUNOS");
						for (Aluno a : alunos) {
							System.out.println("Posição " + alunos.indexOf(a));
							System.out.println(a);
							System.out.println("------------------------------");
						}
						
						System.out.println("Escolha o aluno pela sua posição: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < alunos.size()) {
							Aluno aluno = alunos.get(pos);
							System.out.println("O aluno que deseja adicionar na turma é esta?");
							System.out.println("==============================");
							System.out.println(aluno);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] Não");
							
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
								System.out.println("AVISO: Opção inválida!");
							}
						}
						else {
							System.out.println("ERRO: Posição informada não é válida. Voltando ao menu inicial...");
						}
					}
					else {
						System.out.println("AVISO: Não há alunos cadastradas. Impossível continuar operação. Voltando ao menu inicial...");
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
			System.out.println("AVISO: Não há turmas cadastrados NO BANCO DE TURMAS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void removerAluno() {
		System.out.println("==============================");
		System.out.println("REMOVER ALUNO DA TURMA");
		
		if(!turmas.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha a turma pela sua posição: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < turmas.size()) {
				Turma turma = turmas.get(pos);
				System.out.println("A turma que deseja remover o aluno é essa?");
				System.out.println("==============================");
				System.out.println(turma);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					ArrayList<Aluno> alunosDaTurma = turma.getAlunos();
					
					if(!alunosDaTurma.isEmpty()) {
						
						System.out.println("==============================");
						for (Aluno alu : alunosDaTurma) {
							System.out.println("Posição " + alunosDaTurma.indexOf(alu));
							System.out.println(alu);
							System.out.println("------------------------------");
						}
						
						System.out.println("Escolha o aluno pela sua posição: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < alunosDaTurma.size()) {
							Aluno aluno = alunosDaTurma.get(pos);
							System.out.println("O aluno que deseja remover da turma é esse?");
							System.out.println("==============================");
							System.out.println(aluno);
							System.out.println("==============================");
							System.out.println("[1] Sim");
							System.out.println("[2] Não");
							
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
								System.out.println("AVISO: Opção inválida!");
							}
						}
						else {
							System.out.println("ERRO: Posição informada não é válida. Voltando ao menu inicial...");
						}
					}
					else {
						System.out.println("AVISO: Não há alunos cadastrados NESSA TURMA. Impossível continuar operação. Voltando ao menu inicial...");
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
			System.out.println("AVISO: Não há turmas cadastradas NO BANCO DE TURMAS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}
	
	public void consultarAlunos() {
		System.out.println("==============================");
		System.out.println("CONSULTAR ALUNOS DA TURMA");
		
		if(!turmas.isEmpty()) {
			
			gerarRelatorio();
			
			System.out.println("Escolha a turma pela sua posição: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < turmas.size()) {
				Turma turma = turmas.get(pos);
				System.out.println("A turma que deseja consultar os alunos é esta?");
				System.out.println("==============================");
				System.out.println(turma);
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] Não");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					
					ArrayList<Aluno> alunosDaTurma = turma.getAlunos();
					
					if(!alunosDaTurma.isEmpty()) {
						for(Aluno aluno : alunosDaTurma) {
							System.out.println("------------------------------");
							System.out.println("Posição " + alunosDaTurma.indexOf(aluno));
							System.out.println(aluno);
						}
						System.out.println("==============================");
					}
					else {
						System.out.println("AVISO: Não há alunos cadastrados NESSA TURMA. Impossível continuar operação. Voltando ao menu inicial...");
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
			System.out.println("AVISO: Não há turmas cadastradas NO BANCO DE TURMAS. Impossível continuar operação. Voltando ao menu inicial...");
		}
	}

}
