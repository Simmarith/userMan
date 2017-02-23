package com.simmarith.sqlCon;

import java.sql.ResultSet;
import java.sql.SQLException;

public class House extends DbEntity {
    // Properties
    private String number = null;
    private Street street = new Street();

    // Getter and Setter
    public String getNumber() {
        if (this.number == null) {
            this.number = this.fetchProperty("number");
        }
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Street getStreet() {
        if (this.street.getId() == null) {
            this.street = new Street(this.fetchProperty("id_street"));
        }
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    // Methods
    public void persist() {
        if (this.getId() == null) {
            ResultSet res = this.con.sql(String.format(
                    "insert into %s (id_city, number) values ('%s', '%s')",
                    this.tableName, this.getStreet().getId(), this.getNumber()));
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
                "update %s set id_city = '%s', number = '%s' where id = %s",
                this.tableName, this.getStreet().getId(), this.getNumber(),
                this.getId()));
    }

    // Constructors
    public House() {
        super("house");
    }

    public House(String id) {
        this();
        this.setId(id);
    }

}
