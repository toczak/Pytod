package Servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

@WebServlet(name = "ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String password = request.getParameter("inputPassword");
        String password2 = request.getParameter("inputPassword2");

        if (password.isEmpty() || password2.isEmpty()) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("zmien-haslo.jsp");
            out.println("<font color=red><h2>Uzupełnij wszystkie pola!</h2></font>");
            requestDispatcher.include(request, response);
        } else {
            boolean correctValue = true;

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
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("zmien-haslo.jsp");
                out.println("<font color=red><h1>Popraw wskazane pola!</h1></font><br/>");
                requestDispatcher.include(request, response);
            } else {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pytod?useUnicode=yes&characterEncoding=UTF-8", "root", "");
                    RequestDispatcher rd = request.getRequestDispatcher("index");
                    request.setAttribute("komunikat", "<center><font color='green'><h2>Poprawnie zmieniłeś hasło!</h2></font></center>");
                    PreparedStatement preparedStatement = conn.prepareStatement("UPDATE user SET password=?, must_change_password=? WHERE id="+request.getSession().getAttribute("login"));
                    preparedStatement.setString(1, password);
                    preparedStatement.setBoolean(2, false);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    conn.close();
                    request.getSession().setAttribute("mustChangePassword",false);
                    rd.forward(request, response);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
