package com.tishina.dao.impl;

import com.tishina.dao.AuthorDAO;
import com.tishina.dao.TishinaDataSource;
import com.tishina.dao.util.ResultSetHandler;
import com.tishina.model.Author;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("unchecked")
public class AuthorMysqlDAO implements AuthorDAO {
    private static final int AUTHOR_COUNT_PER_SELECT = 10;
    private static final String getAllAuthorsQuery = "select id, name, about from Author LIMIT "+AUTHOR_COUNT_PER_SELECT;

    public Collection<Author> getAllAuthors() {
        return (Collection<Author>) TishinaDataSource.executePreparedStatement(
                getAllAuthorsQuery,
                new ResultSetHandler<Collection<Author>>() {
                    @Override
                    public Collection<Author> handle(ResultSet rs) throws Exception {
                        Collection<Author> authors = new ArrayList<>(AUTHOR_COUNT_PER_SELECT);
                        while (rs.next()) {
                            Author author = new Author(rs.getInt(1),
                                    rs.getString(2),
                                    rs.getString(3));
                            authors.add(author);
                        }
                        return authors;
                    }
                });
    }


}
