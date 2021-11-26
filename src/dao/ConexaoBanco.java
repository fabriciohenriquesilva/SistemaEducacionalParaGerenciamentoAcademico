package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoBanco {

	private static Connection conexao = null;
	
	public static Connection getConexao() throws SQLException {
		if((conexao == null) || (conexao.isClosed())) {
			conexao = fabricaDeConexoes();
		}
		
		return conexao;
	}
	
	public static Connection fabricaDeConexoes() {
		try {
			Class.forName("org.postgresql.Driver");
			
			Properties prop = new Properties();
			prop.put("user", "postgres");
			prop.put("password", "admin");
			prop.put("charset", "UTF-8");
			
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbagenda", prop);	
		}
		catch(Exception e) {
			System.err.println("Erro ao conectar ao banco de dados! " +  e.getMessage());
		}
		
		return null;
	}
}
