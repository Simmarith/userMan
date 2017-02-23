package com.simmarith.sqlCon;

import java.sql.ResultSet;
import java.sql.SQLException;

public class City extends DbEntity {
    // Properties
    private String name = null;
    private String plz = null;

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

    public String getPlz() {
        if (this.plz == null) {
            this.plz = this.fetchProperty("plz");
        }
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    // Methods
    public void persist() {
        if (this.getId() == null) {
            ResultSet res = this.con.sql(String.format(
                    "insert into %s (name, plz) values ('%s', '%s')",
                    this.tableName, this.getName(), this.getPlz()));
            try {
                res.next();
                this.setId(Long.toString(res.getLong(1)));
                return;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        this.con.sql(String.format(
                "update %s set name = '%s', plz = '%s' where id = %s",
                this.tableName, this.getName(), this.getPlz(), this.getId()));
    }

    // Constructors
    public City() {
        super("city");
    }

    public City(String id) {
        this();
        this.setId(id);
    }

}
