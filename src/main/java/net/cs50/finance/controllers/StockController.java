package net.cs50.finance.controllers;

import net.cs50.finance.models.Stock;
import net.cs50.finance.models.StockLookupException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Chris Bay on 5/17/15.
 */
@Controller
public class StockController extends AbstractFinanceController {

    // TODO - implement quote, buy, sell

    @RequestMapping(value = "/quote", method = RequestMethod.GET)
    public String quoteForm() {
        return "quote_form";
    }

    @RequestMapping(value = "/quote", method = RequestMethod.POST)
    public String quote(String symbol, Model model) {

        Stock stock;

        try {
            stock = Stock.lookupStock(symbol);
        } catch (StockLookupException e) {
            e.printStackTrace();
            return displayError("Error retrieving info for stock: " + symbol, model);
        }

        model.addAttribute("stock_desc", stock.getSymbol() + " (" + stock.getName() + ")");
        model.addAttribute("stock_price", "" + stock.getPrice());

        return "quote_display";
    }

}
