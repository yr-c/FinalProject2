package org.yoonchan.entities;

import lombok.*;

import java.util.Comparator;

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
        AVAILABLE, BORROWED, LOST
    }

    /**
     * Compares Users ascendingly by id, then by title, then by Status.
     * The Status ordering follows the natural ordering of the Status enum, which goes: AVAILABLE -> BORROWED -> LOST.
     */
    public static class ItemIdComparator implements Comparator<Item> {
        @Override
        public int compare(Item o1, Item o2) {
            int id1 = Integer.parseInt(o1.id.substring(1));
            int id2 = Integer.parseInt(o2.id.substring(1));
            int idDifference = Integer.compare(id1, id2);

            int titleDifference = o1.title.compareTo(o2.title);

            Comparator<Status> naturalOrderComparator = Comparator.naturalOrder();
            int statusDifference = naturalOrderComparator.compare(o1.status, o2.status);

            if (idDifference == 0) {
                return titleDifference == 0 ? statusDifference : titleDifference;
            }
            return idDifference;
        }
    }

    /**
     * Compares Users ascendingly by title, then by Status, then by id. The Status ordering follows the natural
     * ordering of the Status enum, which goes: AVAILABLE -> BORROWED -> LOST.
     */
    public static class ItemTitleComparator implements Comparator<Item> {
        @Override
        public int compare(Item o1, Item o2) {
            int id1 = Integer.parseInt(o1.id.substring(1));
            int id2 = Integer.parseInt(o2.id.substring(1));
            int idDifference = Integer.compare(id1, id2);

            int titleDifference = o1.title.compareTo(o2.title);

            Comparator<Status> naturalOrderComparator = Comparator.naturalOrder();
            int statusDifference = naturalOrderComparator.compare(o1.status, o2.status);

            if (titleDifference == 0) {
                return statusDifference == 0 ? idDifference : statusDifference;
            }
            return titleDifference;
        }
    }
}
