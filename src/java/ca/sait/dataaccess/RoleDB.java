/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sait.dataaccess;

import ca.sait.models.Role;
import java.util.*;
import java.sql.*;
import javax.persistence.*;

/**
 *
 * @author phamh
 */
public class RoleDB {
    public String get(int id) throws SQLException{
        Role role;
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        
        EntityManager em = emFactory.createEntityManager();
        
        try {
            role = em.find(Role.class, id);
        } finally {
            em.close();
        }
        return role.getName();
        
        
    }
    
    public ArrayList<Role> getAll() throws SQLException {
        ArrayList<Role> roles = new ArrayList<>();
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        
        EntityManager em = emFactory.createEntityManager();
        
        try {
            roles.addAll(em.createNamedQuery("Role.findAll", Role.class).getResultList());
        } finally {
            em.close();
        }
        return roles;
    }
}
