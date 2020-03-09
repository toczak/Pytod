package Servlets;

import Beans.DatabaseOperation;

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
        request.getSession().setAttribute("login", request.getAttribute("login"));
        request.getSession().setAttribute("type", request.getAttribute("type"));
        request.getSession().setAttribute("mustChangePassword", request.getAttribute("mustChangePassword"));
        request.getSession().setAttribute("mustShowAlert", request.getAttribute("mustShowAlert"));
        request.getSession().setAttribute("alertText", request.getAttribute("alertText"));
        if (((boolean) request.getAttribute("mustShowAlert")))
            DatabaseOperation.turnOffMustShowAlert((int) request.getAttribute("login"));
        RequestDispatcher rd = request.getRequestDispatcher("index");
        if((boolean) request.getAttribute("mustChangePassword")){
            rd = request.getRequestDispatcher("zmien-haslo.jsp");
        }
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
