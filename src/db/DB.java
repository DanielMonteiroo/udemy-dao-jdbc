package db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;
	
	//metodo para conectar com DB
	public static Connection getConnection() {
		if (conn == null) {
			try {
			Properties props = loadProperties();
			String url = props.getProperty("dburl");
			conn = DriverManager.getConnection(url, props);
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	//metodo fechar conexão
	public static void closeConnection() {
		if(conn != null){
			try {
			conn.close();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	
	//metodo ler dados DB
	public static Properties loadProperties() {
		
		try(FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch (Exception e) {
			throw new DbException(e.getMessage());
		}
		
	}
	
	//metodo fechamento statement
	public static void closeStatement(Statement st) {
		if(st != null) {
		try {
			st.close();
		} catch (SQLException e) {
		throw new DbException(e.getMessage());
		}
	}
	}
	
	
	//metodo fechamento result set
		public static void closeResultSet(ResultSet rs) {
			if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			throw new DbException(e.getMessage());
			}
		}
		}
		
	
}