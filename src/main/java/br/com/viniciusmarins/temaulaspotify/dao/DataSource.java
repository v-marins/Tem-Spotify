package br.com.viniciusmarins.temaulaspotify.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
	
	private String hostname;
	private String username;
	private String password;
	private String database;
	private Connection connection;
	
	public DataSource()  {
		
		try {
			hostname = "localhost";
			database = "temspotify";
			username = "temspotify";
			password = "T3m$p0tiFy";
			String URL = "jdbc:mysql://"+hostname+":3306/"+database;
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connection = DriverManager.getConnection(URL, username, password);
			System.out.println("Conexão Estabelecida!");
			
		} catch (SQLException ex) {
			System.out.println("ERRO AO CONECTAR: " + ex.getMessage());
		}
		
	}

	public Connection getConnection() {
		return connection;
	}
	
	

}
