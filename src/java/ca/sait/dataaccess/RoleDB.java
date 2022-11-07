/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sait.dataaccess;

import ca.sait.models.Role;
import java.util.*;
import java.sql.*;

/**
 *
 * @author phamh
 */
public class RoleDB {
    public String get(int id) throws SQLException{
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection conn = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String roleDescription;
        
        String sql = "SELECT role_name FROM role WHERE role_id = ?";
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
        
            rs.next();
            roleDescription = rs.getString(1);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(conn);
        }
        return roleDescription;
        
        
    }
    
    public ArrayList<Role> getAll() throws SQLException {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection conn = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Role> roles = new ArrayList<>();
        
        String sql = "SELECT * FROM role";
        
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        
            while(rs.next()) {
                int roleId = rs.getInt(1);
                String roleDescription = rs.getString(2);
                
                Role role = new Role(roleId, roleDescription);
                roles.add(role);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(conn);
        }
        return roles;
    }
}
