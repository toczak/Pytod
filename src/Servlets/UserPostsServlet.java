package Servlets;

import JSON.JSONPost;
import Model.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserPostsServlet")
public class UserPostsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
//        JSONPost.setRealPath(getServletContext().getRealPath(""));
//        JSONPost.readPostList();
        List<Post> postList = getAllPostListByUserName(Integer.parseInt(request.getParameter("id_user")));
        int currentPage;
        int maxPost = Integer.parseInt(getServletContext().getInitParameter("MaxAllPost"));
        int maxPage = (postList.size() / maxPost);
        if (maxPost * maxPage < postList.size())
            maxPage++;
        if (request.getParameterMap().containsKey("strona")) {
            if (request.getParameter("strona").isEmpty() || request.getParameter("strona") == null) {
                currentPage = 1;
            } else currentPage = Integer.parseInt(request.getParameter("strona"));
        } else currentPage = 1;

        List<Post> pagePostList = getPostListByPagination(postList, currentPage, maxPost);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("maxPage", maxPage);
        request.setAttribute("postList", pagePostList);
        request.setAttribute("id_user", request.getParameter("id_user"));
        RequestDispatcher rd = request.getRequestDispatcher("lista-pytan.jsp");
        rd.forward(request, response);
    }

    private List<Post> getPostListByPagination(List<Post> postList, int currentPage, int maxPost) {
        List<Post> list = new ArrayList<>();
        for (int i = ((currentPage - 1) * maxPost); i < ((currentPage - 1) * maxPost) + maxPost; i++) {
            if (i < postList.size())
                try {
                    list.add(postList.get(i));
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
        }
        return list;
    }

    private List<Post> getAllPostListByUserName(int id_user) {
        List<Post> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pytod", "root", "");
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM post WHERE id_user=" + id_user;
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new Post(resultSet.getInt("id"), resultSet.getString("text"), resultSet.getInt("id_user"), resultSet.getTimestamp("date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
