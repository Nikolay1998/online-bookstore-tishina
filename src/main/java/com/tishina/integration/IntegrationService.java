package com.tishina.integration;

import com.tishina.dao.AuthorDAO;
import com.tishina.dao.BookDAO;
import com.tishina.dao.DAOFactoryHolder;
import com.tishina.model.Author;
import com.tishina.model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IntegrationService {

    public ImportReport updateBookCatalog(File inputFile) {
        ImportReport report = new ImportReport();

        //parse input file
        List<AuthorBookRow> rows = parseInputFile(inputFile);

        //is there author with name from file? If yes, skip; If no, create in database
        AuthorDAO authorDAO = DAOFactoryHolder.getDAOFactory().getAuthorDAO();
        Author author;
        for (AuthorBookRow row : rows) {
            author = authorDAO.getAuthorByName(row.authorName, true, false);
            if (author == null) {
                author = authorDAO.createAuthor(new Author(null, row.authorName, null));
                report.addAuthorToCreatedAuthors(author);
            } else {
                report.addAuthorToExistedAuthors(author);
            }
        }

        //is there book with name and author from file? If yes, skip; If no, create in database
        BookDAO bookDAO = DAOFactoryHolder.getDAOFactory().getBookDAO();
        Book book;
        for (AuthorBookRow row : rows) {
            author = authorDAO.getAuthorByName(row.authorName, true, false);
            book = bookDAO.getBookByNameAndAuthor(row.bookName, author);
            if (book == null) {
                book = new Book(row.bookName, author);
                bookDAO.createBook(book);
                report.addBookToCreatedBooks(book);
            } else {
                report.addBookToExistedBooks(book);
            }
        }

        return report;
    }

    public List<AuthorBookRow> parseInputFile(File file) {
        List<AuthorBookRow> rows = new ArrayList<>();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    String[] parsed = s.split("@#");
                    if (parsed.length == 3) {
                        rows.add(new AuthorBookRow(parsed));
                    }
                }
            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        return rows;
    }


    public static class AuthorBookRow {
        String bookName;
        String bookPrice;
        String authorName;

        public AuthorBookRow(String bookName, String bookPrice, String authorName) {
            this.bookName = bookName;
            this.bookPrice = bookPrice;
            this.authorName = authorName;
        }

        public AuthorBookRow(String[] strings) {
            this.bookName = strings[0].trim();
            this.bookPrice = strings[1].trim();
            this.authorName = strings[2].trim();
        }


        @Override
        public String toString() {
            return "AuthorBookRow{" +
                    "bookName='" + bookName + '\'' +
                    ", bookPrice='" + bookPrice + '\'' +
                    ", authorName='" + authorName + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AuthorBookRow that = (AuthorBookRow) o;
            return Objects.equals(bookName, that.bookName) &&
                    Objects.equals(bookPrice, that.bookPrice) &&
                    Objects.equals(authorName, that.authorName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(bookName, bookPrice, authorName);
        }
    }
}
