package com.tishina.dao.impl;

import com.tishina.dao.BookDAO;
import com.tishina.dao.TishinaDataSource;
import com.tishina.dao.util.ResultSetHandler;
import com.tishina.model.Author;
import com.tishina.model.Book;

import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMysqlDAO implements BookDAO{
    private static final String getBookByIdQuery = "select a.id, a.name,  a.about, b.id, b.name, b.about, " +
            "b.wh_amount from Book b left join Author a on b.author_id = a.id where b.id = ?";
    private static final String INSERT_INTO_BOOK_TABLE_QUERY =
            "update book set name = ?, author_id = ?, about = ?, wh_amount = ? where id = ?";

    @Override
    public Book getById(Integer id) {
        System.out.println("BookMysqlDAO.getById START for id = "+id);
        return (Book) TishinaDataSource.executePreparedStatement(
                getBookByIdQuery,
                new Object[][]{{JDBCType.INTEGER, id}},
                new ResultSetHandler<Book>(){
                    public Book handle(ResultSet rs) throws SQLException {
                        if (!rs.next()) {
                            throw new RuntimeException("Can't find book with id = "+id);
                        }
                        Author author = new Author(rs.getInt("a.id"),
                                rs.getString("a.name"),
                                rs.getString("a.about"));
                        Book book = new Book(rs.getInt("b.id"),
                                rs.getString("b.name"),
                                rs.getString("b.about"),
                                author,
                                rs.getInt("b.wh_amount"));
                        return book;
                    }
                }
        );
    }

    @Override
    public void updateBook(Book book) {
        int updatedBookCount = TishinaDataSource.executeDMLStatement(
                INSERT_INTO_BOOK_TABLE_QUERY,
                new Object[][] {{JDBCType.VARCHAR, book.getName()},
                        {JDBCType.INTEGER, book.getAuthor().getId()},
                        {JDBCType.VARCHAR, book.getDescription()},
                        {JDBCType.INTEGER, book.getWhAmount()-book.getOrderedAmount()},
                        {JDBCType.INTEGER, book.getId()}}
        );
        System.out.println("BookMysqlDAO.updateBook updated books count: "+updatedBookCount);
    }


}
