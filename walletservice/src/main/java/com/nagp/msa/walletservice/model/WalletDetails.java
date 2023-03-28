package com.nagp.msa.walletservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WalletDetails {

    private String username;
    private int walletMoney;
    public WalletDetails(){

    }
    @JsonCreator
    public WalletDetails(@JsonProperty("username") String username, @JsonProperty("money") int walletMoney) {
        this.username = username;
        this.walletMoney = walletMoney;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getWalletMoney() {
        return walletMoney;
    }

    public void setWalletMoney(int walletMoney) {
        this.walletMoney = walletMoney;
    }
}
