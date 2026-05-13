package org.yoonchan.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@Getter
@ToString
public class DVD extends Item {
    private static int nextId = 1;
    @Setter private String publisher;
    @Setter private int issueNumber;

    // For CSV functionality
    public DVD(String id, String title, Status status, String publisher, int issueNumber) {
        super(id, title, status);
        this.publisher = publisher;
        this.issueNumber = issueNumber;
    }

    public DVD(String title) {
        super(String.format("D%04d", nextId++), title);
    }
}
