package test;

import exceptions.InvalidTimeException;
import exceptions.NotCountingDownException;
import exceptions.WrongLengthException;
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
        } catch (WrongLengthException | InvalidTimeException e) {
            fail("Exception thrown when shouldn't have.");
        }
    }

    @Test
    void testStartTimer() {
        testTimer.startTimer();
        assertTrue(testTimer.getCountdownStatus());
    }

    @Test
    void testStopTimer() {
        testTimer.stopTimer();
        assertFalse(testTimer.getCountdownStatus());
    }

    @Test
    void testResetStoppedTimer() {
        try {
            testTimer.setTime("0139");
            assertEquals(1, testTimer.getMins());
            assertEquals(39, testTimer.getSecs());
            testTimer.resetTimer();
            assertEquals(0, testTimer.getMins());
            assertEquals(0, testTimer.getSecs());
        } catch (WrongLengthException | InvalidTimeException e) {
            fail("shouldn't have thrown exception.");
        }
    }

    @Test
    void testResetCountingTimer() {
        try {
            testTimer.setTime("0540");
            testTimer.runTimer();
            assertTrue(testTimer.getCountdownStatus());
            testTimer.resetTimer();
            assertFalse(testTimer.getCountdownStatus());
            assertEquals(0, testTimer.getMins());
            assertEquals(0, testTimer.getSecs());
        } catch (WrongLengthException | InvalidTimeException e) {
            fail("Shouldn't have thrown exception.");
        }
    }

    @Test
    void testCountDownSecondsOnly() {
        try {
            testTimer.setTime("0005");
            testTimer.countDown();
            while (testTimer.getSecs() > 0) {
                assertTrue(testTimer.getCountdownStatus());
            }
            assertFalse(testTimer.getCountdownStatus());
            assertEquals(0, testTimer.getMins());
            assertEquals(0, testTimer.getSecs());
            // while loop runs, assert isCountingDown is true
            // after loop, mins and secs must be 0
        } catch (NotCountingDownException e) {
            fail("Exception shouldn't have been thrown (was counting down)");
        } catch (WrongLengthException | InvalidTimeException e) {
            fail("Exception should't have been thrown.");
        }
    }

    @Test
    void testCountDownOneMinute() {
        try {
            testTimer.setTime("0100");
            testTimer.countDown();
            do {
                assertTrue(testTimer.getCountdownStatus());
            }
            while (testTimer.getMins() != 0 | testTimer.getSecs() != 0);
            assertFalse(testTimer.getCountdownStatus());
            assertEquals(0, testTimer.getMins());
            assertEquals(0, testTimer.getSecs());
            // while loop runs, assert isCountingDown is true
            // after loop, mins and secs must be 0
        } catch (NotCountingDownException e) {
            fail("Exception should've have been thrown (was counting down)");
        } catch (WrongLengthException | InvalidTimeException e) {
            fail("Exception should't have been thrown.");
        }
    }

    @Test
    void playAlarm() {

    } // TODO - IMPLEMENT DURING GUI PHASE
}