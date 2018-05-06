package com.tishina.model;

public class Client {
    private Integer id;
    private String name;
    private String login;
    private String password;

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
}
