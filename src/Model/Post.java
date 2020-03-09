package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {
    private int id;
    private String textQuestion;
    private int author;
    private Date date;

    public Post(int id, String textQuestion, int author, Date date) {
        this.id = id;
        this.textQuestion = textQuestion;
        this.author = author;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextQuestion() {
        return textQuestion;
    }

    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
