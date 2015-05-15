package net.cs50.finance.controllers;

import net.cs50.finance.models.User;
import net.cs50.finance.models.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cbay on 5/15/15.
 */
public abstract class AbstractFinanceController {

    @Autowired
    protected UserDao userDao;

    private static final String errorTemplate = "error";
    private static final String errorMessageIdentifier = "message";

    public static final String userSessionKey = "id";

    public String displayError(String message, Model model) {
        model.addAttribute(errorMessageIdentifier, message);
        return errorTemplate;
    }

    public User getUserFromSession(HttpServletRequest request){

        String userId = (String) request.getSession().getAttribute(userSessionKey);

        if (userId == null) return null;

        return userDao.findById(userId);

    }

    // jesse was here. (Just testing git. Delete this comment.)

}
