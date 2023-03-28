package com.nagp.msa.walletservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDetails {

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public WalletMoney getWalletMoney() {
        return walletMoney;
    }

    public void setWalletMoney(WalletMoney walletMoney) {
        this.walletMoney = walletMoney;
    }

    private String Username;
    private String name;
    private long contact;
    private String address;
    private WalletMoney walletMoney;

    @JsonCreator
    public UserDetails(@JsonProperty("cUsername") String cUsername, @JsonProperty("name") String name,
                       @JsonProperty("contact") long contact,
                       @JsonProperty("address") String address) {
        this.Username = cUsername;
        this.name = name;
        this.contact = contact;
        this.address = address;
    }
}
