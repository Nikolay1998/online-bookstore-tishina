package com.tishina.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

    private Integer id;
    private Client client;
    private String status;
    private Date startDate;
    private Date completionDate;
    private Map<Book, Integer> books;

    public Order(Client client, List<Book> books) {
        this.client = client;
        this.books = new HashMap<>();
        for (Book book : books) {
            this.books.put(book, book.getOrderedAmount());
        }
    }

    public Order(Integer id, Client client, Map<Book, Integer> books) {
        this.id = id;
        this.client = client;
        this.books = books;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getCompletionDate() {
        return completionDate;
    }
    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Map<Book, Integer> getBooks() {
        return books;
    }
    public void setBooks(Map<Book, Integer> books) {
        this.books = books;
    }
    public boolean isActive(){return "Active".equals(status);}
}
