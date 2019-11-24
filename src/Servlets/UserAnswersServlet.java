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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserAnswersServlet")
public class UserAnswersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        JSONAnswerPost.setRealPath(getServletContext().getRealPath(""));
        List<AnswerPost> answerPostList = JSONAnswerPost.getAllAnswerstListByUserName(request.getParameter("nick"));
        int currentPage;
        int maxPost = Integer.parseInt(getServletContext().getInitParameter("MaxAllPost"));
        int maxPage = (answerPostList.size() / maxPost);
        if(maxPost * maxPage < answerPostList.size())
            maxPage++;
        if(request.getParameterMap().containsKey("strona")) {
            if (request.getParameter("strona").isEmpty() || request.getParameter("strona") == null) {
                currentPage = 1;
            } else currentPage = Integer.parseInt(request.getParameter("strona"));
        } else currentPage = 1;

        List<AnswerPost> pagePostList = getPostListByPagination(answerPostList,currentPage,maxPost);
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("maxPage",maxPage);
        request.setAttribute("postList", pagePostList);
        request.setAttribute("nick",request.getParameter("nick"));
        RequestDispatcher rd = request.getRequestDispatcher("lista-odpowiedzi.jsp");
        rd.forward(request, response);
    }

    private List<AnswerPost> getPostListByPagination(List<AnswerPost> answersPostList, int currentPage, int maxPost) {
        List<AnswerPost> list = new ArrayList<>();
        for (int i = ((currentPage - 1) * maxPost); i < ((currentPage - 1) * maxPost) + maxPost; i++) {
            try {
                list.add(answersPostList.get(i));
            } catch (IndexOutOfBoundsException e) {
//                e.printStackTrace();
            }
        }
        return list;
    }
}
