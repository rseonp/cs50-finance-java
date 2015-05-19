package net.cs50.finance.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Chris Bay on 5/17/15.
 */
@Controller
public class PortfolioController extends AbstractFinanceController {

    @RequestMapping(value = "/portfolio")
    public String portfolio(HttpServletRequest request, Model model){

        // TODO - Implement portfolio display

        model.addAttribute("title", "Portfolio");
        model.addAttribute("portfolioNavClass", "active");

        return "portfolio";
    }

    // TODO - Implement transaction history view

}
