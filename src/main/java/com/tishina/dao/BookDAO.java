package com.tishina.dao;

import com.tishina.model.Author;
import com.tishina.model.Book;

import java.util.List;

public interface BookDAO {

    Book getById(Integer id);
    List<Book> getBooksByCategory(Book.Category category);
    List<Book> getBooksByNameAndAuthor(String name, Author author);
    Book getBookByNameAndAuthor(String name, Author author);
    void updateBook(Book book);
    Book createBook(Book book);

}
