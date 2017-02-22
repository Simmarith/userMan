package org.aebian.umFrontend;

    /* Authentification Classes and Methods */

import com.simmarith.sqlCon.User;

import java.util.ArrayList;
import java.util.Iterator;

public class Authenticator {

    public static User thisUser;

    //Returns User if login matches; else null
    public static User validateLogin(String username, String password) {
        ArrayList<User> allUsers = User.getAllUsers();
        Iterator<User> userIterator = allUsers.iterator();
        while (userIterator.hasNext()) {
            thisUser = userIterator.next();
            if (username.equals(thisUser.getUserName())) {
                if (password.equals(thisUser.getPassword())) {
                    return thisUser;
                }
                return null;
            }
        }
        return null;
    }
}