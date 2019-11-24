package Servlets;

import JSON.JSONAnswerPost;
import JSON.JSONPost;
import Model.AnswerPost;
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

@WebServlet(name = "Servlets.PostServlet")
public class PostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getAttribute("id") == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index");
            requestDispatcher.forward(request, response);
        } else processRequest(request, response, (Integer) request.getAttribute("id"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("id").isEmpty() || request.getParameter("id") == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index");
            requestDispatcher.forward(request, response);
        } else processRequest(request, response, Integer.parseInt(request.getParameter("id")));
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        JSONPost.setRealPath(getServletContext().getRealPath(""));
        JSONPost.readPostList();
        List<Post> postList = JSONPost.getPostList();
        Post post = postList.get(id - 1);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pytanie.jsp");
        request.setAttribute("id", post.getId());
        request.setAttribute("author", post.getAuthor());
        request.setAttribute("question", post.getTextQuestion());
        request.setAttribute("date", post.getDate());
        request.setAttribute("color", getBackgroundColorByBrowser(request.getHeader("user-agent")));


        //Pobranie wszystkich odpowiedzi
        JSONAnswerPost.readAnswerPostList();
        List<AnswerPost> answerPostList = JSONAnswerPost.getAnswerPostListWithPostId(id);
        request.setAttribute("answerList", answerPostList);
        requestDispatcher.include(request, response);
    }

    private String getBackgroundColorByBrowser(String userAgent) {
        if (userAgent.contains("Chrome")) {
            return "bg-primary";
        } else if (userAgent.contains("Firefox")) {
            return "bg-light";
        }
        return "bg-light";
    }
}
