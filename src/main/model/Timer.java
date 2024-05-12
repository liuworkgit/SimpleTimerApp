package model;

import exceptions.CountdownActiveException;
import exceptions.InvalidTimeException;
import exceptions.NotCountingDownException;
import exceptions.WrongLengthException;

import java.awt.*;

// represents a timer that counts down in minutes and seconds
public class Timer {
    private int mins;
    private int secs;
    private boolean isCountingDown;

    // EFFECTS: creates a new timer object that has 0 mins, 0 secs, and isCountingDown == false
    public Timer() {
        mins = 0;
        secs = 0;
        isCountingDown = false;
    }

    // REQUIRES: fullTime length is 4 digits - otherwise, throw WrongLengthException
    //           The first two digits = [0, 59]
    //           The remaining two digits = [0, 59]
    //           If inputted number can't be reasonably parsed into mins and secs, throw InvalidTimeException
    // EFFECTS: sets a time
    // MODIFIES: this, mins, secs
    public void setTime(String fullTime) throws WrongLengthException, InvalidTimeException {
        if (fullTime.length() != 4) {
            throw new WrongLengthException();
        } else {
            int newMins = Integer.parseInt(fullTime.substring(0, 2));
            int newSecs = Integer.parseInt(fullTime.substring(2, 4));
            if (areCorrectValues(newMins, newSecs)) {
                mins = newMins;
                secs = newSecs;
            } else {
                throw new InvalidTimeException();
            }
        }
    }

    // EFFECTS: returns true if the given mins value is between 0 and 59,
    //          and if the given secs value is between 0 and 59
    public boolean areCorrectValues(int mins, int secs) {
        return ((0 <= mins) && (mins <= 59)) && ((0 <= secs) && (secs <= 59));
    }

    // REQUIRES: isCountingDown == false
    // EFFECTS: runs the timer from start to finish
    public void runTimer() {
        try {
            startTimer();
            countDown();
            playAlarm();
        } catch (NotCountingDownException e) {
            System.out.println("Countdown didn't start.");
        } catch (CountdownActiveException e) {
            System.out.println("Countdown didn't end.");
        }
    }

    // EFFECTS: starts the timer by setting isCountingDown to true
    // MODIFIES: this, isCountingDown
    public void startTimer() {
        isCountingDown = true;
    }

    // EFFECTS: stops the timer by setting isCountingDown to false
    // MODIFIES: this, isCountingDown
    public void stopTimer() {
        isCountingDown = false;
    }

    // EFFECTS: resets the timer.
    //          if isCountingDown == true, calls stopTimer(), then resets the timer
    // MODIFIES: this, mins, secs, isCountingDown
    public void resetTimer() {
        if (isCountingDown) {
            stopTimer();
        }
        mins = 0;
        secs = 0;
    }

    // REQUIRES: isCountingDown == true
    //           otherwise, throw NotCountingDownException
    // EFFECTS: counts down the timer value to 0, then calls stopTimer
    // MODIFIES: this, mins, secs
    public void countDown() throws NotCountingDownException {
        if (!isCountingDown) {
            throw new NotCountingDownException();
        }
        while (secs > 0 | mins > 0) {
            if (secs == 0) {
                mins--;
                secs = 59;
            }
            countSecs();
        }
        stopTimer();
    }

    // EFFECTS: counts down the secs value to 0
    // MODIFIES: this, secs
    private void countSecs() {
        while (secs > 0) secs--;
    }

    // REQUIRES: isCountingDown == false
    //           otherwise, throw CountdownActiveException
    // EFFECTS: plays alarm
    public void playAlarm() throws CountdownActiveException {
        if (isCountingDown) {
            throw new CountdownActiveException();
        }
        Toolkit.getDefaultToolkit().beep();
    }

    // GETTERS
    public int getMins() {
        return mins;
    }

    public int getSecs() {
        return secs;
    }

    public boolean getCountdownStatus() {
        return isCountingDown;
    }
}
