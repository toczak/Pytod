package Model;

import java.util.Date;

public class AnswerPost {
    private int idPost;
    private String author;
    private String text;
    private String date;

    public AnswerPost(int idPost, String author, String text, String date) {
        this.idPost = idPost;
        this.author = author;
        this.text = text;
        this.date = date;
    }

    public AnswerPost() {

    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
