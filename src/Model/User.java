package Model;

import Beans.DatabaseOperation;
import Beans.UserBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private int id_type_user;
    private String username;
    private String email;
    private String password;
    private String typeName;
    public List<User> userList;

    public User(int id, int id_type_user, String username, String email, String password) {
        this.id = id;
        this.id_type_user = id_type_user;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_type_user() {
        return id_type_user;
    }

    public void setId_type_user(int id_type_user) {
        this.id_type_user = id_type_user;
    }

}
