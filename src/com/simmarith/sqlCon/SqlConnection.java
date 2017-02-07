package com.simmarith.sqlCon;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import com.simmarith.utils.GlobalProperties;

public class SqlConnection {
	// Properties
	private static SqlConnection instance;
    private static Connection con;

	//Methods
	public SqlConnection getInstance() {
		if (SqlConnection.instance == null) {
			SqlConnection.instance = new SqlConnection();
		}
		return SqlConnection.instance;
	}
	
	// Constructors
	private SqlConnection() {
		GlobalProperties.initProperties();
		try {
			String url = String.format("jdbc:mysql://%s:%d/%s?allowMultiQueries=true",
					GlobalProperties.getProperty("db.url"), GlobalProperties.getProperty("db.port"),
					GlobalProperties.getProperty("db.database"));
			String username = GlobalProperties.getProperty("db.username");
			String password = GlobalProperties.getProperty("db.password");
			SqlConnection.con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
