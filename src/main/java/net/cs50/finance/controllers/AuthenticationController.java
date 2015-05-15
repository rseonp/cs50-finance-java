package net.cs50.finance.controllers;

import net.cs50.finance.controllers.AbstractFinanceController;
import net.cs50.finance.models.User;
import net.cs50.finance.models.dao.UserDao;
import net.cs50.finance.models.util.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cbay on 5/15/15.
 */
@Controller
public class AuthenticationController extends AbstractFinanceController {

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String displayRegisterForm() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(String userName, String password, String confirmPassword, Model model) {

        User existingUser = userDao.findByUserName(userName);

        if (!password.equals(confirmPassword)) {
            return this.displayError("Passwords do not match. Try again.", model);
        } else if (existingUser != null) {
            return this.displayError(
                    "The username " + userName + " already exits in the system. Please select a different username", model);
        }

        User newUser = new User(userName, password);
        userDao.save(newUser);
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String displayLoginForm(){
        return "login";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "login";
    }

    @RequestMapping(value = {"/", "/portfolio"}, method = RequestMethod.GET)
    public String displayPortfolio(){
        return "portfolio";
    }

    @RequestMapping(value = "/portfolio", method = RequestMethod.POST)
    public String verifyLogin(String userName, String password, HttpServletRequest request, Model model){

        // If accessing this path via POST, we're logging in

        User user = userDao.findByUserName(userName);

        // User is invalid
        if (user == null) {
            return this.displayError("Invalid username.", model);
        } else if (!PasswordHash.isValidPassword(password, user.getHash())) {
            return this.displayError("Invalid password.", model);
        }

        // User is valid; set in session
        request.getSession().setAttribute(userSessionKey, user.getUserName());

        return "portfolio";
    }

}
