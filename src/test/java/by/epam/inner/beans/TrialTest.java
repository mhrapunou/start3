package by.epam.inner.beans;

import by.epam.inner.beans.Trial;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TrialTest {

    Trial testTrialPassed;
    Trial testEqualTrial;
    Trial testTrialUnpassed;
    Trial emptyTrial;

    @Before
    public void setUp()  {
        testTrialPassed = new Trial("Metelskaya", 78,85);
        testEqualTrial = new Trial(testTrialPassed);
        testTrialUnpassed = new Trial("Tretinnikov", 68,25);
        emptyTrial = new Trial();
    }

    @After
    public void tearDown() {
        testTrialPassed = null;
        testEqualTrial = null;
        testTrialUnpassed = null;
        emptyTrial = null;
    }

    @Test
    public void testGetAccount() {
        Assert.assertEquals("Metelskaya", testEqualTrial.getAccount());
    }

    @Test
    public void testGetMark1() {
        Assert.assertEquals(78, testTrialPassed.getMark1());
    }

    @Test
    public void testGetMark2() {
        Assert.assertEquals(85, testTrialPassed.getMark2());
    }

    @Test
    public void testSetAccount() {
        emptyTrial.setAccount("Stepanenko");
        Assert.assertEquals("Stepanenko", emptyTrial.getAccount());
    }

    @Test
    public void testSetMark1() {
        emptyTrial.setMark1(70);
        Assert.assertEquals(70, emptyTrial.getMark1());
    }

    @Test
    public void testSetMark2() {
        emptyTrial.setMark2(65);
        Assert.assertEquals(65, emptyTrial.getMark2());
    }

    @Test
    public void testClearMarks() {
        emptyTrial.clearMarks();
        Assert.assertEquals(0, emptyTrial.getMark1() + emptyTrial.getMark2());
    }

    @Test
    public void testCopy() {
        Assert.assertEquals(testTrialPassed.getAccount(), testTrialPassed.copy().getAccount());
    }

    @Test
    public void testIsPassed() {
        Assert.assertTrue(testTrialPassed.isPassed());
        Assert.assertFalse(testTrialUnpassed.isPassed());
    }

   @Test
   public void testGetMarksToString() {
        Assert.assertEquals("78;85", testTrialPassed.getMarksToString());
    }

    @Test
    public void testTestToString() {
        Assert.assertEquals("Metelskaya;78;85;true", testTrialPassed.toString());
    }
}