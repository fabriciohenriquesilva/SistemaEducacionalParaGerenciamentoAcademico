package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Curso;

public class CursoDAO {
	private Connection conexao = null;
	
	public CursoDAO() {
		try {
			conexao = ConexaoBanco.getConexao();
		} catch (SQLException e) {
			System.out.println("ERRO: Conectar ao banco de dados. "  + e.getMessage());
		}
	}
	
	public void inserir(Curso curso) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "INSERT INTO curso (codigo, nome) VALUES (?, ?)";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, curso.getCodigo());
			ps.setString(2, curso.getNome());
			ps.execute();
			ps.close();
			System.out.println("SUCESSO: Curso adicionado!");
		}
		catch(Exception e) {
			System.out.println("ERRO: Inserir CURSO no banco de dados. " + e.getMessage());
		}
	}
	
	public void excluir(Curso curso) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "DELETE FROM curso WHERE codigo = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, curso.getCodigo());
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("ERRO: Excluir CURSO no banco de dados. " + e.getMessage());
		}
	}
	
	public void alterar(Curso curso) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "UPDATE curso SET nome = ? WHERE codigo = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, curso.getNome());
			ps.setString(2, curso.getCodigo());
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("ERRO: Alterar CURSO no banco de dados. " + e.getMessage());
		}
	}
	
	public ArrayList<Curso> relatorio(){
		ArrayList<Curso> cursos = null;
		String sql;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean umaVez = true;
		
		sql = "SELECT codigo, nome FROM curso";
		
		try {
			ps = conexao.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				if(umaVez) {
					cursos = new ArrayList<>();
					umaVez = false;
				}
				Curso curso = new Curso(rs.getString("codigo"),
						rs.getString("nome"));
				
				cursos.add(curso);
			}
			
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("ERRO: Relatório de CURSO no banco de dados. " + e.getMessage());
		}
		return cursos;
	}
}
