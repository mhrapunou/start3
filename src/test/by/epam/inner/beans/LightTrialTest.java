package by.epam.inner.beans;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LightTrialTest {
    LightTrial testLightTrial;
    LightTrial testEmptyLightTrial;

    @Before
    public void setUp() throws Exception {
        testLightTrial = new LightTrial("Cool", 90, 80);
        testEmptyLightTrial = new LightTrial();
    }

    @After
    public void tearDown() throws Exception {
        testLightTrial = null;
        testEmptyLightTrial = null;
    }

    @Test
    public void testCopy() {
        assertEquals(testLightTrial.getAccount(), testLightTrial.copy().getAccount());
    }

    @Test
    public void testIsPassed() {
        assertTrue(testLightTrial.isPassed());
    }
}