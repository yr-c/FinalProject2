package org.yoonchan.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@Getter
@ToString
@Setter
public class Magazine extends Item {
    @Setter private static int nextId = 1;
    private String director;
    private int durationMins;

    // For CSV functionality
    public Magazine(String id, String title, Status status, String director, int durationMins) {
        super(id, title, status);
        this.director = director;
        this.durationMins = durationMins;
    }

    public Magazine(String title) {
        super(String.format("M%07d", nextId++), title);
    }
}
