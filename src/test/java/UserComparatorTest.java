import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.yoonchan.entities.Book;
import org.yoonchan.roles.*;

import java.util.ArrayList;
import java.util.List;

public class UserComparatorTest {
    @Test
    @DisplayName("""
            testUserIdComparator1:\s
            user1 = Student("01", "John", [])\
            user2 = Teacher("02", "Adam", []);\
            user3 = Admin("03", "Sarah", [])\
            ->\
            [user1, user2, user3]""")
    void testUserIdComparator1() {
        List<User> users = new ArrayList<>();
        User user1 = new Student("S01", "John", new ArrayList<>());
        User user2 = new Teacher("T02", "Adam", new ArrayList<>());
        User user3 = new Admin("A03", "Sarah", new ArrayList<>());
        users.add(user2);
        users.add(user3);
        users.add(user1);
        users.sort(new User.UserIdComparator());

        Object[] actual = users.toArray();
        Object[] expected = new Object[]{user1, user2, user3};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("""
            testUserNameComparator1:\s
            user1 = Student("S01", "John", [])\
            user2 = Student("S02", "Adam", []);\
            user3 = Student("S03", "Sarah", [])\
            ->\
            [user2, user1, user3]""")
    void testUserNameComparator1() {
        List<User> users = new ArrayList<>();
        User user1 = new Student("01", "John", new ArrayList<>());
        User user2 = new Student("02", "Adam", new ArrayList<>());
        User user3 = new Student("03", "Sarah", new ArrayList<>());
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.sort(new User.UserNameComparator());

        Object[] actual = users.toArray();
        Object[] expected = new Object[]{user2, user1, user3};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("""
            testUserNameComparator2:\s
            user1 = Student("S01", "Adam", [])\
            user2 = Teacher("T02", "Adam", []);\
            user3 = Admin("A03", "Sarah", [])\
            ->\
            [user2, user1, user3]""")
    void testUserNameComparator2() {
        List<User> users = new ArrayList<>();
        User user1 = new Student("01", "Adam",
                    List.of(new Book("Martian", "978-0553418026", "Andy Weir", "Sci-Fi")));
        User user2 = new Teacher("02", "Adam", new ArrayList<>());
        User user3 = new Admin("03", "Sarah", new ArrayList<>());
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.sort(new User.UserNameComparator());

        Object[] actual = users.toArray();
        Object[] expected = new Object[]{user2, user1, user3};
        Assertions.assertArrayEquals(expected, actual);
    }
}
