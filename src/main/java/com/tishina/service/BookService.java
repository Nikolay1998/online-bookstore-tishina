package com.tishina.service;

import com.tishina.dao.BookDAO;
import com.tishina.dao.DAOFactoryHolder;
import com.tishina.model.Book;

import java.util.List;

public class BookService {
    private BookDAO bookDAO = DAOFactoryHolder.getDAOFactory().getBookDAO();

    public Book findBookById(Integer id) {
        return bookDAO.getById(id);
    }

    public List<Book> findBooksByCategoryId(Integer id) {
        System.out.println("findBooksByCategoryId:  Start");
        Book.Category category = Book.Category.getCategoryById(id);

        System.out.println("findBooksByCategoryId: category = "
                + (category == null ? "null" :category.getId())+"; "
                + (category == null ? "null" :category.getName()));
        return bookDAO.getBooksByCategory(category);
    }
}
