import JSON.JSONUser;
import Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    List<User> userList;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        RequestDispatcher rd = request.getRequestDispatcher("index");
        request.getSession().setAttribute("login", request.getAttribute("login"));
        rd.forward(request, response);

//        PrintWriter out = response.getWriter();
//        String login = request.getParameter("inputLogin");
//        String password = request.getParameter("inputPassword");
//
//        if (login.isEmpty() || password.isEmpty()) {
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("zaloguj.jsp");
//            out.println("<font color=red><h2>Uzupełnij wszystkie pola!</h2></font>");
//            requestDispatcher.include(request, response);
//        } else {
//            if (JSONUser.readUsersList()) {
//                userList = JSONUser.getUserList();
//                if (checkUserAtList(login, password)) {
//                    RequestDispatcher rd = request.getRequestDispatcher("index");
//                    System.out.println("Zalogowany!");
//                    request.getSession().setAttribute("login",login);
//                    rd.forward(request, response);
//                    return;
//                }
//                else {
//                    RequestDispatcher rd = request.getRequestDispatcher("index");
//                    request.setAttribute("komunikat","<center><font color=red><h2>Błędne hasło!</h2></font></center>");
//                    rd.include(request, response);
//                    return;
//                }
//            }
//        }
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("zaloguj.jsp");
//        out.println("<font color=red><h2>W systemie nie ma takiego użytkownika!</h2></font>");
//        requestDispatcher.include(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private boolean checkUserAtList(String login, String password) {
        for (User user : userList) {
            if ((login.equalsIgnoreCase(user.getUsername()) || login.equalsIgnoreCase(user.getEmail())) && password.equals(user.getPassword()))
                return true;
        }
        return false;
    }

}
