package by.epam.inner.beans;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExtraTrialTest {
    private ExtraTrial passedExtraTrial;
    private ExtraTrial emptyExtraTrial;


    @Before
    public void setUp()  {
        passedExtraTrial = new ExtraTrial("Cool", 80, 70, 90);
        emptyExtraTrial = new ExtraTrial();
    }

    @After
    public void tearDown() {
        passedExtraTrial = null;
        emptyExtraTrial = null;
    }

    @Test
    public void testGetMark3() {
        assertEquals(90, passedExtraTrial.getMark3());
    }

    @Test
    public void testSetMark3() {
       ExtraTrial expectedExtraTrialWithMark3 = new ExtraTrial("Clever", 50, 10, 15);
       expectedExtraTrialWithMark3.setMark3(90);
       assertEquals(expectedExtraTrialWithMark3.getMark3(), passedExtraTrial.getMark3());
    }

    @Test
    public void testCopy() {
        assertEquals(passedExtraTrial.getAccount(), passedExtraTrial.copy().getAccount());
    }

    @Test
    public void testClearMarks() {
        ExtraTrial clearMarksTrial = new ExtraTrial("Average", 50, 80, 90);
        clearMarksTrial.clearMarks();
        assertEquals(0, clearMarksTrial.getMark1() + clearMarksTrial.getMark2() + clearMarksTrial.getMark3());
    }

    @Test
    public void testIsPassed() {
        assertTrue(passedExtraTrial.isPassed());
    }

    @Test
    public void testGetMarksToString() {
        String expectedMarksToString = "80;70;90";
        assertEquals(expectedMarksToString, passedExtraTrial.getMarksToString());
    }
}