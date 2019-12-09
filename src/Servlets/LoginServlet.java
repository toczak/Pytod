package Servlets;

import JSON.JSONUser;
import Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Servlets.LoginServlet")
public class LoginServlet extends HttpServlet implements ServletRequestListener {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        RequestDispatcher rd = request.getRequestDispatcher("index");
        request.getSession().setAttribute("login", request.getAttribute("login"));
        request.getSession().setAttribute("type", request.getAttribute("type"));
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
