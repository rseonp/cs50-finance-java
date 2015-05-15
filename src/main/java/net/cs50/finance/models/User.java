package net.cs50.finance.models;

import net.cs50.finance.models.util.PasswordHash;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by cbay on 5/10/15.
 */

@Entity
@Table(name = "users")
public class User {

    private int id;
    private String userName;
    private String hash;
    //private HashMap<String, StockHolding> portfolio = new HashMap<String, StockHolding>();

    public User() {}

    public User(String userName, String password) {
        this.hash = PasswordHash.getHash(password);
        this.userName = userName;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    // TODO - handle non-unique user names appropriately
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

    private void setHash(String hash) {
        this.hash = hash;
    }


    /*public HashMap<String, StockHolding> getPortfolio() {
        return portfolio;
    }*/

}
