package com.nagp.msa.inventoryservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceProviderDetails {
    private String userName;
    private boolean loggedIn;

    private String scope;

    @JsonCreator
    public ServiceProviderDetails(@JsonProperty("cUsername") String userName,
                                  @JsonProperty("loggedIn") boolean loggedIn, @JsonProperty("scope")
                                      String scope) {
        this.userName = userName;
        this.loggedIn = loggedIn;
        this.scope = scope;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
