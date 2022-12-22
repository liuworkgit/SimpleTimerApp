package ui;

// represents a timer
public class Timer {
    private int mins;
    private int secs;
    private boolean isCountingDown;

    // TODO - SINGLE INSTANCE OR NOT?
    // EFFECTS: creates a new timer object
    public Timer() {
        mins = 0;
        secs = 0;
    }

    // REQUIRES: fullTime length is four digits
    // EFFECTS: sets a time
    // MODIFIES: this, mins, secs
    public void setTime(int fullTime) {}

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
