package cs246.finalproject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static java.util.Calendar.HOUR;

/**
 * Entry class
 *
 * @author Cory Swainston
 * @version 1.0
 */

public class Entry {
    private Calendar timestamp;
    private String journalText;
    private String title;
    private int rating;

    /**
     * Default constructor
     */
    public Entry() {
        timestamp = Calendar.getInstance();
        journalText = "";
        title = "";
        rating = 0;
    }

    /**
     * Non-default constructors with all parameters
     *
     * @param rating
     * @param journalText
     * @param title
     * @param timestamp
     */
    public Entry(int rating, String journalText, String title, String timestamp) {

        setTimestamp(timestamp);
        setJournalText(journalText);
        setTitle(title);
        setRating(rating);
    }

    /**
     * Getters and setters
     */
    public String getTimestamp() {
        return String.valueOf(timestamp.getTimeInMillis());
    }

    public String getJournalText() {
        return journalText;
    }

    public String getTitle() { return title; }

    public int getRating() {
        return rating;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp.setTimeInMillis(Long.parseLong(timestamp));
    }

    public void setJournalText(String journalText) {
        if (journalText != null) {
            this.journalText = journalText;
        }
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return sdf.format(timestamp.getTime());
    }

    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        String time = sdf.format(timestamp.getTime());

        return time;
    }
}
