package com.simmarith.sqlCon;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbEntity {
    // Properties
    protected SqlConnection con;
    protected String tableName;
    private String id = null;

    // Getter and Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Methods
    protected String fetchProperty(String propertyName) {
        if (this.id != null) {
            try {
                ResultSet res = this.con.sql(String.format(
                        "Select %s from %s where id = %s", propertyName,
                        this.tableName, this.id));
                res.next();
                return res.getString(1);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    // Constructors
    public DbEntity(String tableName) {
        this.con = SqlConnection.getInstance();
        this.tableName = tableName;
    }
}
