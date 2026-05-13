import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.yoonchan.util.ItemUtil;

public class ItemUtilTest {
    @Test
    @DisplayName("testIsISBNValid1: null -> NullPointerException")
    void testIsISBNValid1() {
        Assertions.assertThrows(NullPointerException.class, () ->
                ItemUtil.isISBNValid(null));
    }

    @Test
    @DisplayName("testIsISBNValid2: \"\" -> false")
    void testIsISBNValid2() {
        String input = "";
        boolean expected = false;
        boolean actual = ItemUtil.isISBNValid(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("testIsISBNValid3: \"978-0-306-40615-7\" -> true")
    void testIsISBNValid3() {
        String input = "978-0-306-40615-7"; // Wikipedia example
        boolean expected = true;
        boolean actual = ItemUtil.isISBNValid(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("testIsISBNValid4: \"978-0553418026\" -> true")
    void testIsISBNValid4() {
        String input = "978-0553418026"; // The Martian
        boolean expected = true;
        boolean actual = ItemUtil.isISBNValid(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("testIsISBNValid5: \"978-0451524935\" -> true")
    void testIsISBNValid5() {
        String input = "978-0451524935"; // 1984
        boolean expected = true;
        boolean actual = ItemUtil.isISBNValid(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("testIsISBNValid5: \"978-0451524934\" -> false")
    void testIsISBNValid6() {
        String input = "978-0451524934"; // Fake - one off from 1984
        boolean expected = false;
        boolean actual = ItemUtil.isISBNValid(input);

        Assertions.assertEquals(expected, actual);
    }
}
