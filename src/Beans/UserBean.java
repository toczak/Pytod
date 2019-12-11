package Beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@RequestScoped
public class UserBean {
    private int id;
    private int id_type_user;
    private String username;
    private String email;
    private String password;
    private String typeName;
    public List userList;

    public UserBean(int id, int id_type_user, String username, String email, String password) {
        this.id = id;
        this.id_type_user = id_type_user;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserBean() {
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

    @PostConstruct
    public void init() {
        userList = DatabaseOperation.getUsers();
    }

    public List getUserList() {
        return userList;
    }

    public String getNameOfTypeUser(int id) {
        return DatabaseOperation.getNameTypeOfUser(id);
    }

    public String editUser(int id) {
        return DatabaseOperation.getUserToEditById(id);
    }

    public String updateUser(UserBean updateUser) {
        return DatabaseOperation.updateUserByEdit(updateUser);
    }

    public String deleteUser(int id) {
        return DatabaseOperation.deleteUser(id);
    }

    public String getUsernameById(int id) {
        return DatabaseOperation.getUsernameById(id);
    }

}
