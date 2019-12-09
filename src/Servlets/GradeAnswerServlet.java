package Servlets;

import JSON.JSONGradeAnswer;
import JSON.JSONPost;
import JSON.JSONUser;
import Model.GradeAnswer;
import Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet(name = "GradeAnswerServlet")
public class GradeAnswerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int grade = Integer.parseInt(request.getParameter("selectValue"));
        int idPost = Integer.parseInt(request.getParameter("idPost"));
        int idAnswer = Integer.parseInt(request.getParameter("idAnswer"));
        int idUser = (int) request.getSession().getAttribute("login");
        Connection conn;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pytod", "root", "");
            if(grade>0 && grade<=10 && !userGradedAnswer(conn, idAnswer,idUser)) {
                RequestDispatcher rd = request.getRequestDispatcher("index");
                request.setAttribute("komunikat", "<center><font color='green'><h2>Poprawnie się zarejestrowałeś! Możesz się zalogować.</h2></font></center>");
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO grade_answer(id_answer_post,id_user,grade) VALUES(?,?,?)");
                preparedStatement.setInt(1, idAnswer);
                preparedStatement.setInt(2, idUser);
                preparedStatement.setInt(3, grade);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                conn.close();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        request.setAttribute("id",idPost);
        RequestDispatcher rd = request.getRequestDispatcher("pytanie");
        rd.include(request, response);
    }

    private boolean userGradedAnswer(Connection conn, int id_answer, int id_user) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "SELECT id FROM grade_answer WHERE id_answer_post='" + id_answer + "' and id_user='" + id_user+"'";
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet.isBeforeFirst();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
