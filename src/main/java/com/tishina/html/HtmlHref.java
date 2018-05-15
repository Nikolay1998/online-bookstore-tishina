package com.tishina.html;

import java.util.HashMap;
import java.util.Map;

public class HtmlHref {
    private String jspLink;
    private String id;
    private String name;
    private Map<String, String> params = new HashMap<>();

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

    public HtmlHref addParameter(String paramName, Object paramValue) {
        if (paramValue instanceof String) {
            params.put(paramName, (String) paramValue);
        } else if (paramValue instanceof Integer) {
            params.put(paramName, String.valueOf(paramValue));
        }
        return this;
    }

    public String print() {
        StringBuilder sb = new StringBuilder()
                .append("<a href=")
                .append(jspLink);
        if (params.isEmpty()) {
            sb.append(id == null ? "" : "?id=" + id);
        } else {
            sb.append("?");
            boolean isFirst = true;
            for (Map.Entry<String, String> paramAndValue : params.entrySet()) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append("&");
                }
                sb.append(paramAndValue.getKey())
                        .append("=")
                        .append(paramAndValue.getValue());
            }
        }
        sb.append(">")
                .append(name)
                .append("</a><br/>");
        return sb.toString();
    }
}
