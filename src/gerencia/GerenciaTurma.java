package gerencia;

import java.util.ArrayList;
import java.util.Scanner;

import model.Aluno;
import model.Curso;
import model.Disciplina;
import model.Professor;
import model.Turma;

public class GerenciaTurma implements Gerencia {
	
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
			
			lerDados(turma);	
			
			if(turmas.add(turma)) {
				System.out.println("SUCESSO: Turma cadastrada!");
			}
			else {
				System.out.println("ERRO: N�o foi poss�vel adicionar a disciplina!");
			}
		}
	}
	
	public void remover() {
		System.out.println("==============================");
		System.out.println("REMO��O DE TURMA");
		
		if(!turmas.isEmpty()) {
			
			System.out.println("Digite a posi��o da turma a ser removida: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < turmas.size()) {
				System.out.println("A turma que deseja remover � essa?");
				System.out.println("==============================");
				System.out.println(turmas.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					turmas.remove(pos);
					System.out.println("SUCESSO: Turma removida!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Opera��o de remo��o de turma cancelada!");
				}
				else {
					System.out.println("ERRO: Op��o inv�lida!");
				}
			}
			else {
				System.out.println("AVISO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: N�o h� turmas cadastradas. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void alterar() {
		
		System.out.println("==============================");
		System.out.println("ALTERA��O DE TURMA");
		
		if(!turmas.isEmpty()) {
			System.out.println("Digite a posi��o da turma a ser alterada: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < turmas.size()) {
				System.out.println("A turma que deseja alterar � esta?");
				System.out.println("==============================");
				System.out.println(turmas.get(pos));
				System.out.println("==============================");
				System.out.println("[1] Sim");
				System.out.println("[2] N�o");
				
				int opcao = sc.nextInt();
				sc.skip("\r\n");
				
				if(opcao == 1) {
					Turma turma = turmas.get(pos);
					
					lerDados(turma);
					
					System.out.println("SUCESSO: Turma alterada!");
				}
				else if(opcao == 2){
					System.out.println("AVISO: Opera��o de altera��o de dados da turma cancelada! Voltando ao menu inicial...");
				}
				else {
					System.out.println("ERRO: Op��o inv�lida!");
				}
			}
			else {
				System.out.println("AVISO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: N�o h� turmas cadastradas. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void consultar() {
		System.out.println("==============================");
		System.out.println("CONSULTA DE TURMAS");
		
		if(!turmas.isEmpty()) {
			System.out.println("Digite a posi��o da turma a ser consultada: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < turmas.size()) {
				System.out.println("==============================");
				System.out.println(turmas.get(pos));
				System.out.println("==============================");
			}
			else {
				System.out.println("AVISO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: N�o h� turmas cadastradas. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void gerarRelatorio() {
		System.out.println("==============================");
		System.out.println("RELAT�RIO DE TURMAS");
		
		if(!turmas.isEmpty()) {
			System.out.println("==============================");
			for (Turma turma : turmas) {
				System.out.println(turma);
				System.out.println("------------------------------");
			}
		}
		else {
			System.out.println("AVISO: N�o h� turmas cadastradas. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	private void lerDados(Turma turma) {
		
		System.out.println("1. Digite o ano: ");
		turma.setAno(sc.nextInt());
		sc.skip("\r\n");
		
		System.out.println("2. Digite o semestre: ");
		turma.setSemestre(sc.nextInt());
		sc.skip("\r\n");
		
		System.out.println("CURSOS CADASTRADOS NO SISTEMA: ");
		for (Curso curso : cursos) {
			System.out.println("Posi��o #" + cursos.indexOf(curso));
			System.out.println(curso);
			System.out.println("------------------------------");	
		}
		
		System.out.println("3. Digite a posi��o do curso para adicionar na turma: ");
		int pos = sc.nextInt();
		sc.skip("\r\n");
		
		if(pos >= 0 && pos < cursos.size()) {
			turma.setCurso(cursos.get(pos));
			System.out.println("SUCESSO: Curso adicionado na turma!");
			
			System.out.println("PROFESSORES CADASTRADOS NO SISTEMA: ");
			for (Professor professor : professores) {
				System.out.println("Posi��o #" + professores.indexOf(professor));
				System.out.println(professor);
				System.out.println("------------------------------");	
			}
			
			System.out.println("4. Digite a posi��o do professor para adicionar na turma: ");
			pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < professores.size()) {
				turma.setProfessor(professores.get(pos));
				System.out.println("SUCESSO: Professor adicionado na turma!");
				
				System.out.println("DISCIPLINAS CADASTRADOS NO SISTEMA: ");
				for (Disciplina disciplina : disciplinas) {
					System.out.println("Posi��o #" + disciplinas.indexOf(disciplina));
					System.out.println(disciplina);
					System.out.println("------------------------------");	
				}
				
				System.out.println("5. Digite a posi��o da disciplina para adicionar na turma: ");
				pos = sc.nextInt();
				sc.skip("\r\n");
				
				if(pos >= 0 && pos < disciplinas.size()) {
					turma.setDisciplina(disciplinas.get(pos));
					System.out.println("SUCESSO: Disciplina adicionada na turma!");
				}
				else {
					System.out.println("ERRO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
				}
			}
			else {
				System.out.println("ERRO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("ERRO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
		}
	}
	
	public void adicionarAluno() {
		System.out.println("==============================");
		System.out.println("ADICIONAR ALUNO NA TURMA");
		
		if(!turmas.isEmpty()) {
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
				
				if(!alunos.isEmpty()) {
					if(opcao == 1) {
						System.out.println("Escolha o aluno pela sua posi��o: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < alunos.size()) {
							Aluno aluno = alunos.get(pos);
							System.out.println("A prova que deseja adicionar no aluno � esta?");
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
							else {
								System.out.println("AVISO: Opera��o cancelada!");
							}
						}
						else {
							System.out.println("ERRO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
						}
					}
					else {
						System.out.println("AVISO: Opera��o cancelada!");
					}
				}
				else {
					System.out.println("AVISO: N�o h� alunos cadastradas. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
				}
			}
			else {
				System.out.println("ERRO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: N�o h� turmas cadastrados. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void removerAluno() {
		System.out.println("==============================");
		System.out.println("REMOVER ALUNO DA TURMA");
		
		if(!turmas.isEmpty()) {
			System.out.println("Escolha o curso pela sua posi��o: ");
			int pos = sc.nextInt();
			sc.skip("\r\n");
			
			if(pos >= 0 && pos < turmas.size()) {
				Turma turma = turmas.get(pos);
				System.out.println("A turma que deseja remover a o aluno � essa?");
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
						System.out.println("Escolha o aluno pela sua posi��o: ");
						pos = sc.nextInt();
						sc.skip("\r\n");
						
						if(pos >= 0 && pos < alunosDaTurma.size()) {
							Aluno aluno = alunosDaTurma.get(pos);
							System.out.println("A disciplina que deseja remover do curso � essa?");
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
							else {
								System.out.println("AVISO: Opera��o cancelada!");
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
				else {
					System.out.println("AVISO: Opera��o cancelada!");
				}
			}
			else {
				System.out.println("ERRO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: N�o h� turmas cadastradas. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}
	
	public void consultarAlunos() {
		System.out.println("==============================");
		System.out.println("CONSULTAR ALUNOS DA TURMA");
		
		if(!turmas.isEmpty()) {
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
						if(pos >= 0 && pos < alunosDaTurma.size()) {
							for(Aluno aluno : alunosDaTurma) {
								System.out.println("------------------------------");
								System.out.println("Posi��o " + alunosDaTurma.indexOf(aluno));
								System.out.println(aluno);
							}
							System.out.println("==============================");
						}
						else {
							System.out.println("ERRO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
						}
					}
					else {
						System.out.println("AVISO: N�o h� alunos cadastrados NESSA TURMA. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
					}
				}
				else {
					System.out.println("AVISO: Opera��o cancelada!");
				}
			}
			else {
				System.out.println("ERRO: Posi��o informada n�o � v�lida. Voltando ao menu inicial...");
			}
		}
		else {
			System.out.println("AVISO: N�o h� turmas cadastradas. Imposs�vel continuar opera��o. Voltando ao menu inicial...");
		}
	}

}
