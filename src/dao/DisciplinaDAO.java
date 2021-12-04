package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Disciplina;

public class DisciplinaDAO {
	
	private Connection conexao = null;
	
	public DisciplinaDAO() {
		try {
			conexao = ConexaoBanco.getConexao();
		} catch (SQLException e) {
			System.out.println("ERRO: Conectar ao banco de dados. "  + e.getMessage());
		}
	}
	
	public void inserir(Disciplina disciplina, String codCurso) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "INSERT INTO disciplina (codigo, nome, ementa, cargahoraria, codcurso) VALUES (?, ?, ?, ?, ?)";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, disciplina.getCodigo());
			ps.setString(2, disciplina.getNome());
			ps.setString(3, disciplina.getEmenta());
			ps.setInt(4, disciplina.getCargaHoraria());
			ps.setString(5, codCurso);
			ps.execute();
			ps.close();
			System.out.println("SUCESSO: Disciplina adicionada!");
		}
		catch(Exception e) {
			System.out.println("ERRO: Inserir DISCIPLINA no banco de dados. " + e.getMessage());
		}
	}
	
	public void excluir(Disciplina disciplina) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "DELETE FROM disciplina WHERE codigo = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, disciplina.getCodigo());
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("ERRO: Excluir DISCIPLINA no banco de dados. " + e.getMessage());
		}
	}
	
	public void alterar(Disciplina disciplina, String codCurso) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "UPDATE disciplina SET nome = ?, ementa = ?, cargahoraria = ?, codcurso = ? WHERE codigo = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, disciplina.getNome());
			ps.setString(2, disciplina.getEmenta());
			ps.setInt(3, disciplina.getCargaHoraria());
			ps.setString(4, codCurso);
			ps.setString(5, disciplina.getCodigo());
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("ERRO: Alterar DISCIPLINA no banco de dados. " + e.getMessage());
		}
	}
	
	public Disciplina consultar(String codigo) {
		String sql;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Disciplina disciplina = null;

		sql = "SELECT codigo, nome, ementa, cargahoraria FROM disciplina WHERE codigo = ?";

		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, codigo);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				disciplina = new Disciplina(rs.getString("codigo"),
						rs.getString("nome"),
						rs.getString("ementa"),
						rs.getInt("cargahoraria"));
			}
			
			ps.close();
			rs.close();
		}
		catch(Exception e) {
			System.out.println("ERRO: Consultar DISCIPLINA no banco de dados. " + e.getMessage());
		}
		return disciplina;
	}
	
	public ArrayList<Disciplina> relatorio(){
		ArrayList<Disciplina> disciplinas = null;
		String sql;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean umaVez = true;
		
		sql = "SELECT codigo, nome, ementa, cargahoraria FROM disciplina";
		
		try {
			ps = conexao.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				if(umaVez) {
					disciplinas = new ArrayList<>();
					umaVez = false;
				}
				Disciplina disciplina = new Disciplina(rs.getString("codigo"),
						rs.getString("nome"),
						rs.getString("ementa"),
						rs.getInt("cargahoraria"));
				
				disciplinas.add(disciplina);
			}
			
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("ERRO: Relatório de DISCIPLINA no banco de dados. " + e.getMessage());
		}
		return disciplinas;
	}
}
