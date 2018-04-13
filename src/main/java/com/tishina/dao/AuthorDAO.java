package com.tishina.dao;

import com.tishina.model.Author;

import java.util.Collection;

public interface AuthorDAO {

    public Author getById(Integer id);
    public Collection<Author> getAllAuthors();
    public Collection<Author> getAuthorsByName(String name, boolean matchAllWordOnly, boolean matchStartWith);
}
