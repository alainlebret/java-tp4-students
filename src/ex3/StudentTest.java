package ex3;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    public void testStudentCreation() {
        Student s = new Student("Saigne", "Jean", 12.5);
        assertNotNull(s, "Student object cannot be null.");
        assertEquals("Saigne", s.getLastName());
        assertEquals("Jean", s.getFirstName());
        assertEquals(12.5, s.getMeanScore());

        // Verify generated code
        int expectedCode = ('S' + 'J') + ("Saigne".length() + "Jean".length());
        assertEquals(expectedCode, s.getCode(), "The generated code is incorrect.");
    }

    @org.junit.jupiter.api.Test
    public void testSettersUpdateCode() {
        Student s = new Student("Cepte", "Jack", 15.0);
        int oldCode = s.getCode();
        s.setLastName("Saigne");
        int newCode = s.getCode();
        assertNotEquals(oldCode, newCode, "The code must be modified after last name update.");
        s.setFirstName("Jean");
        int expectedCode = ('S' + 'J') + ("Saigne".length() + "Jean".length());
        assertEquals(expectedCode, s.getCode(), "The code is not correctly recalculated.");
    }

}