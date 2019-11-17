import JSON.JSONUser;
import Model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
            saveInfoToFile(ip);
            requestDispatcher.include(request, response);
        } else {
            if (JSONUser.readUsersList()) {
                userList = JSONUser.getUserList();
                if (checkUserAtList(login, password)) {
                    request.setAttribute("login",login);
                    chain.doFilter(request, response);
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("index");
                    request.setAttribute("komunikat", "<center><font color=red><h2>Błędny login lub hasło!</h2></font></center>");
                    saveInfoToFile(ip);
                    rd.include(request, response);
                }
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

    private void saveInfoToFile(String ip) throws IOException {
        File file = new File("C:\\Users\\tocza\\Desktop\\Java PWSZ\\Lista5\\pytod\\Dane\\Logs.txt");
        FileWriter fr = new FileWriter(file, true);
        fr.write(new Date().toString() + ": Nieudana proba logowania. IP:" + ip + "\n");
        fr.close();
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
