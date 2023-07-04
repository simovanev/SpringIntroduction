package com.example.bookshopsystem.service;

import com.example.bookshopsystem.domain.entities.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {
    boolean isDataServiced();
    void seedAuthors(List<Author> authors);

   public Author getRandomAuthor();

    List< Author> getAllAuthorsWithBooksBeforeYear(LocalDate of);
}
