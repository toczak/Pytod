package Servlets;

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
import java.util.*;
import java.util.regex.Pattern;

@WebServlet(name = "Servlets.RegisterServlet")
public class RegisterServlet extends HttpServlet {
    List<User> userList;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("inputNick");
        String email = request.getParameter("inputEmail");
        String password = request.getParameter("inputPassword");
        String password2 = request.getParameter("inputPassword2");

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("rejestracja.jsp");
            out.println("<font color=red><h2>Uzupełnij wszystkie pola!</h2></font>");
            requestDispatcher.include(request, response);
        } else {
            boolean correctValue = true;

            request.setAttribute("name", name);
            if (!Pattern.matches("[\\w]{2,20}", name)) {
                correctValue = false;
                request.setAttribute("resultName", "<font color=red><i>Podany nick nie jest poprawne!</i></font><br/>");
            }

            request.setAttribute("email", email);
            if (!Pattern.matches("[\\w.%+-]+@[\\w.-]+\\.[a-zA-z]{2,4}", email)) {
                correctValue = false;
                request.setAttribute("resultEmail", "<font color=red><i>Podany email nie jest poprawny!</i></font><br/>");
            }

            request.setAttribute("password", password);
            if (!Pattern.matches(".{8,}", password)) {
                correctValue = false;
                request.setAttribute("resultPassword", "<font color=red><i>Podane haslo nie spelnia wymogow!</i></font><br/>");
            }

            request.setAttribute("password2", password2);
            if (!password.equals(password2)) {
                correctValue = false;
                request.setAttribute("resultPassword2", "<font color=red><i>Podane hasla roznia sie!</i></font><br/>");
            }

            if (!correctValue) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("rejestracja.jsp");
                out.println("<font color=red><h1>Popraw wskazane pola!</h1></font><br/>");
                requestDispatcher.include(request, response);
            } else {
                JSONUser.setRealPath(getServletContext().getRealPath(""));
                if (JSONUser.readUsersList()) {
                    userList = JSONUser.getUserList();
                    if (!checkUserAtList(name, email)) {
                        System.out.println("Jest już dany uzytkownik!");
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("rejestracja.jsp");
                        out.println("<font color=red><h2>Użytkownik o podanym loginie/mailu już istnieje!</h2></font>");
                        requestDispatcher.include(request, response);
                        return;
                    }
                } else userList = new ArrayList<>();
                RequestDispatcher rd = request.getRequestDispatcher("index");
                request.setAttribute("komunikat","<center><font color='green'><h2>Poprawnie się zarejestrowałeś! Możesz się zalogować.</h2></font></center>");
                User user = new User(name, email, password);
                userList.add(user);
                JSONUser.saveUserListToJSONFile(userList);
                rd.forward(request, response);
            }
        }
    }

    private boolean checkUserAtList(String name, String email) {
        for (User user : userList) {
            if (name.equalsIgnoreCase(user.getUsername()) || email.equals(user.getEmail()))
                return false;
        }
        return true;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
