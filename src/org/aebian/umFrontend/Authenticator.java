package org.aebian.umFrontend;

import com.simmarith.sqlCon.Permission;
import com.simmarith.sqlCon.User;

import java.util.ArrayList;
import java.util.Iterator;

import static org.aebian.umFrontend.view.FrontendView.currUser;

public class Authenticator {

    public static User thisUser;
    public static Permission thisPermission;

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

    public static Boolean checkAccess(Permission permission) {
        ArrayList<Permission> userPerm = currUser.getPermissions();
        Iterator<Permission> permissionIterator = userPerm.iterator();
        while (permissionIterator.hasNext()) {
            thisPermission = permissionIterator.next();
            if (permission.getId() == thisPermission.getId()) {
                return true;
            }
            return false;
        }
        return false;

    }
}