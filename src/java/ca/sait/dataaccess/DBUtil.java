/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sait.dataaccess;

import javax.persistence.*;

/**
 *
 * @author phamh
 */
public class DBUtil {
    
    private static final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("UsersPU");

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}
