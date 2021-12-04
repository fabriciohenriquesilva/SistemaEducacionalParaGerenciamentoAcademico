package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Curso;
import model.Disciplina;
import model.Professor;
import model.Turma;

public class TurmaDAO {
	
	private Connection conexao = null;
	
	public TurmaDAO() {
		try {
			conexao = ConexaoBanco.getConexao();
		} catch (SQLException e) {
			System.out.println("ERRO: Conectar ao banco de dados. "  + e.getMessage());
		}
	}
	
	public void inserir(Turma turma) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "INSERT INTO turma (codcurso, codprofessor, coddisciplina, ano, semestre) VALUES (?, ?, ?, ?, ?)";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, turma.getCurso().getCodigo());
			ps.setInt(2, turma.getProfessor().getCodigo());
			ps.setString(3, turma.getDisciplina().getCodigo());
			ps.setInt(4, turma.getAno());
			ps.setInt(5, turma.getSemestre());
			ps.execute();
			ps.close();
			System.out.println("SUCESSO: Turma adicionada!");
		}
		catch(Exception e) {
			System.out.println("ERRO: Inserir TURMA no banco de dados. " + e.getMessage());
		}
	}
	
	public void excluir(Turma turma) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "DELETE FROM turma WHERE codigo = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setInt(1, turma.getCodigo());
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("ERRO: Excluir TURMA no banco de dados. " + e.getMessage());
		}
	}
	
	public void alterar(Turma turma) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "UPDATE turma SET codcurso = ?, codprofessor = ?, coddisciplina = ?, ano = ?, semestre = ? WHERE codigo = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, turma.getCurso().getCodigo());
			ps.setInt(2, turma.getProfessor().getCodigo());
			ps.setString(3, turma.getDisciplina().getCodigo());
			ps.setInt(4, turma.getAno());
			ps.setInt(5, turma.getSemestre());
			ps.setInt(6, turma.getCodigo());
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("ERRO: Alterar TURMA no banco de dados. " + e.getMessage());
		}
	}
	
	public Turma consultar(int codigo) {
		String sql;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Turma turma = null;
		
		Curso curso = null;
		Professor professor = null;
		Disciplina disciplina = null;		
		
		CursoDAO cursoDao = new CursoDAO();
		ProfessorDAO professorDao = new ProfessorDAO();
		DisciplinaDAO disciplinaDao = new DisciplinaDAO();

		sql = "SELECT codigo, codcurso, codprofessor, coddisciplina, ano, semestre FROM turma WHERE codigo = ?";

		try {
			ps = conexao.prepareStatement(sql);
			ps.setInt(1, codigo);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				curso = cursoDao.consultar(rs.getString("codcurso"));
				professor = professorDao.consultar(rs.getInt("codprofessor"));
				disciplina = disciplinaDao.consultar(rs.getString("coddisciplina"));
				
				turma = new Turma(rs.getInt("codigo"),
						curso,
						professor,
						disciplina,
						rs.getInt("ano"),
						rs.getInt("semestre"));
			}
			
			ps.close();
			rs.close();
		}
		catch(Exception e) {
			System.out.println("ERRO: Consultar DISCIPLINA no banco de dados. " + e.getMessage());
		}
		return turma;
	}
	
	public ArrayList<Turma> relatorio(){
		ArrayList<Turma> turmas = null;
		String sql;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean umaVez = true;
		
		Curso curso = null;
		Professor professor = null;
		Disciplina disciplina = null;		
		
		CursoDAO cursoDao = new CursoDAO();
		ProfessorDAO professorDao = new ProfessorDAO();
		DisciplinaDAO disciplinaDao = new DisciplinaDAO();
		
		sql = "SELECT codigo, codcurso, codprofessor, coddisciplina, ano, semestre FROM turma";
		
		try {
			ps = conexao.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				if(umaVez) {
					turmas = new ArrayList<>();
					umaVez = false;
				}
				
				curso = cursoDao.consultar(rs.getString("codcurso"));
				professor = professorDao.consultar(rs.getInt("codprofessor"));
				disciplina = disciplinaDao.consultar(rs.getString("coddisciplina"));
				
				Turma turma = new Turma(rs.getInt("codigo"),
						curso,
						professor,
						disciplina,
						rs.getInt("ano"),
						rs.getInt("semestre"));
				
				turmas.add(turma);
			}
			
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("ERRO: Relatório de TURMA no banco de dados. " + e.getMessage());
		}
		return turmas;
	}
}
