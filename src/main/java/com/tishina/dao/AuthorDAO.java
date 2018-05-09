package com.tishina.dao;

import com.tishina.model.Author;

import java.util.Collection;

public interface AuthorDAO {

    Author getById(Integer id);
    Collection<Author> getAllAuthors();
    Collection<Author> getAuthorsByName(String name, boolean matchAllWordOnly, boolean matchStartWith);
}
