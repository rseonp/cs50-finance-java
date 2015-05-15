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

    protected void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public float getPrice() {
        return this.price;
    }

    private void setPrice(float price) {
        this.price = price;
    }

    private float getCurrentPrice() {
        // TODO - make remote call to get updated price info
        return 0;
    }
}
