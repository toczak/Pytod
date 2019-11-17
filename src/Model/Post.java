package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {
    private int id;
    private String textQuestion;
    private String author;
    private String date;
    private int countAnswers;
//    List<AnswerPost> answerPosts = new ArrayList<>();

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCountAnswers() {
        return countAnswers;
    }

    public void setCountAnswers(int countAnswers) {
        this.countAnswers = countAnswers;
    }

//    public List<AnswerPost> getAnswerPosts() {
//        return answerPosts;
//    }
//
//    public void setAnswerPosts(List<AnswerPost> answerPosts) {
//        this.answerPosts = answerPosts;
//    }
}
