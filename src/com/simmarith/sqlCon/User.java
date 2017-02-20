package com.simmarith.sqlCon;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends DbEntity {
    // Properties
    private String fName = null;
    private String lName = null;
    private String password = null;
    private String skype = null;
    private String mail = null;
    private String tel = null;
    private String house = null;

    // Getter and Setter
    public String getFname() {
        if (this.fName == null) {
            this.fName = super.fetchProperty("fname");
        }
        return this.fName;
    }

    public void setFname(String fname) {
        this.fName = fname;
    }

    public String getLname() {
        if (this.lName == null) {
            this.lName = super.fetchProperty("lname");
        }
        return lName;
    }

    public void setLname(String lname) {
        this.lName = lname;
    }

    public String getUserName() {
        return this.getFname() + "." + this.getLname();
    }
    
    public String getPassword() {
        if (this.password == null) {
            this.password = super.fetchProperty("password");
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getSkype() {
        if (this.skype == null) {
            this.skype = super.fetchProperty("skype");
        }
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getMail() {
        if (this.mail == null) {
            this.mail = super.fetchProperty("mail");
        }
        return mail;
    }

    public void setMail(String email) {
        this.mail = email;
    }

    public String getTel() {
        if (this.tel == null) {
            this.tel = super.fetchProperty("tel");
        }
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getHouse() {
        if (this.house == null) {
            this.house = super.fetchProperty("house");
        }
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    // Methods
    public void persist() {
        if (this.getId() == null) {
            ResultSet res = this.con.sql(String
                            .format("insert into %s (fname, lname, password, skype, mail, tel) values ('%s', '%s', '%s', '%s', '%s', '%s')",
                                    this.tableName, this.getFname(),
                                    this.getLname(), this.getPassword(), this.getSkype(),
                                    this.getMail(), this.getTel()));
            try {
                res.next();
                this.setId(Long.toString(res.getLong(1)));
                return;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        this.con.sql(String
                .format("update %s set fname = '%s', lname = '%s', password = '%s', skype = '%s', mail = '%s', tel = '%s' where id = %s",
                        this.tableName, this.getFname(), this.getLname(), this.getPassword(),
                        this.getSkype(), this.getMail(), this.getTel(), this.getId()));
    }

    // Constructors

    public User() {
        super("user");
    }

    public User(String id) {
        this();
        this.setId(id);
    }

}
