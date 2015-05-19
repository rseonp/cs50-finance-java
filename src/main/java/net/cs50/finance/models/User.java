package net.cs50.finance.models;

import net.cs50.finance.models.util.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cbay on 5/10/15.
 */

@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    private String userName;
    private String hash;
    private Map<String, StockHolding> portfolio;

    public User() {}

    public User(String userName, String password) {
        this.hash = PasswordHash.getHash(password);
        this.userName = userName;
        this.portfolio = new HashMap<String, StockHolding>();
    }

    @NotNull
    @Column(name = "username", unique = true, nullable = false)
    public String getUserName() {
        return userName;
    }

    protected void setUserName(String userName){
        this.userName = userName;
    }

    @NotNull
    @Column(name = "hash")
    public String getHash() {
        return hash;
    }

    protected void setHash(String hash) {
        this.hash = hash;
    }

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "owner_id")
    public Map<String, StockHolding> getPortfolio() {
        return portfolio;
    }

    private void setPortfolio(Map<String, StockHolding> portfolio) {
        this.portfolio = portfolio;
    }

    void addHolding (StockHolding holding) throws IllegalArgumentException {

        // Ensure a holding for the symbol doesn't already exist
        if (portfolio.containsKey(holding.getSymbol())) {
            throw new IllegalArgumentException("A holding for symbol " + holding.getSymbol()
                    + " already exits for user " + getUid());
        }

        portfolio.put(holding.getSymbol(), holding);
    }

}