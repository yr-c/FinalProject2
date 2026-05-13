package org.yoonchan.entities;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public abstract class Item {
    protected String id;
    @Setter protected String title;
    @Setter protected Status status;

    public Item(String id, String title) {
        this.id = id;
        this.title = title;
        this.status = Status.AVAILABLE;
    }

    public enum Status {
        BORROWED, AVAILABLE, LOST
    }
}
