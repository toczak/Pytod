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
    private static String realPath;

    public static void setRealPath(String path){
        realPath = path;
    }

    public static List<User> getUserList() {
        return userList;
    }

    public static User getUserInfo(String searchText) {
        readUsersList();
        User userNew = new User();
        for (User user : userList) {
            String author = user.getUsername();
            if (searchText.equals(author)) {
                userNew = user;
                break;
            }
        }
        return userNew;
    }

    public static boolean readUsersList() {
        JSONParser jsonParser = new JSONParser();
        userList = new ArrayList<>();
        try (FileReader reader = new FileReader(realPath + "Dane\\Users.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray userListJSON = (JSONArray) obj;
            userListJSON.forEach(user -> parseUserObject((JSONObject) user));

        } catch (IOException e) {
            System.out.println("------------------- Błąd w operacji na pliku!");
                        e.printStackTrace();

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

        try (FileWriter file = new FileWriter(realPath + "Dane\\Users.json")) {
            file.write(userListJSON.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
