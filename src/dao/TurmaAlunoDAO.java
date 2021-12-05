package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import model.Aluno;
import model.Turma;

public class TurmaAlunoDAO {

	private Connection conexao = null;
	
	public TurmaAlunoDAO() {
		try {
			conexao = ConexaoBanco.getConexao();
		} catch (SQLException e) {
			System.out.println("ERRO: Conectar ao banco de dados. "  + e.getMessage());
		}
	}
	
	public void inserir(Turma turma, Aluno aluno) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "INSERT INTO turmaaluno (codturma, mataluno) VALUES (?, ?)";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setInt(1, turma.getCodigo());
			ps.setString(2, aluno.getMatricula());
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("ERRO: Inserir ALUNO na TURMA no banco de dados. " + e.getMessage());
		}
	}
	
	public void excluir(Turma turma, Aluno aluno) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "DELETE FROM turmaaluno WHERE codturma = ? AND mataluno = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setInt(1, turma.getCodigo());
			ps.setString(2, aluno.getMatricula());
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("ERRO: Excluir ALUNO da TURMA banco de dados. " + e.getMessage());
		}
	}

	public ArrayList<Aluno> relatorio(Turma turma){
		ArrayList<Aluno> alunos = null;
		String sql;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean umaVez = true;
		
		sql = "SELECT a.matricula, a.nome, a.cpf, a.datamatricula FROM turmaaluno ta LEFT JOIN aluno a ON ta.mataluno = a.matricula WHERE codturma = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setInt(1, turma.getCodigo());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				if(umaVez) {
					alunos = new ArrayList<>();
					umaVez = false;
				}
				Aluno alu = new Aluno(rs.getString("matricula"),
					rs.getString("nome"),
					rs.getString("cpf"),
					rs.getObject("datamatricula", LocalDate.class) );
				
				alunos.add(alu);
			}
			
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("ERRO: Relatório de ALUNOS de uma TURMA no banco de dados. " + e.getMessage());
		}
		return alunos;
	}
}
