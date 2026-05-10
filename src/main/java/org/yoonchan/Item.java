package org.yoonchan;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Item {
    protected String id;
    protected String title;
    protected Status status;

    public enum Status {
        BORROWED, AVAILABLE, LOST
    }
}
