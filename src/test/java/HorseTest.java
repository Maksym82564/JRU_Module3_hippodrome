import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.mockStatic;

public class HorseTest {
    @Test
    void throwExceptionIfNullStringPassed() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null,1.0));
    }

    @Test
    void checkExceptionMessageIfNullStringPassed() {
        String expectedMessage = "Name cannot be null.";
        try {
            new Horse(null,1.0);
        }
        catch (IllegalArgumentException e) {
            Assertions.assertEquals(expectedMessage, e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\t"})
    void throwExceptionIfWhitespaceStringPassed(String name) {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(name,1.0));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\t"})
    void checkExceptionMessageIfWhitespaceStringPassed(String name) {
        String expectedMessage = "Name cannot be blank.";
        try {
            new Horse(name,1.0);
        }
        catch (IllegalArgumentException e) {
            Assertions.assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    void throwExceptionIfSecondArgumentNegative() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("name",-1.0));
    }

    @Test
    void checkExceptionMessageIfSecondArgumentNegative () {
        String expectedMessage = "Speed cannot be negative.";
        try {
            new Horse("name", -1.0);
        }
        catch (IllegalArgumentException e) {
            Assertions.assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    void throwExceptionIfThirdArgumentNegative() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("name",1.0,-1.0));
    }

    @Test
    void checkExceptionMessageIfThirdArgumentNegative() {
        String expectedMessage = "Distance cannot be negative.";
        try {
            new Horse("name", 1.0, -1.0);
        }
        catch (IllegalArgumentException e) {
            Assertions.assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    void getName() {
        String expectedName = "name";
        Horse horse = new Horse(expectedName, 1.0);
        Assertions.assertEquals(expectedName, horse.getName());
    }

    @Test
    void getSpeed() {
        double expectedSpeed = 1.0;
        Horse horse = new Horse("name", expectedSpeed);
        Assertions.assertEquals(expectedSpeed, horse.getSpeed());
    }

    @Test
    void getDistance() {
        double expectedDistance = 1.0;
        Horse horse = new Horse("name", 1.0, expectedDistance);
        Assertions.assertEquals(expectedDistance, horse.getDistance());
    }

    @Test
    void getDistanceIfCreatedWith2Parameters() {
        double expectedDistance = 0.0;
        Horse horse = new Horse("name", 1.0);
        Assertions.assertEquals(expectedDistance, horse.getDistance());
    }

    @Test
    void getRandomDoubleCallInsideMoveMethod() {
        try (MockedStatic<Horse> staticMock = mockStatic(Horse.class)) {
            new Horse("name", 1.0).move();
            staticMock.verify(() -> Horse.getRandomDouble(0.2,0.9));
        }
    }

    @Test
    void checkDistanceCalculatedInMove() {
        try (MockedStatic<Horse> staticMock = mockStatic(Horse.class)) {
            staticMock.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(0.5);
            Horse horse = new Horse("name", 2.0, 1.0);
            double expectedDistance = horse.getDistance() + horse.getSpeed() * Horse.getRandomDouble(0.2,0.9);
            horse.move();
            Assertions.assertEquals(expectedDistance, horse.getDistance());
        }
    }

}
