package com.nagp.msa.profilemanagement.model;

public class UserDetails {

    private String cUsername;
    private String cSecret;
    private String name;
    private long contact;
    private String address;
    private boolean loggedIn;

    private String scope;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getcUsername() {
        return cUsername;
    }

    public void setcUsername(String cUsername) {
        this.cUsername = cUsername;
    }

    public String getcSecret() {
        return cSecret;
    }

    public void setcSecret(String cSecret) {
        this.cSecret = cSecret;
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

    @Override
    public String toString() {
        return "CustomerDetails{" +
                "cUsername='" + cUsername + '\'' +
                ", cSecret='" + cSecret + '\'' +
                ", name='" + name + '\'' +
                ", contact=" + contact +
                ", address='" + address + '\'' +
                ", loggedIn=" + loggedIn +
                '}';
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

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public UserDetails(String cUsername, String cSecret, String name, long contact, String address, boolean loggedIn, String scope) {
        this.cUsername = cUsername;
        this.cSecret = cSecret;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.loggedIn = loggedIn;
        this.scope = scope;
    }
}
