package com.tishina.html;

public class Href {
    private String link;
    private String name;

    public Href(String link, String name) {
        this.link = link;
        this.name = name;
    }

    public String print() {
        return "<a href=\"/Tishina"+link+"\">"+name+"</a><br/>";
    }
}
