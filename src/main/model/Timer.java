package model;

// represents a timer
public class Timer {
    private int mins;
    private int secs;
    private boolean isCountingDown;

    // EFFECTS: creates a new timer object
    public Timer() {
        mins = 0;
        secs = 0;
    }

    // REQUIRES: fullTime length is four digits - otherwise, throw WrongLengthException
    // EFFECTS: sets a time
    // MODIFIES: this, mins, secs
    // TODO - THROW EXCEPTION IF FULLTIME WRONG LENGTH
    // TODO - THROW EXCEPTION IF INPUTTED TIME INVALID
    // ex: mins must be between 0 and 12, secs must be between 0 and 59
    public void setTime(int fullTime) throws WrongLengthException, InvalidTimeException {}

    // EFFECTS: starts the timer by setting isCountingDown to true
    public void startTimer() {}

    // EFFECTS: stops the timer
    public void stopTimer() {}

    // EFFECTS: resets the timer.
    //          if isCountingDown == false, calls stopTimer(), then resets the timer
    public void resetTimer() {}

    // EFFECTS: counts down
    // TODO - THROW EXCEPTION IF NOT COUNTING DOWN?
    public void countDown() {}

    // EFFECTS: plays alarm
    // TODO - THROW EXCEPTION IF COUNTING DOWN?
    public void playAlarm() {}
}
