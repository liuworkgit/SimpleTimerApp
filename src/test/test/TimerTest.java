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
    /**
     * Tests constructor
     */
    void testConstructor() {
        assertEquals(0, timer.getMins());
        assertEquals(0, timer.getSecs());
        assertFalse(timer.getCountdownStatus());
    }

    @Test
    /**
     * Tests if able to set valid times, such as:
     * - an arbitrary time that meets criteria
     * - 0 mins, 0 secs
     * - 59 mins, 59 secs
     * - 59 mins, 0 secs
     * - 0 mins, 59 secs
     */
    void testSetValidTimes() {
        try {
            timer.setTime("0532");
            assertEquals(5, timer.getMins());
            assertEquals(32, timer.getSecs());

            timer.setTime("0000");
            assertEquals(0, timer.getMins());
            assertEquals(0, timer.getSecs());

            timer.setTime("5959");
            assertEquals(59, timer.getMins());
            assertEquals(59, timer.getSecs());

            timer.setTime("5900");
            assertEquals(59, timer.getMins());
            assertEquals(0, timer.getSecs());

            timer.setTime("0059");
            assertEquals(0, timer.getMins());
            assertEquals(59, timer.getSecs());
        } catch (WrongLengthException | InvalidTimeException e) {
            fail("Exception thrown when shouldn't have.");
        }
    }

    @Test
    /**
     * Tests if exception thrown when given time input is too short
     */
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
    /**
     * Tests if exception thrown when given time input is too long
     */
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
    /**
     * Tests if exception thrown when given time input isn't within 0-59 mins and 0-59 secs
     */
    void testSetInvalidTime() {
        try {
            timer.setTime("6000");
            fail("Invalid time entered.");
        } catch (WrongLengthException e) {
            fail("Wrong exception thrown.");
        } catch (InvalidTimeException e) {
            // pass
        }
    }

    @Test
    /**
     * Tests if able to run a timer successfully from start to finish
     */
    void testRunTimer() {
        try {
            timer.setTime("0130");
            timer.runTimer();
            assertFalse(timer.getCountdownStatus());
            assertEquals(0, timer.getMins());
            assertEquals(0, timer.getSecs());
        } catch (WrongLengthException | InvalidTimeException e) {
            fail("Issue with setTime().");
        }
    }

    @Test
    /**
     * Tests if able to successfully start a timer
     */
    void testStartTimer() {
        timer.startTimer();
        assertTrue(timer.getCountdownStatus());
    }

    @Test
    /**
     * Tests if able to successfully stop a timer
     */
    void testStopTimer() {
        timer.stopTimer();
        assertFalse(timer.getCountdownStatus());
    }

    @Test
    /**
     * Tests if able to reset a timer that isn't running
     */
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
    /**
     * Tests if able to reset a timer that is currently running
     */
    void testResetCountingTimer() {
        try {
            timer.setTime("0540");
            timer.runTimer();
            assertFalse(timer.getCountdownStatus());
            timer.resetTimer();
            assertFalse(timer.getCountdownStatus());
            assertEquals(0, timer.getMins());
            assertEquals(0, timer.getSecs());
        } catch (WrongLengthException | InvalidTimeException e) {
            fail("Shouldn't have thrown exception.");
        }
    }

    @Test
    /**
     * Tests the timer's ability to count down seconds only
     */
    void testCountDownSecondsOnly() {
        try {
            timer.setTime("0005");
            timer.startTimer();
            timer.countDown();
            assertFalse(timer.getCountdownStatus());
            assertEquals(0, timer.getMins());
            assertEquals(0, timer.getSecs());
        } catch (NotCountingDownException e) {
            fail("Exception shouldn't have been thrown (was counting down)");
        } catch (WrongLengthException | InvalidTimeException e) {
            fail("Exception should't have been thrown.");
        }
    }

    @Test
    /**
     * Tests the timer's ability to count down a minute only
     */
    void testCountDownOneMinute() {
        try {
            timer.setTime("0100");
            timer.startTimer();
            timer.countDown();
            assertFalse(timer.getCountdownStatus());
            assertEquals(0, timer.getMins());
            assertEquals(0, timer.getSecs());
        } catch (NotCountingDownException e) {
            fail("Exception should've have been thrown (was counting down)");
        } catch (WrongLengthException | InvalidTimeException e) {
            fail("Exception should't have been thrown.");
        }
    }

    @Test
    /**
     * Tests the timer's ability to play a sound
     */
    void playAlarm() {

    } // TODO - IMPLEMENT DURING GUI PHASE
}