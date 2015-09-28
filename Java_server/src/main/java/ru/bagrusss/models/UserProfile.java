package ru.bagrusss.models;

/**
 * Created by vladislav.
 */

public class UserProfile {
    private String userPassword;
    private String userLogin;
    private String userEmail;
    public UserProfile(String login, String password, String email){
        userLogin=login;
        userEmail=email;
        userPassword=password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}