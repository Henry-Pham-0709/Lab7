/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sait.dataaccess;

import ca.sait.models.User;
import java.sql.*;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author phamh
 */
public class UserDB {
    public ArrayList<User> getAll() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        
        EntityManager em = emFactory.createEntityManager();
        try {
            users.addAll(em.createNamedQuery("User.findAll", User.class).getResultList());
        } finally {
            em.close();
        }
        return users;
    }
    
    public User get(String emailSearch) throws SQLException {
        User user;
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        
        EntityManager em = emFactory.createEntityManager();
        
        try {
            user = em.find(User.class, emailSearch);
        } finally {
            em.close();
        }
        return user;
    }
    
    public void add(User user) throws SQLException{
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        
        EntityManager em = emFactory.createEntityManager();
        
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.persist(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
        
    }
    
    public void update(User user) throws SQLException {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        
        EntityManager em = emFactory.createEntityManager();
        
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
        
    }
    
    public void delete(User user) throws SQLException {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        
        EntityManager em = emFactory.createEntityManager();
        
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.remove(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
