package com.example.bookshopsystem.domain.entities;

import com.example.bookshopsystem.domain.enums.AgeRestriction;
import com.example.bookshopsystem.domain.enums.Type;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( length = 50,nullable = false)
    private String title;
    @Column(length = 1000)
    private String description;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private int copies;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Enumerated(EnumType.STRING)
    private AgeRestriction restriction;
    @ManyToMany
    private Set<Category> categories;
    @ManyToOne
    private Author author;

}
