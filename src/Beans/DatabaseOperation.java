package Beans;

import Model.User;

import javax.faces.context.FacesContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatabaseOperation {
    public static Statement st;
    public static Connection connection;
    public static ResultSet rs;
    public static PreparedStatement pst;

    private static Connection connectToDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/pytod?useUnicode=yes&characterEncoding=UTF-8";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static List<User> getUsers() {

        List<User> users = new ArrayList<User>();
        try {
            st = connectToDatabase().createStatement();
            rs = st.executeQuery("select * from user where deleted=0");

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setId_type_user(rs.getInt("id_type_user"));
//                user.setId_type_user(rs.getInt("id_type_user"));
//                user.setId_type_user(rs.getInt("id_type_user"));
//                user.setId_type_user(rs.getInt("id_type_user"));
                users.add(user);
            }
            rs.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static String getUsernameById(int id) {
        String username = null;
        try {
            pst = connectToDatabase().prepareStatement("select username from user WHERE id=" + id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            username = rs.getString("username");
            rs.close();
            pst.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }

    public static String getNameTypeOfUser(int id) {
        String nameOfType = null;
        try {
            pst = connectToDatabase().prepareStatement("select name from type_user WHERE id=" + id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            nameOfType = rs.getString("name");
            rs.close();
            pst.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nameOfType;
    }

    public static String getUserToEditById(int id) {
        UserBean user = null;
        Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        try {
            st = connectToDatabase().createStatement();
            rs = st.executeQuery("select * from user WHERE id=" + id);
            if (rs != null) {
                rs.next();
                user = new UserBean();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setId_type_user(rs.getInt("id_type_user"));
                user.setBlocked(rs.getBoolean("blocked"));
                user.setMustChangePassword(rs.getBoolean("must_change_password"));
                user.setMustShowAlert(rs.getBoolean("show_alert"));
                user.setAlertText(rs.getString("text_alert"));
            }
            sessionMapObj.put("editUser", user);
            rs.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "edytuj-uzytkownika.xhtml?faces-redirect=true";
    }

    public static String updateUserByEdit(UserBean updateUser) {
        try {
            pst = connectToDatabase().prepareStatement("update user set password=?, id_type_user=?, blocked=?, must_change_password=?,show_alert=?,text_alert=? where id=?");
            pst.setString(1, updateUser.getPassword());
            pst.setInt(2, updateUser.getId_type_user());
            pst.setBoolean(3,updateUser.isBlocked());
            pst.setBoolean(4,updateUser.isMustChangePassword());
            pst.setBoolean(5,updateUser.isMustShowAlert());
            pst.setString(6,updateUser.getAlertText());
            pst.setInt(7, updateUser.getId());
            pst.executeUpdate();
            connection.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return "lista-uzytkownikow.xhtml?faces-redirect=true";
    }

    public static String deleteUser(int id) {
        try {
            pst = connectToDatabase().prepareStatement("UPDATE user SET username=?, email=?, deleted=? where id = " + id);
            pst.setString(1, "Użytkownik usunięty");
            pst.setString(2, "Użytkownik usunięty");
            pst.setInt(3, 1);
            pst.executeUpdate();
            connection.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return "lista-uzytkownikow.xhtml?faces-redirect=true";
    }

    public static String getUserListBySearch(String searchUsername) {
        Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        List<User> users = getUsers();
        List<User> newUsers = new ArrayList<>();
        for (User user : users) {
            if (user.getUsername().matches("^" + searchUsername + ".*")) {
                newUsers.add(user);
            }
        }
        sessionMapObj.put("listUser", newUsers);

        return "wyszukiwanie.xhtml?faces-redirect=true";


    }

    public static void turnOffMustShowAlert(int id) {
        try {
            pst = connectToDatabase().prepareStatement("UPDATE user SET show_alert=? where id = " + id);
            pst.setBoolean(1, false);
            pst.executeUpdate();
            connection.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
    }
}
