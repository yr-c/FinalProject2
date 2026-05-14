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
     * Compares Users ascendingly by id, then by title, then by Status. The Status ordering follows the natural
     * ordering of the Status enum, which goes: AVAILABLE -> BORROWED -> LOST.
     */
    public static class ItemIdComparator implements Comparator<Item> {
        @Override
        public int compare(Item o1, Item o2) {
            Comparator<Status> naturalOrderComparator = Comparator.naturalOrder();

            int idDifference = Integer.compare(Integer.parseInt(o1.id), Integer.parseInt(o2.id));
            int titleDifference = o1.title.compareTo(o2.title);
            int statusDifference = naturalOrderComparator.compare(o1.status, o2.status);

            if (idDifference == 0) {
                return titleDifference == 0 ? statusDifference : titleDifference;
            }
            return idDifference;
        }
    }

    /**
     * Compares Users ascendingly by title, then by id, then by Status. The Status ordering follows the natural
     * ordering of the Status enum, which goes: AVAILABLE -> BORROWED -> LOST.
     */
    public static class ItemTitleComparator implements Comparator<Item> {
        @Override
        public int compare(Item o1, Item o2) {
            Comparator<Status> naturalOrderComparator = Comparator.naturalOrder();

            int idDifference = Integer.compare(Integer.parseInt(o1.id), Integer.parseInt(o2.id));
            int titleDifference = o1.title.compareTo(o2.title);
            int statusDifference = naturalOrderComparator.compare(o1.status, o2.status);

            if (titleDifference == 0) {
                return idDifference == 0 ? statusDifference : idDifference;
            }
            return titleDifference;
        }
    }
}
