import JSON.JSONUser;
import Model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.List;

@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    List<User> userList;

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String login = request.getParameter("inputLogin");
        String password = request.getParameter("inputPassword");

        String ip = request.getRemoteAddr();
        if (login.isEmpty() || password.isEmpty()) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index");
            request.setAttribute("komunikat", "<font color=red><h2>Uzupełnij wszystkie pola!</h2></font>");
            saveInfoToFile(request.getServletContext().getRealPath(""), ip);
            requestDispatcher.include(request, response);
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pytod","root","");
                Statement statement = conn.createStatement();
                String sql = "SELECT id, id_type_user FROM user WHERE (email='"+login+"' OR username='"+login+"') AND password='"+password+"'";
                ResultSet resultSet = statement.executeQuery(sql);
                if (resultSet.isBeforeFirst() ) {
                    resultSet.next();
                    int id = resultSet.getInt("id");
                    int id_type = resultSet.getInt("id_type_user");
                    sql = "SELECT blocked,must_change_password,show_alert,text_alert FROM user WHERE id="+resultSet.getInt("id");
                    ResultSet resultSet2 = statement.executeQuery(sql);
                    resultSet2.next();
                    if(!resultSet2.getBoolean("blocked")) {
                        request.setAttribute("login", id);
                        request.setAttribute("type", id_type);
                        request.setAttribute("mustChangePassword", resultSet2.getBoolean("must_change_password"));
                        request.setAttribute("mustShowAlert", resultSet2.getBoolean("show_alert"));
                        request.setAttribute("alertText", resultSet2.getString("text_alert"));
                        chain.doFilter(request, response);
                    } else{
                        RequestDispatcher rd = request.getRequestDispatcher("index");
                        request.setAttribute("komunikat", "<center><font color=red><h2>Konto zablokowane! Skontaktuj się z administratorem.</h2></font></center>");
                        saveInfoToFile(request.getServletContext().getRealPath(""), ip);
                        rd.include(request, response);
                    }
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("index");
                    request.setAttribute("komunikat", "<center><font color=red><h2>Błędny login lub hasło!</h2></font></center>");
                    saveInfoToFile(request.getServletContext().getRealPath(""), ip);
                    rd.include(request, response);
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private boolean checkUserAtList(String login, String password) {
        for (User user : userList) {
            if ((login.equalsIgnoreCase(user.getUsername()) || login.equalsIgnoreCase(user.getEmail())) && password.equals(user.getPassword()))
                return true;
        }
        return false;
    }

    private void saveInfoToFile(String realPath, String ip) throws IOException {
        File file = new File(realPath + "Dane\\Logs.txt");
        FileWriter fr = new FileWriter(file, true);
        fr.write(new Date().toString() + ": Nieudana proba logowania. IP:" + ip + "\n");
        fr.close();
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
