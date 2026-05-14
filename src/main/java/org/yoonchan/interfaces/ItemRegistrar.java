package org.yoonchan.interfaces;

import org.yoonchan.Library;
import org.yoonchan.entities.Item;

public interface ItemRegistrar {
    /**
     * Adds an Item to the Library's itemCatalogue.
     * @param item The Item to be added.
     * @throws NullPointerException If the input Item is null.
     */
    default void registerItemToLibrary(Item item) throws NullPointerException {
        if (item == null) throw new NullPointerException();

        Library.itemCatalogue.add(item);
    }
}
