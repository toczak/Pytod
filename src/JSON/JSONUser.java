package JSON;

import Model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONUser {

    static List<User> userList;

    public static List<User> getUserList() {
        return userList;
    }

    public static boolean readUsersList() {
        JSONParser jsonParser = new JSONParser();
        userList = new ArrayList<>();
        try (FileReader reader = new FileReader("C:\\Users\\tocza\\Desktop\\Java PWSZ\\Lista5\\pytod\\Dane\\Users.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray userListJSON = (JSONArray) obj;
//            System.out.println(userListJSON);
//            for(Object userObject : userListJSON){
//                parseUserObject((JSONObject) userObject);
//            }
            userListJSON.forEach(user -> parseUserObject((JSONObject) user));

        } catch (IOException e) {
            System.out.println("------------------- Błąd w operacji na pliku!");
            return false;
        } catch (ParseException e) {
            System.out.println("------------------- Błąd w parsowaniu!");
            return false;

        }
        return true;
    }

    static void parseUserObject(JSONObject userJSON) {
        JSONObject userObject = (JSONObject) userJSON.get("user");
        User user = new User();
        user.setUsername((String) userObject.get("name"));
        user.setEmail((String) userObject.get("email"));
        user.setPassword((String) userObject.get("password"));
        userList.add(user);
    }

    public static void saveUserListToJSONFile(List<User> userList) {
        JSONArray userListJSON = new JSONArray();
        for (User user : userList) {
            JSONObject userJSON = new JSONObject();
            userJSON.put("name", user.getUsername());
            userJSON.put("email", user.getEmail());
            userJSON.put("password", user.getPassword());

            JSONObject userObject = new JSONObject();
            userObject.put("user",userJSON);
            userListJSON.add(userObject);
        }

        try (FileWriter file = new FileWriter("Dane/Users.json")) {
            file.write(userListJSON.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
