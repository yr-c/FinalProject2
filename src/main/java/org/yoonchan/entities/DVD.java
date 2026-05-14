package org.yoonchan.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString(callSuper = true)
@Setter
public class DVD extends Item {
    @Setter private static int nextId = 1;
    private String director;
    private int durationMins;

    // For CSV functionality
    public DVD(String id, String title, Status status, String director, int durationMins) {
        super(id, title, status);
        this.director = director;
        this.durationMins = durationMins;
    }

    public DVD(String title, String director, int durationMins) {
        super(String.format("D%07d", nextId++), title);
        this.director = director;
        this.durationMins = durationMins;
    }
}
