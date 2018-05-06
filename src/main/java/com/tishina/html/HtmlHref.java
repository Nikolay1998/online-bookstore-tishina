package com.tishina.html;

public class HtmlHref {
    private String jspLink;
    private String id;
    private String name;

    public HtmlHref(String jspLink, String name) {
        this.jspLink = jspLink;
        this.name = name;
    }

    public HtmlHref(String jspLink, Object id, String name) {
        this.jspLink = jspLink;
        this.name = name;

        if (id instanceof String) {
            this.id = (String) id;
        } else if (id instanceof Integer) {
            this.id = id.toString();
        }
    }

    public String print() {
        StringBuilder sb = new StringBuilder()
                .append("<a href=")
                .append(jspLink)
                .append(id == null ? "" : "?id="+id)
                .append(">")
                .append(name)
                .append("</a><br/>");
        return sb.toString();
    }
}
