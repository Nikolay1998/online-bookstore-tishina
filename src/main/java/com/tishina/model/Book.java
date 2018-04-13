package com.tishina.model;

public class Book {

    private Integer id;
    private String name;
    private String description;
    private Author author;
    private Integer amount;

    public Book(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Book(Integer id, String name, String description, Author author, Integer amount) {
        this(id, name);
        this.description = description;
        this.author = author;
        this.amount = amount;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
