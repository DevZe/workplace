import DataLibrary.Models.UserModel;
import DataLibrary.UserData;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, NoSuchAlgorithmException {
        UserData userData = new UserData();
        UserModel userModel = new UserModel();
        Boolean registered = false;
        if((userModel!=null) && (userData!=null))


        userModel.setUsername("zeie@mail.com");
        userModel.setPassword("Zeie@123");

        registered = userData.UserLogged(userModel);
        System.out.println(registered);

       /* Boolean logged = userData.Login(userModel);
        System.out.println(logged);*/
    }


}
