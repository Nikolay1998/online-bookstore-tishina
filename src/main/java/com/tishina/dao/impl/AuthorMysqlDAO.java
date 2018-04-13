package com.tishina.dao.impl;

import com.tishina.dao.AuthorDAO;
import com.tishina.dao.TishinaDataSource;
import com.tishina.dao.util.ResultSetHandler;
import com.tishina.model.Author;
import com.tishina.model.Book;

import java.sql.JDBCType;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("unchecked")
public class AuthorMysqlDAO implements AuthorDAO {
    private static final int AUTHOR_COUNT_PER_SELECT = 10;
    private static final String getAllAuthorsQuery = "select id, name, about from Author LIMIT "+AUTHOR_COUNT_PER_SELECT;
    private static final String getAuthorByIdQuery = "select a.id, a.name, a.about, b.id, b.name " +
            "from Author a left join Book b on a.id = b.author_id where a.id = ?";
    private static final String getAuthorsByNameQuery = "select id, name, about from Author where name = ?";

    @Override
    public Author getById(Integer id) {
        System.out.println("AuthorMysqlDAO.getById START for id = "+id);
        return (Author) TishinaDataSource.executePreparedStatement(
                getAuthorByIdQuery,
                new Object[][]{{JDBCType.INTEGER, id}},
                new ResultSetHandler<Author>() {
                    @Override
                    public Author handle(ResultSet rs) throws Exception {
                        if (!rs.next()) {
                            throw new RuntimeException("Can't find author with id = "+id);
                        }
                        Author author = null;
                        author = new Author(rs.getInt("a.id"), rs.getString("a.name"), rs.getString("a.about"));
                        Collection<Book> books = new ArrayList<>();
                        do {
                            books.add(new Book(rs.getInt("b.id"), rs.getString("b.name")));
                        } while (rs.next());
                        author.setBooks(books);
                        return author;
                    }
                }
        );
    }

    public Collection<Author> getAllAuthors() {
        return (Collection<Author>) TishinaDataSource.executePreparedStatement(
                getAllAuthorsQuery,
                null,
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

    @Override
    public Collection<Author> getAuthorsByName(String name, boolean matchAllWordOnly, boolean matchStartWith) {
        System.out.println("AuthorMysqlDAO.getAuthorsByName START with name = " + name);
        String queryToExecute = getAuthorsByNameQuery;
        return (Collection<Author>) TishinaDataSource.executePreparedStatement(
                queryToExecute,
                new Object[][] {{JDBCType.VARCHAR, name}},
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
