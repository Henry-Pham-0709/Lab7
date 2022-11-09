package ca.sait.models;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
/**
 *
 * @author phamh
 */
@Entity
@Table(name = "role")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT u FROM Role u")
    , @NamedQuery(name = "Role.findById", query = "SELECT u FROM Role u WHERE u.id = :id")
    , @NamedQuery(name = "Role.findByName", query = "SELECT u FROM Role u WHERE u.name = :name")
              })
public class Role implements Serializable{
    @Id
    @Basic(optional = false)
    @Column(name = "role_id")
    private int id;
    @Basic(optional = false)
    @Column(name = "role_name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role", fetch = FetchType.EAGER)
    private ArrayList<User> users;

    public Role() {
    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public ArrayList<User> getUserList() {
        return this.users;
    }
    
    public void setUserList(ArrayList<User> users) {
        this.users = users;
    }
    
}
