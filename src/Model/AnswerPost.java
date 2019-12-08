package Model;

import java.util.Date;

public class AnswerPost {
    private int id;
    private int id_post;
    private int id_user;
    private String text;
    private Date date;

    public AnswerPost(int id, int id_post, int id_user, String text, Date date) {
        this.id = id;
        this.id_post = id_post;
        this.id_user = id_user;
        this.text = text;
        this.date = date;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
