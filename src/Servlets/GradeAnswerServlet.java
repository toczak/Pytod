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
import java.util.List;

@WebServlet(name = "GradeAnswerServlet")
public class GradeAnswerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int grade = Integer.parseInt(request.getParameter("selectValue"));
        int idPost = Integer.parseInt(request.getParameter("idPost"));
        int idAnswer = Integer.parseInt(request.getParameter("idAnswer"));

        if(grade>0 && grade<=10 && !JSONGradeAnswer.checkGradeAnswerOfUser(idAnswer,(String) request.getSession().getAttribute("login"))) {
            JSONGradeAnswer.setRealPath(getServletContext().getRealPath(""));
            JSONGradeAnswer.readGradeAnswerPostList();
            List<GradeAnswer> gradeAnswerList = JSONGradeAnswer.getGradeAnswerList();
            GradeAnswer gradeAnswer = new GradeAnswer();
            gradeAnswer.setGrade(grade);
            gradeAnswer.setAnswerId(idAnswer);
            gradeAnswer.setUsername((String) request.getSession().getAttribute("login"));
            gradeAnswerList.add(gradeAnswer);
            JSONGradeAnswer.saveGradeAnswerListToJSONFile(gradeAnswerList);
        }
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        request.setAttribute("id",idPost);
        RequestDispatcher rd = request.getRequestDispatcher("pytanie");
        rd.include(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
