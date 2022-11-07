/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sait.services;

import ca.sait.dataaccess.RoleDB;
import ca.sait.models.Role;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author phamh
 */
public class RoleService {
    public ArrayList<Role> getAll() {
        ArrayList<Role> roles = null;
        try {
            RoleDB roleDB = new RoleDB();
            roles = roleDB.getAll();
        } catch(SQLException ex) {
            System.err.println(ex);
        }
        return roles;
    }
    
    public String get(int id) {
        String roleDescription = "";
        try {
            RoleDB roleDB = new RoleDB();
            roleDescription = roleDB.get(id);
        } catch(SQLException ex) {
            System.err.println(ex);
        }
        return roleDescription;
    }
}
