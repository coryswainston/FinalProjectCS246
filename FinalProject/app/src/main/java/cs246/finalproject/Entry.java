package cs246.finalproject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Entry class
 *
 * @author Cory Swainston
 * @version 1.0
 */

public class Entry {
    private String timestamp;
    private String journalText;
    private int rating;

    /**
     * Default constructor
     */
    public Entry() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        timestamp = df.format(c.getTime());
        journalText = "";
        rating = 0;
    }

    /**
     * Non-default constructor with parameters, sets to current date
     *
     * @param rating The heartburn rating
     * @param text   Journal text
     */
    public Entry(int rating, String text) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        timestamp = df.format(c.getTime());
        journalText = text;
        this.rating = rating;
    }

    /**
     * Getters and setters
     */
    public String getTimestamp() {
        return timestamp;
    }

    public String getJournalText() {
        return journalText;
    }

    public int getRating() {
        return rating;
    }

    public void setDate(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setJournalText(String journalText) {
        this.journalText = journalText;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
