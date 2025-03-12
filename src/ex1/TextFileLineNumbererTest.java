package ex1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class TextFileLineNumbererTest {
    @TempDir
    Path tempDir;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    /**
     * Tests that the numberLines method correctly numbers each line,
     * including an empty line.
     */
    @Test
    public void testNumberLinesSimple() throws IOException {
        // Create a temporary file with multiple lines (including an empty line)
        Path tempFile = tempDir.resolve("test.txt");
        String content = "Hello Ensicaen\n" +
                "\n" +
                "Remember that ODL rules is important!";
        Files.write(tempFile, content.getBytes(StandardCharsets.UTF_8));

        TextFileLineNumberer numberer = new TextFileLineNumberer(tempFile.toString(), 60);
        String result = numberer.numberLines();

        // Expected output:
        // "   1: Hello Ensicaen\n"
        // "   2: \n"
        // "   3: Remember that ODL rules is important!\n"
        String expected = String.format("%4d: %s\n", 1, "Hello Ensicaen") +
                String.format("%4d: %s\n", 2, "") +
                String.format("%4d: %s\n", 3, "Remember that ODL rules is important!");
        assertEquals(expected, result);
    }

    /**
     * Tests that a long line is split into multiple numbered segments.
     */
    @Test
    public void testNumberLinesSplitLongLine() throws IOException {
        // Create a temporary file with a single long line.
        Path tempFile = tempDir.resolve("testLong.txt");
        // A 25-character line.
        String longLine = "ABCDEFGHIJKLMNOPQRSTUVWXY";
        Files.write(tempFile, longLine.getBytes(StandardCharsets.UTF_8));

        int maxLength = 10;
        TextFileLineNumberer numberer = new TextFileLineNumberer(tempFile.toString(), maxLength);
        String result = numberer.numberLines();

        // The expected splitting is:
        // Line 1: characters 0-9: "ABCDEFGHIJ"
        // Line 2: characters 10-19: "KLMNOPQRST"
        // Line 3: characters 20-24: "UVWXY"
        String expected = String.format("%4d: %s\n", 1, "ABCDEFGHIJ") +
                String.format("%4d: %s\n", 2, "KLMNOPQRST") +
                String.format("%4d: %s\n", 3, "UVWXY");
        assertEquals(expected, result);
    }

}