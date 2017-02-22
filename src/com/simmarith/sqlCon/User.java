package com.simmarith.sqlCon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User extends DbEntity {
    // Properties
    private String fName = null;
    private String lName = null;
    private String password = null;
    private String skype = null;
    private String mail = null;
    private String tel = null;
    private String house = null;
    private ArrayList<Permission> permissions = null;

    // Getter and Setter
    public String getFname() {
        if (this.fName == null) {
            this.fName = this.fetchProperty("fname");
        }
        return this.fName;
    }

    public void setFname(String fname) {
        this.fName = fname;
    }

    public String getLname() {
        if (this.lName == null) {
            this.lName = this.fetchProperty("lname");
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
            this.password = this.fetchProperty("password");
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getSkype() {
        if (this.skype == null) {
            this.skype = this.fetchProperty("skype");
        }
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getMail() {
        if (this.mail == null) {
            this.mail = this.fetchProperty("mail");
        }
        return mail;
    }

    public void setMail(String email) {
        this.mail = email;
    }

    public String getTel() {
        if (this.tel == null) {
            this.tel = this.fetchProperty("tel");
        }
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getHouse() {
        if (this.house == null) {
            this.house = this.fetchProperty("house");
        }
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }
    
    public ArrayList<Permission> getPermissions() {
        if (this.permissions == null) {
            this.permissions = new ArrayList<>();
            ResultSet res = this.con.sql("Select p.id from user_permission up left join permission p on up.id_permission = p.id where up.id_user = " + this.getId());
            try {
                while (res.next()) {
                    this.permissions.add(new Permission(Long.toString(res.getLong(1))));
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return this.permissions;
    }

    // Methods
    public static ArrayList<User> getAllUsers() {
        SqlConnection con = SqlConnection.getInstance();
        ArrayList<User> allUsers = new ArrayList<>();
        ResultSet res = con.sql("Select id from user");
        try {
            while (res.next()) {
                allUsers.add(new User(Long.toString(res.getLong(1))));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return allUsers;
    }
    
    public void addPermission(Permission permission) {
        this.con.sql(String.format("insert into user_permission (id_user, id_permission) values (%s, %s)", this.getId(), permission.getId()));
        this.permissions = null;
    }
    
    public void revokePermission(Permission permission) {
        this.con.sql(String.format("delete from user_permission where id_user = %s and id_permission = %s", this.getId(), permission.getId()));
        this.permissions = null;
    }
    
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
