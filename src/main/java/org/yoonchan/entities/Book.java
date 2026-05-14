package org.yoonchan.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.yoonchan.util.ItemUtil;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString(callSuper = true)
@Setter
public class Book extends Item {
    @Setter private static int nextId = 1;
    private String isbn;
    private String author;
    private String genre;

    // For CSV functionality
    public Book(String id, String title, Status status, String isbn, String author, String genre) {
        super(id, title, status);
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
    }

    public Book(String title, String isbn, String author, String genre) {
        super(String.format("B%07d", nextId++), title);

        if (!ItemUtil.isIsbnValid(isbn)) throw new IllegalArgumentException("Invalid ISBN.");
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
    }
}
