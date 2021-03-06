package ru.bagrusss.frontend;


import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.bagrusss.models.UserProfile;
import ru.bagrusss.servces.AccountService;
import ru.bagrusss.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    private final AccountService accountService;
    public static final String URL = "/signup";

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void doGet(@NotNull HttpServletRequest request,
                      @NotNull HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println(PageGenerator.getPage("signup.html", null));
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(@NotNull HttpServletRequest req, @NotNull HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        JSONObject responseText = new JSONObject();
        resp.setStatus(HttpServletResponse.SC_OK);
        String result;
        String message;
        UserProfile user=new UserProfile(name, password, email);
        if (accountService.addUser(name, user)) {
            result = "OK";
            message="User successfuly created!";
            accountService.doSaveUser(req, user);
        } else {
            result = "FAIL";
            message="User already exist! Try agan.";
        }
        try {
            responseText.put("status", HttpServletResponse.SC_OK);
            responseText.put("res", result);
            responseText.put("message", message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        resp.getWriter().println(responseText.toString());
    }
}
