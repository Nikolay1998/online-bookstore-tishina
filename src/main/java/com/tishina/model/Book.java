package com.tishina.model;

public class Book {

    private Integer id;
    private String name;
    private String description;
    private Author author;
    private Integer whAmount;
    private Integer orderedAmount = 0;

    public Book(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Book(Integer id, String name, String description, Author author, Integer whAmount) {
        this(id, name);
        this.description = description;
        this.author = author;
        this.whAmount = whAmount;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    public Integer getWhAmount() {
        return whAmount;
    }
    public void setWhAmount(Integer whAmount) {
        this.whAmount = whAmount;
    }
    public Integer getOrderedAmount() {
        return orderedAmount;
    }
    public void setOrderedAmount(Integer orderedAmount) {
        this.orderedAmount = orderedAmount;
    }
}
