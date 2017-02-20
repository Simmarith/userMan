package com.simmarith.sqlCon;

public class User extends DbEntity {
    // Properties
    private String fName = null;
    private String lName = null;
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

    //Methods
    public void persist() {
        this.con.sql(String.format("update %s set fname = '%s', lname = '%s', skype = '%s', mail = '%s', tel = '%s'", this.tableName, this.getFname(), this.getLname(), this.getSkype(), this.getMail(), this.getTel()));
    }
    
    // Constructors
    public User(String id) {
        super("user");
        this.setId(id);
    }

}
