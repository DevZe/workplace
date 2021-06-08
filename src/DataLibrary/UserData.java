package DataLibrary;

import Api.controller.UserController;
import DataLibrary.Models.UserModel;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class UserData {

    UserController userController;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    String token="";

    //this method maps the usermodel into the
    public Map<String,String> MapUser(UserModel userModel){
        Map<String,String> user = new HashMap<>();
        user.put("Username",userModel.getUsername());
        user.put("Password",userModel.getPassword());
        user.put("grant_type","password");
        return user;
    }

    public Map<String,String> MapNewUser(UserModel newUserModel){
        Map<String,String> user = new HashMap<>();
        user.put("Email",newUserModel.getUsername());
        user.put("Password",newUserModel.getPassword());
        user.put("ConfirmPassword",newUserModel.getPassword());
        return user;
    }

    //this method logs the user in and return stores the token
    public String AuthenticateUser(UserModel user) throws IOException {
        userController = new UserController();
        ObjectMapper objectMapper = new ObjectMapper();

        int statCode = 0;
        Boolean logged = false;

        if (userController!=null){
            try {
                statCode = userController.GetAuth(MapUser(user));
               if(statCode == 200){
                    logged = true;
                    Map<String, String> map = objectMapper.readValue(userController.reponseBody, new TypeReference<>() {});
                    token = map.get("access_token");//access token will help in future
                    System.out.println("token :"+ token);
                }else{
                   System.out.println("Shit went down at UserData, line 33 reached");             }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return token;
    }

    public Boolean SignUp(UserModel userModel){
        userController = new UserController();
        int statCode = 0;
        Boolean registered=false;

        if (userController!=null){
            try {
                statCode = userController.SigningUp(MapNewUser(userModel));
                if (statCode == 200){
                    registered = true;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return registered;
    }

    public boolean UserLogged(UserModel userModel){
        userController = new UserController();
        int statCode = 0;
        Boolean registered=false;

        if (userController!=null){
            try {
                registered = userController.GetUserInfo(AuthenticateUser(userModel));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return registered;
    }
}
