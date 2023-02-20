package test;

import exceptions.InvalidTimeException;
import exceptions.NotCountingDownException;
import exceptions.WrongLengthException;
import model.Timer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TimerTest {
    private Timer timer;

    @BeforeEach
    void runBefore() {
        timer = new Timer();
    }

    @Test
    void testConstructor() {
        assertEquals(0, timer.getHours());
        assertEquals(0, timer.getMins());
        assertEquals(0, timer.getSecs());
        assertFalse(timer.getCountdownStatus());
    }

    @Test
    void testSetValidTimes() {
        try {
            timer.setTime("0532");
            assertEquals(5, timer.getMins());
            assertEquals(32, timer.getSecs());

            timer.setTime("0000");
            assertEquals(0, timer.getMins());
            assertEquals(0, timer.getSecs());

            timer.setTime("1259");
            assertEquals(12, timer.getMins());
            assertEquals(59, timer.getSecs());
        } catch (WrongLengthException e) {
            fail("Exception thrown when shouldn't have.");
        } catch (InvalidTimeException e) {
            fail("Exception thrown when shouldn't have.");
        }
    }

    @Test
    void testSetTooShortTime() {
        try {
            timer.setTime("532");
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
            timer.setTime("00532");
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
            timer.setTime("5900");
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
            timer.setTime("0130");
            timer.runTimer();
            do {
                assertTrue(timer.getCountdownStatus());
            }
            while (timer.getMins() != 0 | timer.getSecs() != 0);
            assertFalse(timer.getCountdownStatus());
            assertEquals(0, timer.getMins());
            assertEquals(0, timer.getSecs());
        } catch (WrongLengthException | InvalidTimeException e) {
            fail("Issue with setTime().");
        }
    }

    @Test
    void testStartTimer() {
        timer.startTimer();
        assertTrue(timer.getCountdownStatus());
    }

    @Test
    void testStopTimer() {
        timer.stopTimer();
        assertFalse(timer.getCountdownStatus());
    }

    @Test
    void testResetStoppedTimer() {
        try {
            timer.setTime("0139");
            assertEquals(1, timer.getMins());
            assertEquals(39, timer.getSecs());
            timer.resetTimer();
            assertEquals(0, timer.getMins());
            assertEquals(0, timer.getSecs());
        } catch (WrongLengthException | InvalidTimeException e) {
            fail("shouldn't have thrown exception.");
        }
    }

    @Test
    void testResetCountingTimer() {
        try {
            timer.setTime("0540");
            timer.runTimer();
            assertTrue(timer.getCountdownStatus());
            timer.resetTimer();
            assertFalse(timer.getCountdownStatus());
            assertEquals(0, timer.getMins());
            assertEquals(0, timer.getSecs());
        } catch (WrongLengthException | InvalidTimeException e) {
            fail("Shouldn't have thrown exception.");
        }
    }

    @Test
    void testCountDownSecondsOnly() {
        try {
            timer.setTime("0005");
            timer.countDown();
            while (timer.getSecs() > 0) {
                assertTrue(timer.getCountdownStatus());
            }
            assertFalse(timer.getCountdownStatus());
            assertEquals(0, timer.getMins());
            assertEquals(0, timer.getSecs());
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
            timer.setTime("0100");
            timer.countDown();
            do {
                assertTrue(timer.getCountdownStatus());
            }
            while (timer.getMins() != 0 | timer.getSecs() != 0);
            assertFalse(timer.getCountdownStatus());
            assertEquals(0, timer.getMins());
            assertEquals(0, timer.getSecs());
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