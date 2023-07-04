package com.example.bookshopsystem.service;

import com.example.bookshopsystem.domain.entities.Author;
import com.example.bookshopsystem.domain.entities.Book;
import com.example.bookshopsystem.domain.enums.AgeRestriction;
import com.example.bookshopsystem.domain.enums.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.bookshopsystem.domain.constans.FilePath.*;

@Service
public class SeedServiceImpl implements SeedService {
    private AuthorService authorService;
    private BookService bookService;
    private CategoryService categoryService;

    @Autowired
    public SeedServiceImpl(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorService.isDataServiced())return;

        this.authorService.seedAuthors(
                Files.readAllLines(Path.of(RESOURCE_URL+AUTHORS_FILE_NAME))
                        .stream()
                        .map(e-> Author.builder()
                                .firstName(e.split(" ")[0])
                                .lastName(e.split(" ")[1])
                                .build())
                        .toList());





    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookService.isDataSeeded()) return;

        final List<Book> books = Files.readAllLines(Path.of(RESOURCE_URL + BOOKS_FILE_NAME))
                .stream()
                .filter(s -> !s.isBlank())
                .map(row -> {
                    String[] args = row.split("\\s+");
                    String title = Arrays.stream(args)
                            .skip(5)
                            .collect(Collectors.joining());

                    return Book.builder()
                            .author(this.authorService.getRandomAuthor())
                            .categories(this.categoryService.getRandomCategories())
                            .title(title)
                            .type(Type.values()[Integer.parseInt(args[0])])
                            .restriction(AgeRestriction.values()[Integer.parseInt(args[4])])
                            .releaseDate(LocalDate.parse(args[1], DateTimeFormatter.ofPattern("d/M/yyyy")))
                            .copies(Integer.parseInt(args[2]))
                            .price(Double.parseDouble(args[3]))
                            .build();
                }).toList();

        this.bookService.seedBooks(books);

    }

    @Override
    public void seedCategories() {

    }
}
