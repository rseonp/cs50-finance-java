package net.cs50.finance.models;

/**
 * Created by cbay on 5/15/15.
 */
public class Stock {

    private final String symbol;
    private final float price;
    private final String name;

    private Stock(String symbol, float price, String name) {
        this.symbol = symbol;
        this.price = price;
        this.name = name;
    }

    public static Stock lookupStock(String symbol) {
        // TODO - lookup info
        return new Stock("asdf", 0, "asdf");
    }

    public String getSymbol() {
        return symbol;
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

}
