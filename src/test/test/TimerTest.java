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
    void setRightLengthTime() {
        try {
            testTimer.setTime(532);
            assertEquals(5, testTimer.getMins());
            assertEquals(32, testTimer.getSecs());
            assertFalse(testTimer.getCountdownStatus());
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