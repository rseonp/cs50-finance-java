package net.cs50.finance.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Chris Bay on 5/17/15.
 */
@Controller
public class PortfolioController extends AbstractFinanceController {

    @RequestMapping("/portfolio")
    public String portfolio(Model model){

        // TODO - query for portfolio and pass to view

        return "portfolio";
    }

    // TODO - implement history

}
