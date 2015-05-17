package net.cs50.finance;

import net.cs50.finance.controllers.AbstractFinanceController;
import net.cs50.finance.models.User;
import net.cs50.finance.models.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cbay on 5/11/15.
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        List<String> authPages = Arrays.asList("/login", "/register", "/logout", "/portfolio");

        // Require sign-in for all but auth pages
        if ( !authPages.contains(request.getRequestURI()) ) {

            String userId = (String) request.getSession().getAttribute(AbstractFinanceController.userSessionKey);

            if (userId == null) {
                response.sendRedirect("/login");
                return false;
            }

            User user = userDao.findById(userId);

            // If no ID present in session, redirect to login
            if (user == null) {
                response.sendRedirect("/login");
                return false;
            }
        }

        return true;
    }

}
