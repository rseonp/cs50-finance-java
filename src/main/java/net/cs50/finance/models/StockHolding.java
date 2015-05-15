package net.cs50.finance.models;

/**
 * Created by cbay on 5/10/15.
 */
public class StockHolding implements Holding {

    private float price;
    private String symbol;
    private int shares;

    public StockHolding() {}

    public StockHolding(String symbol, int shares) {
        this.symbol = symbol;
        this.shares = shares;
    }

    public String getSymbol() {
        return symbol;
    }

    private void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getShares() {
        return shares;
    }

    private void setShares(int shares) {
        this.shares = shares;
    }

    private float getCurrentPrice() {
        // TODO - make remote call to get updated price info
        return 0;
    }

    public void buyShares(int numberOfShares){}

    public void sellShares(int numberOfShares){}
}
