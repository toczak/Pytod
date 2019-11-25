package JSON;

import Model.AnswerPost;
import Model.GradeAnswer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONGradeAnswer {
    static List<GradeAnswer> gradeAnswerList;
    private static String realPath = null;

    public static void setRealPath(String path) {
        realPath = path;
    }

    public static String getAverageOfAnswer(int idAnswer){
        readGradeAnswerPostList();
        String answer;
        double average = 0;
        int count = 0;
        for(GradeAnswer grade : gradeAnswerList){
            if(grade.getAnswerId()==idAnswer){
                average += grade.getGrade();
                count++;
            }
        }
        if(average==0.0){
            answer="Brak ocen";
        } else {
            answer = String.valueOf(average/count);
        }
        return answer;
    }

    public static List<GradeAnswer> getGradeAnswerList() {
        return gradeAnswerList;
    }

    public static boolean readGradeAnswerPostList() {
        JSONParser jsonParser = new JSONParser();
        gradeAnswerList = new ArrayList<>();
        try (FileReader reader = new FileReader(realPath + "Dane\\GradeAnswer.json")) {
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
        JSONObject postObject = (JSONObject) postJSON.get("gradeAnswer");
        GradeAnswer post = new GradeAnswer();
        post.setAnswerId(((Long) postObject.get("idAnswer")).intValue());
        post.setUsername(((String) postObject.get("username")));
        post.setGrade(((Long) postObject.get("grade")).intValue());
        gradeAnswerList.add(post);
    }

    public static void saveGradeAnswerListToJSONFile(List<GradeAnswer> gradeAnswerList) {
        JSONArray gradeAnswerPostListJSON = new JSONArray();
        for (GradeAnswer post : gradeAnswerList) {
            JSONObject postJSON = new JSONObject();
            postJSON.put("idAnswer", post.getAnswerId());
            postJSON.put("username", post.getUsername());
            postJSON.put("grade", post.getGrade());

            JSONObject postObject = new JSONObject();
            postObject.put("gradeAnswer", postJSON);
            gradeAnswerPostListJSON.add(postObject);
        }

        try (FileWriter file = new FileWriter(realPath + "Dane\\GradeAnswer.json")) {
            file.write(gradeAnswerPostListJSON.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkGradeAnswerOfUser(int idAnswer, String login) {
        readGradeAnswerPostList();
        for(GradeAnswer grade : gradeAnswerList){
            if(grade.getAnswerId()==idAnswer && login.equals(grade.getUsername())){
                return true;
            }
        }
        return false;
    }
}
