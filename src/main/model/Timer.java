package model;

import exceptions.CountdownActiveException;
import exceptions.InvalidTimeException;
import exceptions.NotCountingDownException;
import exceptions.WrongLengthException;

// TODO - LATER CREATE METHOD THAT CHECKS TO SEE IF TIMER IS 00:00
// represents a timer
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
    //           The first two digits that correspond to minutes must be between 0 and 12
    //           The remaining two digits that correspond to seconds must be between 00 and 59
    // EFFECTS: sets a time
    // MODIFIES: this, mins, secs
    // TODO - THROW EXCEPTION IF FULLTIME WRONG LENGTH
    // TODO - THROW EXCEPTION IF INPUTTED TIME INVALID
    // ex: mins must be between 0 and 12, secs must be between 0 and 59
    public void setTime(String fullTime) throws WrongLengthException, InvalidTimeException {}

    // REQUIRES: isCountingDown == false
    // EFFECTS: runs the timer from start to finish
    public void runTimer() {}

    // EFFECTS: starts the timer by setting isCountingDown to true
    public void startTimer() {}

    // EFFECTS: stops the timer by setting isCountingDown to false
    public void stopTimer() {}

    // EFFECTS: resets the timer.
    //          if isCountingDown == true, calls stopTimer(), then resets the timer
    public void resetTimer() {}

    // REQUIRES: isCountingDown == true
    //           otherwise, throw NotCountingDownException
    // EFFECTS: counts down
    // TODO - THROW EXCEPTION IF NOT COUNTING DOWN?
    public void countDown() throws NotCountingDownException {}

    // REQUIRES: isCountingDown == false
    //           otherwise, throw CountdownActiveException
    // EFFECTS: plays alarm
    // TODO - THROW EXCEPTION IF COUNTING DOWN?
    public void playAlarm() throws CountdownActiveException {}

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
