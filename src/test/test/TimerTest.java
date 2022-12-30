package test;

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
    void setRightLengthTime() {
        try {
            testTimer.setTime(0500);
        } catch (WrongLengthException e) {

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