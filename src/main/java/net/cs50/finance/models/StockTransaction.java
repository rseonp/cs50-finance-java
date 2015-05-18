package net.cs50.finance.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Chris Bay on 5/17/15.
 */
@Entity
@Table(name = "transactions")
public class StockTransaction extends AbstractEntity {

    public enum TransactionType {
        BUY("buy"), SELL("sell");
        private String type;

        private TransactionType(String type){
            this.type = type;
        }
    };

    private TransactionType type;
    private int shares;
    private float price;
    private Date transactionTime;
    private String symbol;
    private int userId;
    private StockHolding stockHolding;

    public StockTransaction(StockHolding stockHolding, int shares, TransactionType type) throws StockLookupException {
        this.stockHolding = stockHolding;
        this.transactionTime = new Date();
        this.symbol = stockHolding.getSymbol();
        this.type = type;
        this.userId = stockHolding.getOwnerId();
        this.price = Stock.lookupStock(symbol).getPrice();
    }

    @ManyToOne
    public StockHolding getStockHolding() {
        return stockHolding;
    }

    private void setStockHolding(StockHolding stockHolding) {
        this.stockHolding = stockHolding;
    }

    @NotNull
    @Column(name = "shares", nullable = false)
    public int getShares() {
        return shares;
    }

    private void setShares(int shares) {
        this.shares = shares;
    }

    @NotNull
    @Column(name = "price", nullable = false)
    public float getPrice() {
        return price;
    }

    private void setPrice(float price) {
        this.price = price;
    }

    @NotNull
    @Column(name = "transaction_time", nullable = false)
    public Date getTransationTime() {
        return transactionTime;
    }

    private void setTransationTime(Date transationTime) {
        this.transactionTime = transationTime;
    }

    @NotNull
    @Column(name = "symbol", nullable = false)
    public String getSymbol() {
        return symbol;
    }

    private void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @NotNull
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    private void setUserId(int userId) {
        this.userId = userId;
    }

}
