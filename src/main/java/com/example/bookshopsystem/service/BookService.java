package com.example.bookshopsystem.service;

import com.example.bookshopsystem.domain.entities.Author;
import com.example.bookshopsystem.domain.entities.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService {
    boolean isDataSeeded();
    void seedBooks(List<Book> books);


    List<Book> getBooksBeforeYear(LocalDate date);
}
