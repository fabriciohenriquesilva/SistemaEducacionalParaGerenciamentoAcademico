package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContatoDAO {
	
	private Connection conexao = null;
	
	public ContatoDAO() {
		try {
			conexao = ConexaoBanco.getConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void incluir() {
		String sql;
		PreparedStatement ps = null;
		
		sql = "INSERT INTO contato (id, nome, telefone, email) VALUES (?, ?, ?, ?)";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setInt(1, 10);
			ps.setString(2, "Fabrício Henrique da Silva");
			ps.setString(3, "(38) 9999-9999");
			ps.setString(4, "email@dominio.com");
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("INCLUSÃO DE CONTATO - ERRO: " + e.getMessage());
		}
	}
	
}
