package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import model.Aluno;
import model.Disciplina;
import model.Prova;
import model.Turma;

public class ProvaAlunoDAO {
	
	private Connection conexao = null;
	
	public ProvaAlunoDAO() {
		try {
			conexao = ConexaoBanco.getConexao();
		} catch (SQLException e) {
			System.out.println("ERRO: Conectar ao banco de dados. "  + e.getMessage());
		}
	}
	
	public void inserir(Aluno aluno, Prova prova, float nota) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "INSERT INTO provaaluno (mataluno, identificadorprova, nota) VALUES (?, ?, ?)";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, aluno.getMatricula());
			ps.setString(2, prova.getIdentificador());
			ps.setFloat(3, nota);
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("ERRO: Inserir PROVA do ALUNO no banco de dados. " + e.getMessage());
		}
	}
	
	public void excluir(Aluno aluno, Prova prova) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "DELETE FROM provaaluno WHERE mataluno = ? AND identificadorprova = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, aluno.getMatricula());
			ps.setString(2, prova.getIdentificador());
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("ERRO: Excluir PROVA do ALUNO no banco de dados. " + e.getMessage());
		}
	}
	
	public ArrayList<Prova> relatorio(Aluno aluno){
		ArrayList<Prova> provas = null;
		String sql;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean umaVez = true;
		
		Turma turma = null;
		TurmaDAO turmaDao = new TurmaDAO();
		
		Disciplina disciplina = null;
		DisciplinaDAO disciplinaDao = new DisciplinaDAO();
		
		sql = "SELECT p.identificador, p.codturma, p.coddisciplina, pa.nota FROM provaaluno pa LEFT JOIN prova p ON p.identificador = pa.identificadorprova WHERE pa.mataluno = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, aluno.getMatricula());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				if(umaVez) {
					provas = new ArrayList<>();
					umaVez = false;
				}
				turma = turmaDao.consultar(rs.getInt("codturma"));
				disciplina = disciplinaDao.consultar(rs.getString("coddisciplina"));
				
				Prova prova = new Prova(rs.getString("identificador"),
						turma,
						disciplina,
						rs.getFloat("nota"));
				
				provas.add(prova);
			}
			
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("ERRO: Relatório de PROVAS realizadas por um ALUNO no banco de dados. " + e.getMessage());
		}
		return provas;
	}
	
	public ArrayList<Aluno> relatorioDeAlunos(Prova prova){
		ArrayList<Aluno> alunos = null;
		String sql;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean umaVez = true;
		
		sql = "SELECT a.matricula, a.nome, a.cpf, a.datamatricula FROM provaaluno pa LEFT JOIN aluno a ON pa.mataluno = a.matricula WHERE pa.identificadorprova = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, prova.getIdentificador());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				if(umaVez) {
					alunos = new ArrayList<>();
					umaVez = false;
				}
				Aluno aluno = new Aluno(rs.getString("matricula"),
						rs.getString("nome"),
						rs.getString("cpf"),
						rs.getObject("datamatricula", LocalDate.class) );
					
					alunos.add(aluno);
			}
			
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("ERRO: Relatório de ALUNOS que realizaram uma PROVA no banco de dados. " + e.getMessage());
		}
		return alunos;
	}
}
