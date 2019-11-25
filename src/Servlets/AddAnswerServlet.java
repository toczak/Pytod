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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "Servlets.AddAnswerServlet")
public class AddAnswerServlet extends HttpServlet {
    List<AnswerPost> answerPostList;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String answerText = request.getParameter("textareaAnswer");

        if (answerText.isEmpty()) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("pytanie.jsp");
            out.println("<font color=red><h2>Treść odpowiedzi nie może być pusta!</h2></font>");
            requestDispatcher.include(request, response);
        } else {
            JSONAnswerPost.setRealPath(getServletContext().getRealPath(""));
            JSONAnswerPost.readAnswerPostList();
            answerPostList = JSONAnswerPost.getAnswerPostList();
            AnswerPost answerPost = new AnswerPost();
            answerPost.setAuthor((String) request.getSession().getAttribute("login"));
            int id = Integer.parseInt(request.getParameter("id"));
            answerPost.setId(answerPostList.size() + 1);
            answerPost.setIdPost(id);
            answerPost.setText(answerText);
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            answerPost.setDate(formatter.format(date));
            answerPostList.add(answerPost);
            JSONAnswerPost.saveAnswerPostListToJSONFile(answerPostList);
            RequestDispatcher rd = request.getRequestDispatcher("pytanie");
            request.setAttribute("id",id);
            rd.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
