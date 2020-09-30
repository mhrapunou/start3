package by.epam.inner.beans;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StrongTrialTest {
    StrongTrial testStrongTrial;
    StrongTrial emptyTestStrongTrial;

    @Before
    public void setUp() throws Exception {
        testStrongTrial = new StrongTrial("Cool", 80, 90);
        emptyTestStrongTrial = new StrongTrial();
    }

    @After
    public void tearDown() throws Exception {
        testStrongTrial = null;
        emptyTestStrongTrial = null;
    }

    @Test
    public void testCopy() {
        assertEquals(testStrongTrial.getAccount(), testStrongTrial.copy().getAccount());
    }

    @Test
    public void isPassed() {
        assertTrue(testStrongTrial.isPassed());
    }
}