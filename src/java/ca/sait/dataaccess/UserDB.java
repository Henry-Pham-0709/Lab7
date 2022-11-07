/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sait.dataaccess;

import ca.sait.models.User;
import java.sql.*;
import java.util.*;

/**
 *
 * @author phamh
 */
public class UserDB {
    public ArrayList<User> getAll() throws SQLException {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection conn = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<User> users = new ArrayList<>();
        
        String sql = "SELECT * FROM user";
        
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        
            while(rs.next()) {
                String email = rs.getString(1);
                int active = rs.getInt(2);
                String first = rs.getString(3);
                String last = rs.getString(4);
                String password = rs.getString(5);
                int role = rs.getInt(6);
                
                User user = new User(email,active,first,last,password,role);
                users.add(user);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(conn);
        }
        return users;
    }
    
    public User get(String emailSearch) throws SQLException {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection conn = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        
        String sql = "SELECT * FROM user WHERE email = ?";
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, emailSearch);
            rs = ps.executeQuery();
        
            rs.next();
            String email = rs.getString(1);
            int active = rs.getInt(2);
            String first = rs.getString(3);
            String last = rs.getString(4);
            String password = rs.getString(5);
            int role = rs.getInt(6);
            user = new User(email,active,first,last,password,role);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(conn);
        }
        return user;
    }
    
    public void add(User user) throws SQLException{
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection conn = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "INSERT into user VALUES (?,?,?,?,?,?)";
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setInt(2, user.getActive());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getRole());
            ps.executeUpdate();
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(conn);
        }
        
    }
    
    public void update(User user) throws SQLException {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection conn = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "UPDATE user SET "
                     + "active = ?, "
                     + "first_name = ?, "
                     + "last_name = ?, "
                     + "password = ?, "
                     + "role = ? "
                     + "WHERE email = ?";
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getActive());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getRole());
            ps.setString(6, user.getEmail());
            ps.executeUpdate();
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(conn);
        }
        
    }
    
    public void delete(User user) throws SQLException {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection conn = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "DELETE FROM user WHERE email = ?";
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.executeUpdate();
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(conn);
        }
    }
}
