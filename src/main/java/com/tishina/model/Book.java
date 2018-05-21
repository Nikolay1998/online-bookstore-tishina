package com.tishina.model;

public class Book {

    private Integer id;
    private String name;
    private String description;
    private Author author;
    private Category category;
    private Double price;
    private Integer whAmount;
    private Integer orderedAmount = 0;

    public Book(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Book(Integer id, String name, String description, Author author, Integer whAmount, int categoryId
                ) {
        this(id, name);
        this.description = description;
        this.author = author;
        this.whAmount = whAmount;
        this.category = Category.getCategoryById(categoryId);
        this.price = price;
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
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public enum Category {
        FIRST(1, "Учебная литература"),
        SECOND(2, "Детям и родителям"),
        THIRD(3, "Бизнес-литература"),
        FORTH(4, "Художественная литература"),
        FIFTH(5, "Научная литература");

        Integer id;
        String name;

        Category(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId(){return id;}

        public String getName() {
            return name;
        }

        public static Category getCategoryById(Integer id) {
            System.out.println("getCategoryById: id = "+id);
            System.out.println("getCategoryById: FORTH.id = "+FORTH.id);
            if (FIRST.id.equals(id)) return FIRST;
            else if (SECOND.id.equals(id)) return SECOND;
            else if (THIRD.id.equals(id)) return THIRD;
            else if (FORTH.id.equals(id)) return FORTH;
            else if (FIFTH.id.equals(id)) return FIFTH;
            return null;
        }
    }
}
