package net.cs50.finance.controllers;

import net.cs50.finance.models.Stock;
import net.cs50.finance.models.StockHolding;
import net.cs50.finance.models.StockLookupException;
import net.cs50.finance.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Chris Bay on 5/17/15.
 */
@Controller
public class PortfolioController extends AbstractFinanceController {

    @RequestMapping(value = "/portfolio")
    public String portfolio(HttpServletRequest request, Model model){

        User user = getUserFromSession(request);
        Map<String, StockHolding> portfolio = user.getPortfolio();
        List<HashMap<String, String>> tableValues = new ArrayList<HashMap<String, String>>();

        try {
            for (StockHolding holding : portfolio.values()) {
                if (holding.getSharesOwned() > 0) {
                    HashMap<String, String> stockValues = new HashMap<String, String>();
                    Stock stock = Stock.lookupStock(holding.getSymbol());

                    stockValues.put("name", stock.toString());
                    stockValues.put("sharesOwned", "" + holding.getSharesOwned());
                    stockValues.put("currentPrice", "" + stock.getPrice());
                    stockValues.put("value", "" + (holding.getSharesOwned() * stock.getPrice()));
                    tableValues.add(stockValues);
                }
            }
        } catch (StockLookupException e) {
            e.printStackTrace();
            return this.displayError("Could not fetch portfolio information.", model);
        }

        model.addAttribute("portfolio", tableValues);
        model.addAttribute("title", "Portfolio");
        model.addAttribute("portfolioNavClass", "active");

        return "portfolio";
    }

}
