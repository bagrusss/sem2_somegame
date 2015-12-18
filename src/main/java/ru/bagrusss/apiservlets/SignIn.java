package ru.bagrusss.apiservlets;


import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import ru.bagrusss.helpers.Errors;
import ru.bagrusss.servces.account.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignIn extends BaseServlet {

    public static final String URL = "/signin/";

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject params;
        try {
            params = mGson.fromJson(req.getReader(), JsonObject.class);
        } catch (JsonSyntaxException e) {
            Errors.errorInvalidJson(resp);
            return;
        }
        String email;
        String password;
        String type;
        try {
            email = params.get(EMAIL).getAsString();
            password = params.remove(PASSWORD).getAsString();
            type = params.get(TYPE).getAsString();
        } catch (NullPointerException e) {
            Errors.errorAPI(resp, Errors.CODE_INVALID_REQUEST, Errors.MESSAGE_INVALID_REQUEST);
            return;
        }
        UserProfile user = mAccountService.getUser(email);
        if (user != null) {
            if (user.getmUserPassword().equals(password)) {
                params.addProperty(ID, user.getId());
                Errors.correct(resp, params);
                return;
            }
        }
        Errors.errorAPI(resp, Errors.CODE_USER_NOT_EXISTS, Errors.MESSAGE_USER_NOT_EXISTS);
    }
}