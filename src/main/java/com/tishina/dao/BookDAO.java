package com.tishina.dao;

import com.tishina.model.Book;

public interface BookDAO {

    Book getById(Integer id);
    void updateBook(Book book);

}
