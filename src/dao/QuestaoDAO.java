package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Disciplina;
import model.Questao;

public class QuestaoDAO {
	
	private Connection conexao = null;
	
	public QuestaoDAO() {
		try {
			conexao = ConexaoBanco.getConexao();
		} catch (SQLException e) {
			System.out.println("ERRO: Conectar ao banco de dados. "  + e.getMessage());
		}
	}
	
	public boolean inserir(Questao questao) {
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
			System.out.println("SUCESSO: Questão adicionada!");
			return true;
		}
		catch(Exception e) {
			System.out.println("ERRO: Inserir QUESTÃO no banco de dados. " + e.getMessage());
		}
		return false;
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
			System.out.println("ERRO: Excluir QUESTÃO no banco de dados. " + e.getMessage());
		}
	}
	
	public void alterar(Questao questao) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "UPDATE questao SET enunciado = ?, valor = ?, coddisciplina = ? WHERE codigo = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, questao.getEnunciado());
			ps.setFloat(2, questao.getValor());
			ps.setString(3, questao.getDisciplina().getCodigo());
			ps.setString(4, questao.getCodigo());
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("ERRO: Alterar QUESTÃO no banco de dados. " + e.getMessage());
		}
	}
	
	public Questao consultar(String codigo){
		String sql;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Questao questao = null;
		
		DisciplinaDAO disciplinaDao = new DisciplinaDAO();
		Disciplina disciplina = null;
		
		sql = "SELECT codigo, enunciado, valor, coddisciplina FROM questao WHERE codigo = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, codigo);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				disciplina = disciplinaDao.consultar(rs.getString("coddisciplina"));
				
				questao = new Questao(rs.getString("codigo"),
						rs.getString("enunciado"),
						rs.getFloat("valor"),
						disciplina);
			}
			
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("ERRO: Relatório de QUESTÃO no banco de dados. " + e.getMessage());
		}
		return questao;
	}
	
	public ArrayList<Questao> relatorio(){
		ArrayList<Questao> questoes = null;
		String sql;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean umaVez = true;
		
		DisciplinaDAO disciplinaDao = new DisciplinaDAO();
		Disciplina disciplina = null;
		
		sql = "SELECT codigo, enunciado, valor, coddisciplina FROM questao";
		
		try {
			ps = conexao.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				if(umaVez) {
					questoes = new ArrayList<>();
					umaVez = false;
				}
				
				disciplina = disciplinaDao.consultar(rs.getString("coddisciplina"));
				
				Questao questao = new Questao(rs.getString("codigo"),
						rs.getString("enunciado"),
						rs.getFloat("valor"),
						disciplina);
				
				questoes.add(questao);
			}
			
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("ERRO: Relatório de QUESTÃO no banco de dados. " + e.getMessage());
		}
		return questoes;
	}
	
	public ArrayList<Questao> relatorioDeQuestoesPorDisciplina(Disciplina disciplina){
		ArrayList<Questao> questoes = null;
		String sql;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean umaVez = true;
		
		sql = "SELECT codigo, enunciado, valor, coddisciplina FROM questao WHERE coddisciplina = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, disciplina.getCodigo());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				if(umaVez) {
					questoes = new ArrayList<>();
					umaVez = false;
				}
				
				Questao questao = new Questao(rs.getString("codigo"),
						rs.getString("enunciado"),
						rs.getFloat("valor"),
						disciplina);
				
				questoes.add(questao);
			}
			
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("ERRO: Relatório de QUESTÃO de uma DISCIPLINA no banco de dados. " + e.getMessage());
		}
		return questoes;
	}
	
}
