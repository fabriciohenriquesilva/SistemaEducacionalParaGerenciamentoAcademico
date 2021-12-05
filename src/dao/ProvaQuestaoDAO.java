package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Disciplina;
import model.Prova;
import model.Questao;
import model.Turma;

public class ProvaQuestaoDAO {
	private Connection conexao = null;
	
	public ProvaQuestaoDAO() {
		try {
			conexao = ConexaoBanco.getConexao();
		} catch (SQLException e) {
			System.out.println("ERRO: Conectar ao banco de dados. "  + e.getMessage());
		}
	}
	
	public void inserir(Prova prova, Questao questao) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "INSERT INTO provaquestao (codigoquestao, identificadorprova) VALUES (?, ?)";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, questao.getCodigo());
			ps.setString(2, prova.getIdentificador());
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("ERRO: Inserir QUESTÃO na PROVA no banco de dados. " + e.getMessage());
		}
	}
	
	public void excluir(Prova prova, Questao questao) {
		String sql;
		PreparedStatement ps = null;
		
		sql = "DELETE FROM provaquestao WHERE codigoquestao = ? AND identificadorprova = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, questao.getCodigo());
			ps.setString(2, prova.getIdentificador());
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("ERRO: Excluir ALUNO da TURMA banco de dados. " + e.getMessage());
		}
	}
	
	public ArrayList<Questao> relatorio(Prova prova){
		ArrayList<Questao> questoes = null;
		String sql;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean umaVez = true;
		
		DisciplinaDAO disciplinaDao = new DisciplinaDAO();
		Disciplina disciplina = null;
		
		sql = "SELECT q.codigo, q.enunciado, q.valor, q.coddisciplina FROM provaquestao pq LEFT JOIN questao q ON pq.codigoquestao = q.codigo WHERE pq.identificadorprova = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, prova.getIdentificador());
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
			System.out.println("ERRO: Relatório de QUESTÕES de uma PROVA no banco de dados. " + e.getMessage());
		}
		return questoes;
	}
	
	public ArrayList<Prova> relatorioDeProvas(Questao questao){
		ArrayList<Prova> provas = null;
		String sql;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean umaVez = true;
		
		Turma turma = null;
		TurmaDAO turmaDao = new TurmaDAO();
		
		DisciplinaDAO disciplinaDao = new DisciplinaDAO();
		Disciplina disciplina = null;
		
		sql = "SELECT p.identificador, p.codturma, p.coddisciplina FROM provaquestao pq LEFT JOIN prova p ON pq.identificadorprova = p.identificador WHERE pq.codigoquestao = ?";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, questao.getCodigo());
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
			System.out.println("ERRO: Relatório de PROVAS de uma QUESTÃO no banco de dados. " + e.getMessage());
		}
		return provas;
	}
}
