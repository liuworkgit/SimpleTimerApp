package test;

import exceptions.InvalidTimeException;
import exceptions.NotCountingDownException;
import exceptions.WrongLengthException;
import model.Timer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimerTest {
    private Timer t;

    @BeforeEach
    void runBefore() {
        t = new Timer();
    }

    @Test
    /**
     * Test constructor
     * */
    void testConstructor() {
        assertEquals(0, t.getHours());
        assertEquals(0, t.getMins());
        assertEquals(0, t.getSecs());
        assertFalse(t.getCountdownStatus());
    }

    @Test
    /**
     * Tests if able to set valid times, such as:
     * - 99 hours, 59 minutes, 59 seconds
     * - only hours and minutes
     * - only minutes and seconds
     * - only hours and seconds
     * - all zeroes
     * - random values for hours, minutes and seconds
     * */
    void testSetValidTimes() {
        try {
            t.setTime("995959");
            assertEquals(99, t.getHours());
            assertEquals(59, t.getMins());
            assertEquals(59, t.getSecs());

            t.setTime("013100");
            assertEquals(1, t.getHours());
            assertEquals(31, t.getMins());
            assertEquals(0, t.getSecs());

            t.setTime("005859");
            assertEquals(0, t.getHours());
            assertEquals(58, t.getMins());
            assertEquals(59, t.getSecs());

            t.setTime("230002");
            assertEquals(23, t.getHours());
            assertEquals(0, t.getMins());
            assertEquals(2, t.getSecs());

            t.setTime("000000");
            assertEquals(0, t.getHours());
            assertEquals(0, t.getMins());
            assertEquals(0, t.getSecs());

            t.setTime("381924");
            assertEquals(38, t.getHours());
            assertEquals(19, t.getMins());
            assertEquals(24, t.getSecs());
        } catch (WrongLengthException | InvalidTimeException e) {
            fail("Exception thrown when shouldn't have.");
        }
    }

    @Test
    /**
     * Tests if exception thrown when inputted time string is too short
     * */
    void testSetTooShortTime() {
        try {
            t.setTime("532");
            fail("Time wrong length.");
        } catch (WrongLengthException e) {
            // pass
        } catch (InvalidTimeException e) {
            fail("Wrong exception thrown.");
        }
    }

    @Test
    /**
     * Tests if exception thrown when inputted time string is too long
     * */
    void testSetTooLongTime() {
        try {
            t.setTime("00000532");
            fail("Time wrong length.");
        } catch (WrongLengthException e) {
            // pass
        } catch (InvalidTimeException e) {
            fail("Wrong exception thrown.");
        }
    }

    @Test
    /**
     * Tests if exception thrown with invalid time string is entered. Length is correct
     * */
    void testSetInvalidTime() {
        try {
            t.setTime("006061");
            fail("Invalid time entered.");
        } catch (WrongLengthException e) {
            fail("Wrong exception thrown.");
        } catch (InvalidTimeException e) {
            // pass
        }
    }

    @Test
    /**
     * Tests if time can be run from arbitrary valid time
     * */
    // TODO - DOESN'T TERMINATE BECAUSE COUNTDOWN DOESN'T WORK
    void testRunTimer() {
        try {
            t.setTime("000130");
            t.runTimer();
            t.stopTimer();
            assertEquals(0, t.getHours());
            assertEquals(0, t.getMins());
            assertEquals(0, t.getSecs());
        } catch (WrongLengthException | InvalidTimeException e) {
            fail("Issue with setTime().");
        }
    }

    @Test
    /**
     * Tests if timer can be successfully started
     * */
    void testStartTimer() {
        t.startTimer();
        assertTrue(t.getCountdownStatus());
    }

    @Test
    /**
     * Tests if timer can be successfully stopped
     * */
    void testStopTimer() {
        t.stopTimer();
        assertFalse(t.getCountdownStatus());
    }

    @Test
    /**
     * Tests if a stopped timer can be successfully reset
     * */
    void testResetStoppedTimer() {
        try {
            t.setTime("000139");
            assertEquals(0, t.getHours());
            assertEquals(1, t.getMins());
            assertEquals(39, t.getSecs());
            t.resetTimer();
            assertEquals(0, t.getHours());
            assertEquals(0, t.getMins());
            assertEquals(0, t.getSecs());
        } catch (WrongLengthException | InvalidTimeException e) {
            fail("shouldn't have thrown exception.");
        }
    }

    @Test
    /**
     * Tests if possible to reset a timer that's still counting down
     * */
    void testResetCountingTimer() {
        try {
            t.setTime("010540");
            t.runTimer();
            assertTrue(t.getCountdownStatus());
            t.resetTimer();
            assertFalse(t.getCountdownStatus());
            assertEquals(0, t.getHours());
            assertEquals(0, t.getMins());
            assertEquals(0, t.getSecs());
        } catch (WrongLengthException | InvalidTimeException e) {
            fail("Shouldn't have thrown exception.");
        }
    }

    @Test
    /**
     * Tests if able to count down (seconds only)
     * */
    void testCountDownSecondsOnly() {
        try {
            t.setTime("000005");
            t.countDown();
            while (t.getSecs() > 0) {
                assertTrue(t.getCountdownStatus());
            }
            assertFalse(t.getCountdownStatus());
            assertEquals(0, t.getSecs());
        } catch (NotCountingDownException e) {
            fail("Exception shouldn't have been thrown (was counting down)");
        } catch (WrongLengthException | InvalidTimeException e) {
            fail("Exception should't have been thrown.");
        }
    }

    @Test
    /**
     * Tests if able to count down (minutes only)
     */
    void testCountDownMinutesOnly() {
        try {
            t.setTime("000400");
            t.countDown();
            do {
                assertTrue(t.getCountdownStatus());
            }
            while (t.getMins() != 0 | t.getSecs() != 0);
            assertFalse(t.getCountdownStatus());
            assertEquals(0, t.getMins());
            assertEquals(0, t.getSecs());
            // while loop runs, assert isCountingDown is true
            // after loop, mins and secs must be 0
        } catch (NotCountingDownException e) {
            fail("Exception should've have been thrown (was counting down)");
        } catch (WrongLengthException | InvalidTimeException e) {
            fail("Exception should't have been thrown.");
        }
    }

    @Test
    /**
     * Tests if able to count down (hours only)
     */
    void testCountDownHoursOnly() {
        try {
            t.setTime("020000");
            t.countDown();
            do {
                assertTrue(t.getCountdownStatus());
            }
            while (t.getHours() != 0 | t.getMins() != 0 | t.getSecs() != 0);
            assertFalse(t.getCountdownStatus());
            assertEquals(0, t.getHours());
            assertEquals(0, t.getMins());
            assertEquals(0, t.getSecs());
        } catch (NotCountingDownException e) {
            fail("Exception should've have been thrown (was counting down)");
        } catch (WrongLengthException | InvalidTimeException e) {
            fail("Exception should't have been thrown.");
        }
    }

    @Test
    /**
     * Tests if able to count down (edge case - 59 minutes, 59 seconds)
     * Note: testing 99 hours would take too long
     */
    void testCountDownEdgeCase() {
        try {
            t.setTime("005959");
            t.countDown();
            do {
                assertTrue(t.getCountdownStatus());
            }
            while (t.getMins() != 0 | t.getSecs() != 0);
            assertFalse(t.getCountdownStatus());
            assertEquals(0, t.getMins());
            assertEquals(0, t.getSecs());
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