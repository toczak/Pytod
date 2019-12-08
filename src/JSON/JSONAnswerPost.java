package JSON;

import Model.AnswerPost;
import Model.Post;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONAnswerPost {

    static List<AnswerPost> answerPostList;
    private static String realPath = null;

    public static void setRealPath(String path) {
        realPath = path;
    }

    public static int getCountOfAnswers() {
        return answerPostList.size();
    }

    public static List<AnswerPost> getAnswerPostList() {
        return answerPostList;
    }

    public static List<AnswerPost> getAnswerPostListWithPostId(int id) {
        List<AnswerPost> list = new ArrayList<>();
        for (AnswerPost answerPost : answerPostList) {
//            if (answerPost.getIdPost() == id)
//                list.add(answerPost);
        }
        return list;
    }

    public static List<AnswerPost> getAllAnswerstListByUserName(String searchText) {
        readAnswerPostList();
        List<AnswerPost> answersListNew = new ArrayList<>();
        for (int i = answerPostList.size() - 1; i >= 0; i--) {
//            String author = answerPostList.get(i).getAuthor();
//            if (author.matches(".*\\b" + searchText + "\\b.*")) {
//                answersListNew.add(answerPostList.get(i));
//            }
        }
        return answersListNew;
    }

    public static int getCountAnswerByPostId(int id) {
        readAnswerPostList();
        int count = 0;
//        for (AnswerPost answerPost : answerPostList) {
//            if (answerPost.getIdPost() == id)
//                count++;
//        }
        return count;
    }


    public static boolean readAnswerPostList() {
        JSONParser jsonParser = new JSONParser();
        answerPostList = new ArrayList<>();
        try (FileReader reader = new FileReader(realPath + "Dane\\Answers.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray answerPostListJSON = (JSONArray) obj;
            answerPostListJSON.forEach(answer -> parseAnswerPostObject((JSONObject) answer));
        } catch (IOException e) {
            System.out.println("------------------- Błąd w operacji na pliku!");
//                        e.printStackTrace();
            return false;
        } catch (ParseException e) {
            System.out.println("------------------- Błąd w parsowaniu!");
//            e.printStackTrace();
            return false;

        }
        return true;
    }

    static void parseAnswerPostObject(JSONObject postJSON) {
        JSONObject postObject = (JSONObject) postJSON.get("answer");
//        AnswerPost post = new AnswerPost();
//        post.setId(((Long) postObject.get("id")).intValue());
//        post.setIdPost(((Long) postObject.get("idPost")).intValue());
//        post.setText((String) postObject.get("text"));
//        post.setAuthor((String) postObject.get("author"));
//        post.setDate((String) postObject.get("date"));
//        answerPostList.add(post);
    }

    public static void saveAnswerPostListToJSONFile(List<AnswerPost> answerPostList) {
        JSONArray answerPostListJSON = new JSONArray();
        for (AnswerPost post : answerPostList) {
            JSONObject postJSON = new JSONObject();
//            postJSON.put("id", post.getId());
//            postJSON.put("idPost", post.getIdPost());
//            postJSON.put("text", post.getText());
//            postJSON.put("author", post.getAuthor());
            postJSON.put("date", post.getDate());

            JSONObject postObject = new JSONObject();
            postObject.put("answer", postJSON);
            answerPostListJSON.add(postObject);
        }

        try (FileWriter file = new FileWriter(realPath + "Dane\\Answers.json")) {
            file.write(answerPostListJSON.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
