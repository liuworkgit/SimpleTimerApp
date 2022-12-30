package test;

import model.InvalidTimeException;
import model.WrongLengthException;
import model.Timer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimerTest {
    private Timer testTimer;

    @BeforeEach
    void runBefore() {
        testTimer = new Timer();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testTimer.getMins());
        assertEquals(0, testTimer.getSecs());
        assertFalse(testTimer.getCountdownStatus());
    }

    @Test
    void testSetValidTimes() {
        try {
            testTimer.setTime("0532");
            assertEquals(5, testTimer.getMins());
            assertEquals(32, testTimer.getSecs());

            testTimer.setTime("0000");
            assertEquals(0, testTimer.getMins());
            assertEquals(0, testTimer.getSecs());

            testTimer.setTime("1259");
            assertEquals(12, testTimer.getMins());
            assertEquals(59, testTimer.getSecs());
        } catch (WrongLengthException e) {
            fail("Exception thrown when shouldn't have.");
        } catch (InvalidTimeException e) {
            fail("Exception thrown when shouldn't have.");
        }
    }

    @Test
    void testSetTooShortTime() {
        try {
            testTimer.setTime("532");
            fail("Time wrong length.");
        } catch (WrongLengthException e) {
            // pass
        } catch (InvalidTimeException e) {
            fail("Wrong exception thrown.");
        }
    }

    @Test
    void testSetTooLongTime() {
        try {
            testTimer.setTime("00532");
            fail("Time wrong length.");
        } catch (WrongLengthException e) {
            // pass
        } catch (InvalidTimeException e) {
            fail("Wrong exception thrown.");
        }
    }

    @Test
    void testSetInvalidTime() {
        try {
            testTimer.setTime("5900");
            fail("Invalid time entered.");
        } catch (WrongLengthException e) {
            fail("Wrong exception thrown.");
        } catch (InvalidTimeException e) {
            // pass
        }
    }

    @Test
    void testRunTimer() {
        try {
            testTimer.setTime("0130");
            testTimer.runTimer();
            assertFalse(testTimer.getCountdownStatus());
            assertEquals(0, testTimer.getMins());
            assertEquals(0, testTimer.getSecs());
        } catch (WrongLengthException e) {
            fail("Exception thrown when shouldn't have.");
        } catch (InvalidTimeException e) {
            fail("Exception thrown when shouldn't have.");
        }
    }

    @Test
    void startTimer() {
    }

    @Test
    void stopTimer() {
    }

    @Test
    void resetTimer() {
    }

    @Test
    void countDown() {
    }

    @Test
    void playAlarm() {
    }
}