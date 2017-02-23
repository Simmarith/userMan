package com.simmarith.sqlCon;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Street extends DbEntity {
    // Properties
    private String name = null;
    private City city = new City();

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

    public City getCity() {
        if (this.city.getId() == null) {
            this.city = new City(this.fetchProperty("id_city"));
        }
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    // Methods
    public void persist() {
        if (this.getId() == null) {
            ResultSet res = this.con.sql(String.format(
                    "insert into %s (id_city, name) values ('%s', '%s')",
                    this.tableName, this.getCity().getId(), this.getName()));
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
                "update %s set id_city = '%s', name = '%s' where id = %s",
                this.tableName, this.getCity().getId(), this.getName(),
                this.getId()));
    }

    // Constructors
    public Street() {
        super("street");
    }

    public Street(String id) {
        this();
        this.setId(id);
    }

}
