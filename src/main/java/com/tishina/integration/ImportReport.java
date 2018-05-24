package com.tishina.integration;

import com.tishina.model.Author;
import com.tishina.model.Book;

import java.util.HashSet;
import java.util.Set;

public class ImportReport {

    private Set<Book> createdBooks = new HashSet<>();
    private Set<Book> existedBooks = new HashSet<>();
    private Set<Author> createdAuthors = new HashSet<>();
    private Set<Author> existedAuthors = new HashSet<>();

    public void addBookToCreatedBooks(Book book) {
        createdBooks.add(book);
    }

    public void addBookToExistedBooks(Book book) {
        if (!createdBooks.contains(book)) {
            existedBooks.add(book);
        }
    }

    public void addAuthorToCreatedAuthors(Author author) {
        createdAuthors.add(author);
    }

    public void addAuthorToExistedAuthors(Author author) {
        if(!existedAuthors.contains(author)) {
            existedAuthors.add(author);
        }
    }

    public Set<Book> getCreatedBooks() {
        return createdBooks;
    }

    public Set<Book> getExistedBooks() {
        return existedBooks;
    }

    public Set<Author> getCreatedAuthors() {
        return createdAuthors;
    }

    public Set<Author> getExistedAuthors() {
        return existedAuthors;
    }

    @Override
    public String toString() {
        return "ImportReport{" +
                "createdBooks=" + createdBooks +
                ", existedBooks=" + existedBooks +
                ", createdAuthors=" + createdAuthors +
                ", existedAuthors=" + existedAuthors +
                '}';
    }
}
