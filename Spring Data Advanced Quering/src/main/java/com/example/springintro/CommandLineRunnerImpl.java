package com.example.springintro;

import com.example.springintro.model.entity.Book;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        //printAllBooksAfterYear(2000);
//        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
        //   printAllAuthorsAndNumberOfTheirBooks();
        // printALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");


       // findBooksByRestriction();

       // goldenEditionBooksUnder5000Copies();
        // printTitleAndPriceNotInRange5To40();
        printBookTitleContainingString();

    }

    private void printBookTitleContainingString() {

        String input= new Scanner(System.in).nextLine();
        this.bookService.bookTitleContainingString(input).forEach(System.out::println);

    }

    private void printTitleAndPriceNotInRange5To40() {
        this.bookService.booksWithPriceNotInRange5And40().forEach(System.out::println);
    }

    private void goldenEditionBooksUnder5000Copies() {
        this.bookService.findAllGoldenUnder5000copies().forEach(System.out::println);
    }

    private void findBooksByRestriction() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        this.bookService.findAllByAgeRestriction(input);
    }

    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
