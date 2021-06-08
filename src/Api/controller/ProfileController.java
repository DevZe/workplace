package Api.controller;

import Api.ApiClient;
import Api.model.ProfileModel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class ProfileController {

    final String urlP = "https://localhost:44369/api/Profile/RegisterProfile";
    ApiClient apiClient;

    public ProfileModel getProfileModel() {
        return profileModel;
    }

    public void setProfileModel(ProfileModel profileModel) {
        this.profileModel = profileModel;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    ProfileModel profileModel;
    String UserId;


    public boolean FillUp(Map<String,String> profile) throws InterruptedException, NoSuchAlgorithmException, IOException {
        int statCode=0;
        boolean filled=false;
        profile.put("UserId",getUserId());
        apiClient = new ApiClient();
        if (apiClient!=null){
            statCode = apiClient.postAsync(urlP,profile);
            if (statCode==200){
                filled=true;
            }
        }
        return filled;
    }


}
