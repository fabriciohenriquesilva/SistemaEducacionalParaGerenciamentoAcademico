package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Questao;

public class QuestaoDAO {
	
	private Connection conexao = null;
	
	public QuestaoDAO() {
		try {
			conexao = ConexaoBanco.getConexao();
		} catch (SQLException e) {
			e.getMessage();
		}
	}
	
	public void inserir(Questao questao) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "INSERT INTO questao (codigo, enunciado, valor, coddisciplina) VALUES (?, ?, ?, ?)";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, questao.getCodigo());
			ps.setString(2, questao.getEnunciado());
			ps.setFloat(3, questao.getValor());
			ps.setString(4, questao.getDisciplina().getCodigo());
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			e.getMessage();
		}
		
	}
	
	public void excluir(Questao questao) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "DELETE FROM questao WHERE codigo = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, questao.getCodigo());
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			e.getMessage();
		}
	}
	
	public void alterar(Questao questao) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "UPDATE questao SET codigo = ?, enunciado = ?, valor = ?, coddisciplina = ? WHERE codigo = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, questao.getCodigo());
			ps.setString(2, questao.getEnunciado());
			ps.setFloat(3, questao.getValor());
			ps.setString(4, questao.getDisciplina().getCodigo());
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			e.getMessage();
		}
	}
	
	public void consultar() {
		
	}
	
}
