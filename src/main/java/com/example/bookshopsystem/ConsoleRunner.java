package com.example.bookshopsystem;

import com.example.bookshopsystem.service.AuthorService;
import com.example.bookshopsystem.service.BookService;
import com.example.bookshopsystem.service.CategoryService;
import com.example.bookshopsystem.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private AuthorService authorService;
    private BookService bookService;
    private SeedService seedService;

    @Autowired
    public ConsoleRunner(AuthorService authorService, BookService bookService, SeedService seedService) {

        this.authorService = authorService;
        this.bookService = bookService;
        this.seedService = seedService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedService.seedAllData();
        this.authorService.getAllAuthorsWithBooksBeforeYear(LocalDate.of(1990,1,1));

    }
}
