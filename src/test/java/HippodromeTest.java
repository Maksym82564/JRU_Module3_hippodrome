import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HippodromeTest {
    @Test
    void throwExceptionIfNullPassed() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null));
    }

    @Test
    void checkExceptionMessageIfNullStringPassed() {
        String expectedMessage = "Horses cannot be null.";
        try {
            new Hippodrome(null);
        }
        catch (IllegalArgumentException e) {
            Assertions.assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    void throwingExceptionIfBlankListPassed() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(Collections.emptyList()));
    }

    @Test
    void checkExceptionMessageIfBlankListPassed() {
        String expectedMessage = "Horses cannot be empty.";
        try {
            new Hippodrome(Collections.emptyList());
        }
        catch (IllegalArgumentException e) {
            Assertions.assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    void getHorses() {
        List<Horse> expectedHorses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            expectedHorses.add(new Horse("Horse " + i, i));
        }
        List<Horse> horses = new ArrayList<>(expectedHorses);
        Hippodrome hippodrome = new Hippodrome(horses);
        Assertions.assertEquals(expectedHorses, hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> mockHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mockHorses.add(Mockito.mock(Horse.class));
        }
        new Hippodrome(mockHorses).move();
        for (int i = 0; i < 50; i++) {
            Mockito.verify(mockHorses.get(i)).move();
        }
    }

    @Test
    void getWinner() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse " + i, i, i * 10));
        }
        Horse expectedWinner = horses.get(horses.size() - 1);
        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = hippodrome.getWinner();
        Assertions.assertEquals(expectedWinner, winner);
    }
}
