package com.example.bookshopsystem.repositories;

import com.example.bookshopsystem.domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<List<Book>> findAllByReleaseDateBefore(LocalDate date);
    Optional<List<Book>> findAllByReleaseDateAfter(LocalDate date);
}
