package com.tishina.model;

public class Client {
    private Integer id;
    private String name;
    private String login;
    private String password;
    private Client referral;

    public Client(String name, String login, String password, Client referral) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.referral = referral;
    }

    public Client (Integer id, String name, String login){
        this.id= id;
        this.name = name;
        this.login = login;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLogin() {
        return login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public Client getReferral() {
        return referral;
    }
    public void setReferral(Client referral) {
        this.referral = referral;
    }
}
