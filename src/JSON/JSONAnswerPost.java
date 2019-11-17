package JSON;

import Model.AnswerPost;
import Model.Post;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONAnswerPost {

    static List<AnswerPost> answerPostList;

    public static List<AnswerPost> getAnswerPostList() {
        return answerPostList;
    }

    public static List<AnswerPost> getAnswerPostListWithPostId(int id){
        List<AnswerPost> list = new ArrayList<>();
        for(AnswerPost answerPost : answerPostList){
            if(answerPost.getIdPost()==id)
                list.add(answerPost);
        }
        return list;
    }

    public static int getCountAnswerByPostId(int id){
        readAnswerPostList();
        int count=0;
        for(AnswerPost answerPost : answerPostList){
            if(answerPost.getIdPost()==id)
                count++;
        }
        return count;
    }

    public static boolean readAnswerPostList() {
        JSONParser jsonParser = new JSONParser();
        answerPostList = new ArrayList<>();
        try (FileReader reader = new FileReader("C:\\Users\\tocza\\Desktop\\Java PWSZ\\Lista5\\pytod\\Dane\\Answers.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray answerPostListJSON = (JSONArray) obj;
//            System.out.println(answerPostListJSON);
//            for(Object userObject : postListJSON){
//                parseUserObject((JSONObject) userObject);
//            }
            answerPostListJSON.forEach(answer -> parseAnswerPostObject((JSONObject) answer));

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

    static void parseAnswerPostObject(JSONObject postJSON) {
        JSONObject postObject = (JSONObject) postJSON.get("answer");
        AnswerPost post = new AnswerPost();
        post.setIdPost(((Long) postObject.get("idPost")).intValue());
        post.setText((String) postObject.get("text"));
        post.setAuthor((String) postObject.get("author"));
        post.setDate((String) postObject.get("date"));
        answerPostList.add(post);
    }

    public static void saveAnswerPostListToJSONFile(List<AnswerPost> answerPostList) {
        JSONArray answerPostListJSON = new JSONArray();
        for (AnswerPost post : answerPostList) {
            JSONObject postJSON = new JSONObject();
            postJSON.put("idPost", post.getIdPost());
            postJSON.put("text", post.getText());
            postJSON.put("author", post.getAuthor());
            postJSON.put("date", post.getDate());

            JSONObject postObject = new JSONObject();
            postObject.put("answer", postJSON);
            answerPostListJSON.add(postObject);
        }

        try (FileWriter file = new FileWriter("C:\\Users\\tocza\\Desktop\\Java PWSZ\\Lista5\\pytod\\Dane\\Answers.json")) {
            file.write(answerPostListJSON.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
