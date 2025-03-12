package ex4;

import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class DirectoryBrowserTest {
    @TempDir
    Path tempDir;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    /**
     * Tests the DirectoryBrowser by creating a temporary directory structure:
     *
     * tempDir/
     *    file1.txt
     *    subdir1/
     *         file2.txt
     *         file3.txt
     *    subdir2/ (empty)
     *
     * Expected file count: 3 (file1.txt, file2.txt, file3.txt)
     */
    @org.junit.jupiter.api.Test
    public void testBrowseDirectory() throws IOException {
        // Create file1.txt in tempDir
        Path file1 = tempDir.resolve("file1.txt");
        Files.write(file1, "Hello".getBytes());

        // Create subdir1 and files within it
        Path subdir1 = tempDir.resolve("subdir1");
        try {
            Files.createDirectory(subdir1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Path file2 = subdir1.resolve("file2.txt");
        Path file3 = subdir1.resolve("file3.txt");
        Files.write(file2, "Ensicaen".getBytes());
        Files.write(file3, "Test".getBytes());

        // Create an empty subdir2
        Path subdir2 = tempDir.resolve("subdir2");
        Files.createDirectory(subdir2);

        // Instantiate DirectoryBrowser with the temporary directory
        DirectoryBrowser browser = new DirectoryBrowser(tempDir.toFile());
        browser.browse();

        // Expected file count is 3 (only files are counted)
        assertEquals(3, browser.getFileCount(), "File count should be 3.");

        String fileList = browser.getFileList();
        // Check that the file list contains the expected entries.
        assertTrue(fileList.contains("[f] file1.txt"), "file1.txt should be listed as a file.");
        assertTrue(fileList.contains("[d] subdir1"), "subdir1 should be listed as a directory.");
        assertTrue(fileList.contains("[f] file2.txt"), "file2.txt should be listed as a file.");
        assertTrue(fileList.contains("[f] file3.txt"), "file3.txt should be listed as a file.");
        assertTrue(fileList.contains("[d] subdir2"), "subdir2 should be listed as a directory.");
    }

}