package JSON;

import Model.Post;
import Model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JSONPost {

    static List<Post> postList;

    public static List<Post> getPostList() {
        return postList;
    }

    public static List<Post> getPostListReverse() {
        readPostList();
        List<Post> postListNew = new ArrayList<>();
        for (int i = postList.size() - 1; i >= 0; i--) {
            postListNew.add(postList.get(i));
        }
        return postListNew;
    }

    public static List<Post> searchAndGetPostListReverse(String searchText) {
        readPostList();
        List<Post> postListNew = new ArrayList<>();
        for (int i = postList.size() - 1; i >= 0; i--) {
            String question = postList.get(i).getTextQuestion();
            if(question.matches(".*\\b"+searchText+"\\b.*")) {
                postListNew.add(postList.get(i));
            }
        }
        return postListNew;
    }

    public static boolean readPostList() {
        JSONParser jsonParser = new JSONParser();
        postList = new ArrayList<>();
        try (FileReader reader = new FileReader("C:\\Users\\tocza\\Desktop\\Java PWSZ\\Lista5\\pytod\\Dane\\Posts.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray postListJSON = (JSONArray) obj;
            postListJSON.forEach(post -> parsePostObject((JSONObject) post));

        } catch (IOException e) {
            System.out.println("------------------- Błąd w operacji na pliku!");
            return false;
        } catch (ParseException e) {
            System.out.println("------------------- Błąd w parsowaniu!");
//            e.printStackTrace();
            return false;

        }
        return true;
    }

    static void parsePostObject(JSONObject postJSON) {
        JSONObject postObject = (JSONObject) postJSON.get("post");
        Post post = new Post();
        post.setId(((Long) postObject.get("id")).intValue());
        post.setTextQuestion((String) postObject.get("textQuestion"));
        post.setAuthor((String) postObject.get("author"));
        post.setDate((String) postObject.get("date"));
        post.setCountAnswers(JSONAnswerPost.getCountAnswerByPostId(post.getId()));

//        post.setCountAnswers(((Long) postObject.get("countAnswers")).intValue());
        postList.add(post);
    }

    public static void savePostListToJSONFile(List<Post> postList) {
        JSONArray postListJSON = new JSONArray();
        for (Post post : postList) {
            JSONObject postJSON = new JSONObject();
            postJSON.put("id", post.getId());
            postJSON.put("textQuestion", post.getTextQuestion());
            postJSON.put("author", post.getAuthor());
            postJSON.put("date", post.getDate());
            postJSON.put("countAnswer", post.getCountAnswers());

            JSONObject postObject = new JSONObject();
            postObject.put("post", postJSON);
            postListJSON.add(postObject);
        }

        try (FileWriter file = new FileWriter("Dane/Posts.json")) {
            file.write(postListJSON.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
