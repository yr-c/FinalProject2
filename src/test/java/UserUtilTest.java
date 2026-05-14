import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.yoonchan.util.UserUtil;

public class UserUtilTest {
    @Test
    @DisplayName("testSplitUserCSVData1: A00001,Mike,[B0000001,Martian,BORROWED,978-0553418026," +
            "Andy Weir,Sci-Fi;B0000002,Harry Potter,BORROWED,978-0747532699,JK Rowling,Fantasy]" +
            "->" +
            "A00001,Mike,[B0000001,Martian,BORROWED,978-0553418026,Andy Weir,Sci-Fi;B0000002,Harry Potter,BORROWED,978-0747532699,JK Rowling,Fantasy]")
    void testSplitUserCSVData1() {
        String input = "A00001,Mike,[B0000001,Martian,BORROWED,978-0553418026," +
                "Andy Weir,Sci-Fi;B0000002,Harry Potter,BORROWED,978-0747532699,JK Rowling,Fantasy]";
        String[] expected = new String[]{
                "A00001", "Mike", "[B0000001,Martian,BORROWED,978-0553418026," +
                "Andy Weir,Sci-Fi;B0000002,Harry Potter,BORROWED,978-0747532699,JK Rowling,Fantasy]"};
        String[] actual = UserUtil.splitUserCSVData(input);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("testSplitUserCSVData2: ")
    void testSplitUserCSVData2() {
        String input = "a,b,[;;]";
        String[] expected = new String[]{"a", "b", "[;;]"};
        String[] actual = UserUtil.splitUserCSVData(input);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("testSplitUserCSVData3: null -> NullPointerException")
    void testSplitUserCSVData3() {
        String input = null;

        Assertions.assertThrows(NullPointerException.class, () ->
                UserUtil.splitUserCSVData(input));
    }

    @Test
    @DisplayName("testSplitUserCSVData4: \"\" -> IllegalArgumentException")
    void testSplitUserCSVData4() {
        String input = "";

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                UserUtil.splitUserCSVData(input));
    }
}
