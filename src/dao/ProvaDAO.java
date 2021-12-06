package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Disciplina;
import model.Prova;
import model.Turma;

public class ProvaDAO {
	
private Connection conexao = null;
	
	public ProvaDAO() {
		try {
			conexao = ConexaoBanco.getConexao();
		} catch (SQLException e) {
			System.out.println("ERRO: Conectar ao banco de dados. "  + e.getMessage());
		}
	}
	
	public void inserir(Prova prova) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "INSERT INTO prova (identificador, codturma, coddisciplina) VALUES (?, ?, ?)";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, prova.getIdentificador());
			ps.setInt(2, prova.getTurma().getCodigo());
			ps.setString(3, prova.getDisciplina().getCodigo());
			ps.execute();
			ps.close();
			System.out.println("SUCESSO: Prova adicionada!");
		}
		catch(Exception e) {
			System.out.println("ERRO: Inserir PROVA no banco de dados. " + e.getMessage());
		}
	}
	
	public void excluir(Prova prova) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "DELETE FROM prova WHERE identificador = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, prova.getIdentificador());
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("ERRO: Excluir PROVA no banco de dados. " + e.getMessage());
		}
	}
	
	public void alterar(Prova prova) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "UPDATE prova SET codturma = ?, coddisciplina = ? WHERE identificador = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setInt(1, prova.getTurma().getCodigo());
			ps.setString(2, prova.getDisciplina().getCodigo());
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("ERRO: Alterar PROVA no banco de dados. " + e.getMessage());
		}
	}
	
	public Prova consultar(String identificador) {
		String sql;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Prova prova = null;
		
		Turma turma = null;
		TurmaDAO turmaDao = new TurmaDAO();
		
		Disciplina disciplina = null;
		DisciplinaDAO disciplinaDao = new DisciplinaDAO();
		
		sql = "SELECT identificador, codturma, coddisciplina FROM prova WHERE identificador = ?";

		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, identificador);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				turma = turmaDao.consultar(rs.getInt("codturma"));
				disciplina = disciplinaDao.consultar(rs.getString("coddisciplina"));
				
				prova = new Prova(rs.getString("identificador"),
						turma,
						disciplina);
			}
			
			ps.close();
			rs.close();
		}
		catch(Exception e) {
			System.out.println("ERRO: Consultar PROVA no banco de dados. " + e.getMessage());
		}
		return prova;
	}
	
	public ArrayList<Prova> relatorio(){
		ArrayList<Prova> provas = null;
		String sql;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean umaVez = true;
		
		Turma turma = null;
		TurmaDAO turmaDao = new TurmaDAO();
		
		Disciplina disciplina = null;
		DisciplinaDAO disciplinaDao = new DisciplinaDAO();
		
		sql = "SELECT identificador, codturma, coddisciplina FROM prova";
		
		try {
			ps = conexao.prepareStatement(sql);
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
						disciplina);
				
				provas.add(prova);
			}
			
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("ERRO: Relatório de PROVAS no banco de dados. " + e.getMessage());
		}
		return provas;
	}
	
	public ArrayList<Prova> relatorioDeProvasPorDisciplina(Disciplina disciplina){
		ArrayList<Prova> provas = null;
		String sql;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean umaVez = true;
		
		Turma turma = null;
		TurmaDAO turmaDao = new TurmaDAO();
		
		//Disciplina disciplina = null;
		//DisciplinaDAO disciplinaDao = new DisciplinaDAO();
		
		sql = "SELECT identificador, codturma, coddisciplina FROM prova WHERE coddisciplina = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, disciplina.getCodigo());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				if(umaVez) {
					provas = new ArrayList<>();
					umaVez = false;
				}			
				turma = turmaDao.consultar(rs.getInt("codturma"));
	
				Prova prova = new Prova(rs.getString("identificador"),
						turma,
						disciplina);
				
				provas.add(prova);
			}
			
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("ERRO: Relatório de PROVAS de uma DISCIPLINA no banco de dados. " + e.getMessage());
		}
		return provas;
	}
	
}
