import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.yoonchan.entities.*;

import java.util.ArrayList;
import java.util.List;

public class ItemComparatorTest {
    @Test
    @DisplayName("""
    testItemIdComparator1:\s
    item1 = Book("B01", "Martian", Item.Status.AVAILABLE,"978-0553418026", "Andy Weir", "Sci-Fi")\
    item2 = Book("B02", "Martian", Item.Status.AVAILABLE,"978-0553418026", "Andy Weir", "Sci-Fi")\
    item3 = DVD("D01", "Martian Movie", Item.Status.LOST,"Ridley Scott", 150)\
    item4 = Magazine("M01", "Martian", Item.Status.BORROWED,"Vogue", 1)\
    ->\
    [item1, item4, item3, item2]""")
    void testItemIdComparator1() {
        List<Item> items = new ArrayList<>();
        Item item1 = new Book("B01", "Martian", Item.Status.AVAILABLE,"978-0553418026", "Andy Weir", "Sci-Fi");
        Item item2 = new Book("B02", "Martian", Item.Status.AVAILABLE,"978-0553418026", "Andy Weir", "Sci-Fi");
        Item item3 = new DVD("D01", "Martian Movie", Item.Status.LOST,"Ridley Scott", 150);
        Item item4 = new Magazine("M01", "Martian", Item.Status.BORROWED,"Vogue", 1);

        items.add(item3);
        items.add(item4);
        items.add(item2);
        items.add(item1);
        items.sort(new Item.ItemIdComparator());

        Object[] actual = items.toArray();
        Object[] expected = new Object[]{item1, item4, item3, item2};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("""
    testItemTitleComparator1:\s
    item1 = Book("B01", "Martian", Item.Status.LOST,"978-0553418026", "Andy Weir", "Sci-Fi")\
    item2 = Book("B02", "Martian", Item.Status.AVAILABLE,"978-0553418026", "Andy Weir", "Sci-Fi")\
    item3 = DVD("D01", "Martian Movie", Item.Status.LOST,"Ridley Scott", 150)\
    item4 = Magazine("M01", "Martian", Item.Status.BORROWED,"Vogue", 1)\
    ->\
    [item1, item4, item3, item2]""")
    void testItemTitleComparator1() {
        List<Item> items = new ArrayList<>();
        Item item1 = new Book("B01", "Martian", Item.Status.LOST,"978-0553418026", "Andy Weir", "Sci-Fi");
        Item item2 = new Book("B02", "Martian", Item.Status.AVAILABLE,"978-0553418026", "Andy Weir", "Sci-Fi");
        Item item3 = new DVD("D01", "Martian Movie", Item.Status.LOST,"Ridley Scott", 150);
        Item item4 = new Magazine("M01", "Martian", Item.Status.BORROWED,"Vogue", 1);

        items.add(item3);
        items.add(item1);
        items.add(item2);
        items.add(item4);
        items.sort(new Item.ItemTitleComparator());

        Object[] actual = items.toArray();
        Object[] expected = new Object[]{item2, item4, item1, item3};
        Assertions.assertArrayEquals(expected, actual);
    }
}
