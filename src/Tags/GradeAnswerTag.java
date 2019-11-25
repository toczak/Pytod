package Tags;

import JSON.JSONGradeAnswer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class GradeAnswerTag extends TagSupport {

    private int idAnswer;
    private int idPost;

    public void setIdPost(int idPost){
        this.idPost = idPost;
    }

    public void setIdAnswer(int idAnswer) {
        this.idAnswer = idAnswer;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print("<small class='text-muted mr-auto ml-3 mt-1'>Ocena: <b>"+ JSONGradeAnswer.getAverageOfAnswer(idAnswer) +"</b></small>");
            out.print("<div><form class='form-inline' method='post' action='ocen'><select class='form-control form-control-sm' name='selectValue'>");
            for (int i = 10; i >= 1; i--) {
                out.print("<option value='" + i + "'>" + i + "</option>");
            }
            out.print("</select>");
            out.print("<input type='hidden' name='idAnswer' value='"+idAnswer+"'>");
            out.print("<input type='hidden' name='idPost' value='"+idPost+"'>");
            out.print("<input type='submit' value='Oceń' name='buttonIdPost' class='btn btn-primary btn-sm ml-1 mr-3'/>" +
                    "</form></div>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
