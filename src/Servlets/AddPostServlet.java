package Servlets;

import Model.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Servlets.AddPostServlet")
public class AddPostServlet extends HttpServlet {

    List<Post> postList;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String question = request.getParameter("textareaQuestion").replace("\r\n"," <br/> ");

        if (question.isEmpty()) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("zadaj.jsp");
            out.println("<font color=red><h2>Treść pytania nie może być pusta!</h2></font>");
            requestDispatcher.include(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("index");
            rd.include(request, response);
        }
    }
}

