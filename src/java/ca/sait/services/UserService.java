package ca.sait.services;

import ca.sait.dataaccess.UserDB;
import ca.sait.models.User;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author phamh
 */
public class UserService {
    public ArrayList<User> getAll() {
        ArrayList<User> users = null;
        try {
            UserDB userDB = new UserDB();
            users = userDB.getAll();
        } catch(SQLException ex) {
            System.err.println(ex);
        }
        return users;
    }
    
    public User get(String email) {
        User user = null;
        try {
            UserDB userDB = new UserDB();
            user = userDB.get(email);
        } catch(SQLException ex) {
            System.err.println(ex);
        }
        return user;
    }
    
    public void add(User user) {
        try {
            UserDB userDB = new UserDB();
            userDB.add(user);
        } catch(SQLException ex) {
            System.err.println(ex);
        } 
    }
    
    public void update(User user) {
         try {
             UserDB userDB = new UserDB();
             userDB.update(user);
         } catch(SQLException ex) {
             System.err.println(ex);
         }
     }
    
    public void delete(User user) {
        try {
            UserDB userDB = new UserDB();
            userDB.delete(user);
        } catch(SQLException ex) {
            System.err.println(ex);
        }
    }
}
