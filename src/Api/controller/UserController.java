package Api.controller;

import Api.ApiClient;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class UserController {
    static final String urlT = "https://localhost:44369/token";//URL of auth/token api
    static final String urlR = "https://localhost:44369/api/Account/Register";
    static final String URLUSERINFOR="https://localhost:44369/api/Account/UserInfo";


    public int responseCode;
    public String reponseBody;
    ApiClient apiClient;

    //this method posts the user name and password for authentication
    public int GetAuth(Map<String,String> data) throws IOException, InterruptedException, NoSuchAlgorithmException {
        apiClient = new ApiClient();
        reponseBody="";
        responseCode=0;
        if(apiClient!=null){
            responseCode = apiClient.postAsync(urlT,data);
            reponseBody = apiClient.body;
        }
        return responseCode;
    }

    public int SigningUp(Map<String,String> user) throws InterruptedException, NoSuchAlgorithmException, IOException {
        int statCode = 0;
        apiClient = new ApiClient();
        if(apiClient!=null){
            statCode = apiClient.postAsync(urlR,user);
        }
        return statCode;
    }

    public boolean GetUserInfo(String token) throws InterruptedException, NoSuchAlgorithmException, IOException {
        boolean registered=false;
        int statCode =0;

        if (apiClient!=null){
            statCode = apiClient.getUserInfor(URLUSERINFOR,token);//this completes the uri query
            if (statCode==200){
                registered = true;
            }
        }else {
            apiClient = new ApiClient();
            statCode = apiClient.getUserInfor(URLUSERINFOR,token);//this completes the uri query
            if (statCode==200){
                registered = true;
            }
        }return  registered;
        }

    }

