package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Professor;

public class ProfessorDAO {
	
	private Connection conexao = null;
	
	public ProfessorDAO() {
		try {
			conexao = ConexaoBanco.getConexao();
		} catch (SQLException e) {
			System.out.println("ERRO: Conectar ao banco de dados. "  + e.getMessage());
		}
	}
	
	public void inserir(Professor professor) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "INSERT INTO professor (nome, cpf, titulacao) VALUES (?, ?, ?)";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, professor.getNome());
			ps.setString(2, professor.getCpf());
			ps.setString(3, professor.getTitulacao());
			ps.execute();
			ps.close();

			System.out.println("SUCESSO: Professor adicionado!");
		}
		catch(Exception e) {
			System.out.println("ERRO: Inserir PROFESSOR no banco de dados. " + e.getMessage());
		}
	}
	
	public void excluir(Professor professor) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "DELETE FROM professor WHERE codigo = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setInt(1, professor.getCodigo());
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("ERRO: Excluir PROFESSOR no banco de dados. " + e.getMessage());
		}
	}
	
	public void alterar(Professor professor) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "UPDATE professor SET nome = ?, cpf = ?, titulacao = ? WHERE codigo = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, professor.getNome());
			ps.setString(2, professor.getCpf());
			ps.setString(3, professor.getTitulacao());
			ps.setInt(4, professor.getCodigo());
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("ERRO: Alterar PROFESSOR no banco de dados. " + e.getMessage());
		}
	}
	
	public ArrayList<Professor> relatorio(){
		ArrayList<Professor> professores = null;
		String sql;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean umaVez = true;
		
		sql = "SELECT codigo, nome, cpf, titulacao FROM professor";
		
		try {
			ps = conexao.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				if(umaVez) {
					professores = new ArrayList<>();
					umaVez = false;
				}
				Professor prof = new Professor(rs.getInt("codigo"),
						rs.getString("nome"),
						rs.getString("cpf"),
						rs.getString("titulacao"));
				
				professores.add(prof);
			}
			
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("ERRO: Relatório de ALUNOS no banco de dados. " + e.getMessage());
		}
		return professores;
	}
	
}
