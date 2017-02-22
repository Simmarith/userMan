package com.simmarith.sqlCon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Permission extends DbEntity {
    // Properties
    private String name = null;
    private String description = null;

    // Getter and Setter
    public String getName() {
        if (this.name == null) {
            this.name = this.fetchProperty("name");
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        if (this.description == null) {
            this.description = this.fetchProperty("description");
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Methods
    public static ArrayList<Permission> getAllPermissions() {
        SqlConnection con = SqlConnection.getInstance();
        ArrayList<Permission> allPermissions = new ArrayList<>();
        ResultSet res = con.sql("Select id from user");
        try {
            while (res.next()) {
                allPermissions
                        .add(new Permission(Long.toString(res.getLong(1))));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return allPermissions;
    }

    public void persist() {
        if (this.getId() == null) {
            ResultSet res = this.con.sql(String.format(
                    "insert into %s (name, description) values ('%s', '%s')", this.tableName,
                    this.getName(), this.getDescription()));
            try {
                res.next();
                this.setId(Long.toString(res.getLong(1)));
                return;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        this.con.sql(String.format("update %s set name = '%s', description = '%s' where id = %s",
                this.tableName, this.getName(), this.getDescription(), this.getId()));
    }

    // Constructors
    public Permission() {
        super("permission");
    }

    public Permission(String id) {
        this();
        this.setId(id);
    }

}
