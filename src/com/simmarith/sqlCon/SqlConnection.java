package com.simmarith.sqlCon;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.regex.Pattern;

import com.simmarith.utils.GlobalProperties;

public class SqlConnection {
    // Properties
    private static SqlConnection instance;
    private static Connection con;
    private static String dmlPattern = "(?i)^INSERT[\\S\\s]*|^UPDATE[\\S\\s]*|^DROP[\\S\\s]*";

    // Methods
    public static SqlConnection getInstance() {
        if (SqlConnection.instance == null) {
            SqlConnection.instance = new SqlConnection();
        }
        return SqlConnection.instance;
    }

    public ResultSet sql(String query) {
        try {
            Statement state = con.createStatement();
            if (Pattern.matches(SqlConnection.dmlPattern, query)) {
                state.executeUpdate(query);
            } else {
                return state.executeQuery(query);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // Constructors
    private SqlConnection() {
        GlobalProperties.initProperties();
        try {
            String url = String.format(
                    "jdbc:mysql://%s:%s/%s?allowMultiQueries=true",
                    GlobalProperties.getProperty("db.url"),
                    GlobalProperties.getProperty("db.port"),
                    GlobalProperties.getProperty("db.database"));
            String username = GlobalProperties.getProperty("db.username");
            String password = GlobalProperties.getProperty("db.password");
            SqlConnection.con = DriverManager.getConnection(url, username,
                    password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
