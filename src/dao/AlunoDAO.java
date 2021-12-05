package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import model.Aluno;

public class AlunoDAO {

	private Connection conexao = null;
	
	public AlunoDAO() {
		try {
			conexao = ConexaoBanco.getConexao();
		} catch (SQLException e) {
			System.out.println("ERRO: Conectar ao banco de dados. "  + e.getMessage());
		}
	}
	
	public void inserir(Aluno aluno) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "INSERT INTO aluno (matricula, nome, cpf, datamatricula) VALUES (?, ?, ?, ?)";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, aluno.getMatricula());
			ps.setString(2, aluno.getNome());
			ps.setString(3, aluno.getCpf());
			ps.setObject(4, aluno.getDataMatricula());
			ps.execute();
			ps.close();
			
			System.out.println("SUCESSO: Aluno adicionado!");
		}
		catch(Exception e) {
			System.out.println("ERRO: Inserir ALUNO no banco de dados. " + e.getMessage());
		}
	}
	
	public void excluir(Aluno aluno) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "DELETE FROM aluno WHERE matricula = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, aluno.getMatricula());
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("ERRO: Excluir ALUNO no banco de dados. " + e.getMessage());
		}
	}
	
	public void alterar(Aluno aluno) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "UPDATE aluno SET nome = ?, cpf = ?, datamatricula = ? WHERE matricula = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, aluno.getNome());
			ps.setString(2, aluno.getCpf());
			ps.setObject(3, aluno.getDataMatricula());
			ps.setString(4, aluno.getMatricula());
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("ERRO: Alterar ALUNO no banco de dados. " + e.getMessage());
		}
	}
	
	public Aluno consultar(String matricula){
		String sql;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Aluno aluno = null;
		
		sql = "SELECT matricula, nome, cpf, datamatricula FROM aluno WHERE matricula = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, matricula);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				aluno = new Aluno(rs.getString("matricula"),
					rs.getString("nome"),
					rs.getString("cpf"),
					rs.getObject("datamatricula", LocalDate.class) );
			}
			
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("ERRO: Relatório de ALUNOS no banco de dados. " + e.getMessage());
		}
		return aluno;
	}
	
	public ArrayList<Aluno> relatorio(){
		ArrayList<Aluno> alunos = null;
		String sql;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean umaVez = true;
		
		sql = "SELECT matricula, nome, cpf, datamatricula FROM aluno";
		
		try {
			ps = conexao.prepareStatement(sql);
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
			System.out.println("ERRO: Relatório de ALUNOS no banco de dados. " + e.getMessage());
		}
		return alunos;
	}
}
