package Servlets;

import JSON.JSONAnswerPost;
import Model.AnswerPost;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@WebServlet(name = "Servlets.AddAnswerServlet")
public class AddAnswerServlet extends HttpServlet {
    List<AnswerPost> answerPostList;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String answerText = request.getParameter("textareaAnswer");
        int id_post = Integer.parseInt(request.getParameter("id"));
        int id_user = (int) request.getSession().getAttribute("login");

        if (answerText.isEmpty()) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("pytanie");
            request.setAttribute("id",id_post);
            out.println("<div style=\"margin-top: 50px\"><center><font color=\"red\"><h2>Treść odpowiedzi nie może być pusta!</h2></font></center></div>");
            requestDispatcher.include(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("pytanie");
            request.setAttribute("id",id_post);
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pytod?useUnicode=yes&characterEncoding=UTF-8", "root", "");
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO answer_post(id_post,id_user,text,date) VALUES(?,?,?,?)");
                preparedStatement.setInt(1, id_post);
                preparedStatement.setInt(2, id_user);
                preparedStatement.setString(3, answerText);
                preparedStatement.setTimestamp(4, new Timestamp(Calendar.getInstance().getTime().getTime()));
                preparedStatement.executeUpdate();
                preparedStatement.close();
                conn.close();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            rd.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
