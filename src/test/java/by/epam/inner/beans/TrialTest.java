package by.epam.inner.beans;

import junit.framework.TestCase;

public class TrialTest extends TestCase {

    Trial testTrialPassed;
    Trial testEqualTrial;
    Trial testTrialUnpassed;
    Trial emptyTrial;

    public void setUp()  {
        testTrialPassed = new Trial("Metelskaya", 78,85);
        testEqualTrial = new Trial(testTrialPassed);
        testTrialUnpassed = new Trial("Tretinnikov", 68,25);
        emptyTrial = new Trial();
    }

    public void tearDown() {
    }

    public void testGetAccount() {
        assertEquals("Metelskaya", testEqualTrial.getAccount());
    }

    public void testGetMark1() {
        assertEquals(78, testTrialPassed.getMark1());
    }

    public void testGetMark2() {
        assertEquals(85, testTrialPassed.getMark2());
    }

    public void testSetAccount() {
        emptyTrial.setAccount("Stepanenko");
        assertEquals("Stepanenko", emptyTrial.getAccount());
    }

    public void testSetMark1() {
        emptyTrial.setMark1(70);
        assertEquals(70, emptyTrial.getMark1());
    }

    public void testSetMark2() {
        emptyTrial.setMark2(65);
        assertEquals(65, emptyTrial.getMark2());
    }

    public void testClearMarks() {
        emptyTrial.clearMarks();
        assertEquals(0, emptyTrial.getMark1() + emptyTrial.getMark2());
    }

    public void testCopy() {
        assertEquals(testTrialPassed.getAccount(), testTrialPassed.copy().getAccount());
    }

    public void testIsPassed() {
        assertTrue(testTrialPassed.isPassed());
        assertFalse(testTrialUnpassed.isPassed());
    }

    public void testGetMarksToString() {
        assertEquals("78;85", testTrialPassed.getMarksToString());
    }

    public void testTestToString() {
        assertEquals("Metelskaya;78;85;true", testTrialPassed.toString());
    }
}