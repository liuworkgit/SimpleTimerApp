package model;

import exceptions.CountdownActiveException;
import exceptions.InvalidTimeException;
import exceptions.NotCountingDownException;
import exceptions.WrongLengthException;

// TODO - LATER CREATE METHOD THAT CHECKS TO SEE IF TIMER IS 00:00
// represents a timer
public class Timer {
    private int hours;
    private int mins;
    private int secs;
    private boolean isCountingDown;

    // EFFECTS: creates a new timer object that has 0 hours, 0 mins, 0 secs, and isCountingDown == false
    public Timer() {
        hours = 0;
        mins = 0;
        secs = 0;
        isCountingDown = false;
    }

    // REQUIRES: fullTime length is 6 digits - otherwise, throw WrongLengthException
    //           The first two digits represent hours, between [0, 99]
    //           The middle two digits represent minutes, between [0, 59]
    //           The last two digits represent seconds, between [0, 59]
    //           If inputted number can't be reasonably parsed, throw InvalidTimeException
    // EFFECTS: sets a time
    // MODIFIES: this, hours, mins, secs
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

    // EFFECTS: returns true if:
    //          - the given hours value is between 0 and 99
    //          - the given mins value is between 0 and 59
    //          - the given secs value is between 0 and 59
    public boolean areCorrectValues(int hours, int mins, int secs) {
        return ((0 <= mins) && (mins <= 12)) && ((0 <= secs) && (secs <= 59));
    }

    // REQUIRES: isCountingDown == false
    // EFFECTS: runs the timer from start to finish
    // TODO
    public void runTimer() {
        try {
            startTimer();
            countDown();
            playAlarm();
        } catch (NotCountingDownException e) {
            System.out.println("Countdown didn't start.");
            // TODO - WHAT GOES HERE?
        } catch (CountdownActiveException e) {
            System.out.println("Countdown didn't end.");
            // TODO - WHAT GOES HERE?
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
    // MODIFIES: this, hours, mins, secs, isCountingDown
    public void resetTimer() {
        if (isCountingDown) {
            stopTimer();
        }
        mins = 0;
        secs = 0;
    }

    // REQUIRES: isCountingDown == true
    //           otherwise, throw NotCountingDownException
    // EFFECTS: counts down the timer value to 0
    // TODO
    public void countDown() throws NotCountingDownException {
        if (isCountingDown) {
//            if (mins = 0) {
//                countDownSecs(secs);
//            } else {
//
//            }
//            // base case: mins = 0
//            // reducing step: mins -= 1
        } else {
            throw new NotCountingDownException();
        }
    }

    // REQUIRES: isCountingDown == false
    //           otherwise, throw CountdownActiveException
    // EFFECTS: plays alarm
    // TODO
    public void playAlarm() throws CountdownActiveException {
    }

    // GETTERS
    public int getHours() {
        return hours;
    }

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
