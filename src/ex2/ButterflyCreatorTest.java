package ex2;

import ex3.Student;

import static org.junit.jupiter.api.Assertions.*;

class ButterflyCreatorTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    public void testGenerateButterfly() {
        // For n = 7, the butterfly should be created using 2 x 7 - 1 = 13 lines.
        ButterflyCreator creator = new ButterflyCreator(7);
        String result = creator.generate();
        String[] lines = result.split("\n");
        assertEquals(13, lines.length, "For n=7, we should count 13 lines.");

        String middleLine = lines[6];
        String expectedStars = "▓".repeat(7);
        assertEquals(expectedStars + expectedStars, middleLine, "Median line is incorrect.");
    }

    @org.junit.jupiter.api.Test
    public void testInvalidSize() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new ButterflyCreator(6); // 6 is even so should throw an exception
        });
        String expectedMessage = "The size should be an even positive integer";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

}