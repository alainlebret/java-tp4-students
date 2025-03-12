package ex3;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class StudentManagerTest {
    private StudentManager manager;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        manager = new StudentManager();
        // For each test, we delete file to begin with an empty list
        File classFile = new File("class.dat");
        if (classFile.exists()) {
            classFile.delete();
        }
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    public void testAddStudent() {
        Student s1 = new Student("Saigne", "Jean", 11.0);
        boolean added = manager.addStudent(s1);
        assertTrue(added, "Adding student have succeed.");

        // Trying to add a clone
        Student s2 = new Student("Saigne", "Jean", 11.0);
        added = manager.addStudent(s2);
        assertFalse(added, "A student clone cannot be added.");
    }

    @org.junit.jupiter.api.Test
    public void testSearchStudentByCode() {
        Student s1 = new Student("Cepte", "Jacques", 14.0);
        manager.addStudent(s1);
        Student found = manager.searchStudentByCode(s1.getCode());
        assertNotNull(found, "A student should be found using its code.");
        assertEquals(s1.getLastName(), found.getLastName());
    }

    @org.junit.jupiter.api.Test
    public void testDeleteStudent() {
        Student s1 = new Student("Cepte", "Jacques", 9.0);
        manager.addStudent(s1);
        boolean deleted = manager.deleteStudent(s1.getCode());
        assertTrue(deleted, "The student should be deleted.");
        // A second deletion must failed
        deleted = manager.deleteStudent(s1.getCode());
        assertFalse(deleted, "Deleting a non-existent student should fail.");
    }

    @org.junit.jupiter.api.Test
    public void testUpdateStudent() {
        Student s1 = new Student("Cepte", "Jacques", 13.0);
        manager.addStudent(s1);
        s1.setMeanScore(15.0);
        boolean updated = manager.updateStudent(s1);
        assertTrue(updated, "The student must be updated.");
        Student updatedStudent = manager.searchStudentByCode(s1.getCode());
        assertEquals(15.0, updatedStudent.getMeanScore(), 0.001, "The score mean is not updated correctly.");
    }

    @org.junit.jupiter.api.Test
    public void testGenerateReport() {
        manager.addStudent(new Student("Saigne", "Jean", 16.0));
        manager.addStudent(new Student("Cepte", "Jacques", 8.5));
        manager.addStudent(new Student("Rage", "Jean", 12.0));

        String report = manager.generateReport();
        assertNotNull(report, "The report must exist.");
        assertTrue(report.contains("Highest scoring student"), "The report must contain information on the student with the highest mean score.");
        assertTrue(report.contains("Lowest scoring student"), "The report must contain information on the student with the lowest mean score.");

        // We delete the report
        File statusFile = new File("status.txt");
        if (statusFile.exists()) {
            statusFile.delete();
        }
    }


}