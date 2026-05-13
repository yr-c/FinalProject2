package org.yoonchan.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@Getter
@ToString
public class Magazine extends Item {
    private static int nextId = 1;
    @Setter private String director;
    @Setter private int durationMins;

    // For CSV functionality
    public Magazine(String id, String title, Status status) {
        super(id, title, status);
    }

    public Magazine(String title) {
        super(String.format("M%04d", nextId++), title);
    }
}
