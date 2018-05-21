package com.tishina.dao;

import com.tishina.model.Book;

import java.util.List;

public interface BookDAO {

    Book getById(Integer id);
    List<Book> getBooksByCategory(Book.Category category);
    void updateBook(Book book);

}
