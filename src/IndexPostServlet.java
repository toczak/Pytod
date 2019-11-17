/*
import JSON.JSONAnswerPost;
import JSON.JSONPost;
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

@WebServlet(name = "IndexPostServlet")
public class IndexPostServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        JSONPost.readPostList();
        List<Post> postList = JSONPost.getPostListReverse();
        request.setAttribute("postList", postList);

        int currentPage;
        int maxPost = Integer.parseInt(getServletContext().getInitParameter("maxPost"));
        int maxPage = (postList.size() / maxPost);
        if(maxPost * maxPage < postList.size())
            maxPage++;
        if(request.getParameterMap().containsKey("strona")) {
            if (request.getParameter("strona").isEmpty() || request.getParameter("strona") == null) {
                currentPage = 1;
            } else currentPage = Integer.parseInt(request.getParameter("strona"));
        } else currentPage = 1;

        request.setAttribute("currentPage",currentPage);
        request.setAttribute("maxPage",maxPage);
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);

    }
}
*/

import JSON.JSONAnswerPost;
import JSON.JSONPost;
import Model.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "IndexPostServlet")
public class IndexPostServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        JSONPost.readPostList();
        List<Post> postList = JSONPost.getPostListReverse();
        int currentPage;
        int maxPost = Integer.parseInt(getServletContext().getInitParameter("maxPost"));
        int maxPage = (postList.size() / maxPost);
        if(maxPost * maxPage < postList.size())
            maxPage++;
        if(request.getParameterMap().containsKey("strona")) {
            if (request.getParameter("strona").isEmpty() || request.getParameter("strona") == null) {
                currentPage = 1;
            } else currentPage = Integer.parseInt(request.getParameter("strona"));
        } else currentPage = 1;

        List<Post> pagePostList = getPostListByPagination(postList,currentPage,maxPost);
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("maxPage",maxPage);
        request.setAttribute("postList", pagePostList);

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    private List<Post> getPostListByPagination(List<Post> postList, int currentPage, int maxPost) {
        List<Post> list = new ArrayList<>();
        for(int i=((currentPage-1)*maxPost);i<((currentPage-1)*maxPost)+maxPost;i++){
            try{
                list.add(postList.get(i));
            }
            catch(IndexOutOfBoundsException e){
//                System.out.println("Blad w indexpostservlet");
//                System.out.println(i + " " + currentPage + " " + maxPost);

            }

        }
        return list;
    }
}

