import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.yoonchan.entities.Book;
import org.yoonchan.entities.Item;
import org.yoonchan.util.ItemUtil;

public class ItemUtilTest {
    @Test
    @DisplayName("testIsIsbnValid1: null -> NullPointerException")
    void testIsIsbnValid1() {
        Assertions.assertThrows(NullPointerException.class, () ->
                ItemUtil.isIsbnValid(null));
    }

    @Test
    @DisplayName("testIsIsbnValid2: \"\" -> false")
    void testIsIsbnValid2() {
        String input = "";
        boolean expected = false;
        boolean actual = ItemUtil.isIsbnValid(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("testIsIsbnValid3: \"978-0-306-40615-7\" -> true")
    void testIsIsbnValid3() {
        String input = "978-0-306-40615-7"; // Wikipedia example
        boolean expected = true;
        boolean actual = ItemUtil.isIsbnValid(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("testIsIsbnValid4: \"978-0553418026\" -> true")
    void testIsIsbnValid4() {
        String input = "978-0553418026"; // The Martian
        boolean expected = true;
        boolean actual = ItemUtil.isIsbnValid(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("testIsIsbnValid5: \"978-0451524935\" -> true")
    void testIsIsbnValid5() {
        String input = "978-0451524935"; // 1984
        boolean expected = true;
        boolean actual = ItemUtil.isIsbnValid(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("testIsIsbnValid5: \"978-0451524934\" -> false")
    void testIsIsbnValid6() {
        String input = "978-0451524934"; // Fake - one off from 1984
        boolean expected = false;
        boolean actual = ItemUtil.isIsbnValid(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("testItemDataToCSVString1: new Book(\"Rainbow Six\", \"9780425170342\", \"Tom Clancy\", \"Action\")" +
            "->" +
            "B1,Rainbow Six,AVAILABLE,9780425170342,Tom Clancy,Action")
    void testSplitUserCSVData1() {
        Item input = new Book("BX", "Rainbow Six", Item.Status.AVAILABLE, "9780425170342", "Tom Clancy", "Action");
        String expected = "BX,Rainbow Six,AVAILABLE,9780425170342,Tom Clancy,Action\n";
        String actual = ItemUtil.itemDataToCSVString(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("testItemDataToCSVString2: null -> NullPointerException")
    void testSplitUserCSVData2() {
        Item input = null;
        Assertions.assertThrows(NullPointerException.class, () ->
                ItemUtil.itemDataToCSVString(input));
    }
}
