package ex3;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the simplified StudentManager class.
 */
public class StudentManagerTest {
    private StudentManager manager;

    @BeforeEach
    public void setUp() {
        manager = new StudentManager();
        // Remove the class file to start fresh.
        File classFile = new File("class.dat");
        if (classFile.exists()) {
            classFile.delete();
        }
    }

    @Test
    public void testAddAndSearchStudent() {
        Student s1 = new Student("Saigne", "Jean", 11.0);
        boolean added = manager.addStudent(s1);
        assertTrue(added, "Student should be added successfully.");

        Student found = manager.searchStudentByCode(s1.getCode());
        assertNotNull(found, "Student should be found by code.");
        assertEquals("Saigne", found.getLastName());
    }

    @Test
    public void testUpdateStudent() {
        Student s1 = new Student("Bave", "Jean", 9.0);
        manager.addStudent(s1);

        // Update mean score
        s1.setMeanScore(12.0);
        boolean updated = manager.updateStudent(s1);
        assertTrue(updated, "Student should be updated.");

        Student updatedStudent = manager.searchStudentByCode(s1.getCode());
        assertEquals(12.0, updatedStudent.getMeanScore(), 0.001, "Mean score should be updated correctly.");
    }

    @Test
    public void testDeleteStudent() {
        Student s1 = new Student("Saigne", "Jeanne", 10.0);
        manager.addStudent(s1);
        boolean deleted = manager.deleteStudent(s1.getCode());
        assertTrue(deleted, "Student should be deleted.");
        
        // Try deleting again should return false.
        deleted = manager.deleteStudent(s1.getCode());
        assertFalse(deleted, "Deleting a non-existing student should fail.");
    }

    @Test
    public void testGenerateReport() {
        manager.addStudent(new Student("Saigne", "Jean", 16.0)); // Best
        manager.addStudent(new Student("SBave", "Jean", 8.5));  // Worst
        manager.addStudent(new Student("Ceptes", "Jacques", 12.0));   // Average
        String report = manager.generateReport();
        assertNotNull(report, "Report should not be null.");
        assertTrue(report.contains("Best student"), "Report should mention the best student.");
        assertTrue(report.contains("Worst student"), "Report should mention the worst student.");
    }
}
