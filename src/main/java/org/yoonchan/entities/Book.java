package org.yoonchan.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@Getter
@ToString
public class Book extends Item {
    private static int nextId = 1;
    @Setter private String ISBN;
    @Setter private String author;
    @Setter private String genre;

    // For CSV functionality
    public Book(String id, String title, Status status, String ISBN, String author, String genre) {
        super(id, title, status);
        this.ISBN = ISBN;
        this.author = author;
        this.genre = genre;
    }

    public Book(String title, String ISBN, String author, String genre) {
        super(String.format("B%04d", nextId++), title);
        this.ISBN = ISBN;
        this.author = author;
        this.genre = genre;
    }
}
