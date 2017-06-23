package cs246.finalproject;

import java.util.Calendar;

/**
 * Entry class
 *
 * @author Cory Swainston
 * @version 1.0
 * @since 6/23/17
 */

public class Entry {
    private Calendar date;
    private String journalText;
    private int rating;

    /**
     * Constructors
     */
    public Entry() {
        date = Calendar.getInstance();
        journalText = "";
        rating = 0;
    }

    public Entry(int rating, String text) {
        date = Calendar.getInstance();
        journalText = text;
        this.rating = rating;
    }

    /**
     * Getters and setters
     */
    public Calendar getDate() {
        return date;
    }

    public String getJournalText() {
        return journalText;
    }

    public int getRating() {
        return rating;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setJournalText(String journalText) {
        this.journalText = journalText;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
