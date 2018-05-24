package com.tishina.dao.impl;

import com.tishina.dao.BookDAO;
import com.tishina.dao.TishinaDataSource;
import com.tishina.dao.util.ResultSetHandler;
import com.tishina.model.Author;
import com.tishina.model.Book;

import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class BookMysqlDAO implements BookDAO{
    private static final String GET_BOOK_BY_ID_QUERY =
            "select a.id, a.name, a.about, b.id, b.name, b.about, b.wh_amount, b.price, bc.id cat_id\n" +
            "  from Book b left join Author a on b.author_id = a.id\n" +
            "              left join book_category bc on b.book_category_id = bc.id\n" +
            " where b.id = ?";

    private static final String GET_BOOK_BY_CATEGORY_QUERY =
            "select a.id, a.name, a.about, b.id, b.name, b.about, b.wh_amount, b.price, bc.id cat_id\n" +
            "  from book b join book_category bc on b.book_category_id = bc.id\n" +
            "         left join Author a on b.author_id = a.id\n" +
            " where bc.id = ?";

    private static final String GET_BOOKS_BY_NAME_AND_AUTHOR_QUERY =
            "select b.id from book b join author a on b.author_id = a.id\n" +
            " where b.name = ? and a.name = ?";

    private static final String UPDATE_BOOK_TABLE_QUERY =
            "update book set name = ?, author_id = ?, about = ?, wh_amount = ?, book_category_id = ? where id = ?";

    private static final String INSERT_INTO_BOOK_TABLE_QUERY =
            "insert into book(id, name, author_id) values(null, ?, ?)";

    @Override
    public Book getById(Integer id) {
        return (Book) TishinaDataSource.executePreparedStatement(
                GET_BOOK_BY_ID_QUERY,
                new Object[][]{{JDBCType.INTEGER, id}},
                new ResultSetHandler<Book>(){
                    public Book handle(ResultSet rs) throws SQLException {
                        if (!rs.next()) {
                            throw new RuntimeException("Can't find book with id = "+id);
                        }
                        Author author = new Author(rs.getInt("a.id"),
                                rs.getString("a.name"),
                                rs.getString("a.about"));
                        return new Book(rs.getInt("b.id"),
                                rs.getString("b.name"),
                                rs.getString("b.about"),
                                author,
                                rs.getInt("b.wh_amount"),
                                rs.getInt("cat_id"),
                                rs.getDouble("b.price"));
                    }
                }
        );
    }

    @Override
    public List<Book> getBooksByCategory(Book.Category category) {
        return (List<Book>) TishinaDataSource.executePreparedStatement(
                GET_BOOK_BY_CATEGORY_QUERY,
                new Object[][]{{JDBCType.INTEGER, category.getId()}},
                new ResultSetHandler<List<Book>>() {
                    @Override
                    public List<Book> handle(ResultSet rs) throws SQLException {
                        List<Book> books = new ArrayList<>();
                        while (rs.next()) {
                            Author author = new Author(rs.getInt("a.id"),
                                    rs.getString("a.name"),
                                    rs.getString("a.about"));
                            books.add(new Book(rs.getInt("b.id"),
                                    rs.getString("b.name"),
                                    rs.getString("b.about"),
                                    author,
                                    rs.getInt("b.wh_amount"),
                                    rs.getInt("cat_id"),
                                    rs.getDouble("b.price")));
                        }
                        return books;
                    }
                }
        );
    }

    @Override
    public List<Book> getBooksByNameAndAuthor(String bookName, Author author) {
        return (List<Book>) TishinaDataSource.executePreparedStatement(
                GET_BOOKS_BY_NAME_AND_AUTHOR_QUERY,
                new Object[][]{{JDBCType.VARCHAR, bookName},
                         {JDBCType.VARCHAR, author.getName()}},
                new ResultSetHandler<List<Book>>() {
                    @Override
                    public List<Book> handle(ResultSet rs) throws SQLException {
                        List<Book> books = new ArrayList<>();
                        while (rs.next()) {
                            Integer bookId = rs.getInt("id");
                            books.add(getById(bookId));
                        }
                        return books;
                    }
                }
        );
    }

    @Override
    public Book getBookByNameAndAuthor(String name, Author author) {
        Collection<Book> books = getBooksByNameAndAuthor(name, author);
        if (books != null && !books.isEmpty()) {
            return books.iterator().next();
        } else {
            return null;
        }
    }

    @Override
    public void updateBook(Book book) {
        int updatedBookCount = TishinaDataSource.executeDMLStatement(
                UPDATE_BOOK_TABLE_QUERY,
                new Object[][] {{JDBCType.VARCHAR, book.getName()},
                        {JDBCType.INTEGER, book.getAuthor().getId()},
                        {JDBCType.VARCHAR, book.getDescription()},
                        {JDBCType.INTEGER, book.getWhAmount()-book.getOrderedAmount()},
                        {JDBCType.INTEGER, book.getCategory().getId()},
                        {JDBCType.INTEGER, book.getId()}}
        );
    }

    @Override
    public Book createBook(Book book) {
        Integer bookId = TishinaDataSource.executeCreateStatementAndGetAutoincrementedId(
                INSERT_INTO_BOOK_TABLE_QUERY,
                new Object[][]{{JDBCType.VARCHAR, book.getName()}, {JDBCType.INTEGER, book.getAuthor().getId()}});
        Objects.requireNonNull(bookId,
                "Unable to insert into table 'Book'. Probably id was not autogenerated in database");
        book.setId(bookId);
        return book;
    }

}
