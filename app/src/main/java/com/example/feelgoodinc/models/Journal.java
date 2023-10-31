package com.example.feelgoodinc.models;

import java.util.Date;

/**
 * This class is a Model class that will be used to store a user's
 * journal.
 *
 * Usage example:
 * <pre>
 *  String journalTitle = "";
 *  Date createdWhen = Date.
 * Journal journal = new Journal(journalTitle, new Date(), newDate(), "This is a journal about my feelings");
 * </pre>
 *
 */
public class Journal {
    private String journalName;
    private Date createdWhen;
    private Date lastEditedWhen;
    private String content;

    //TODO: may want to change the constructor to prevent unwanted nulls
    public Journal(String journalName, Date createdWhen, Date lastEditedWhen, String content) {
        this.journalName = journalName;
        this.createdWhen = createdWhen;
        this.lastEditedWhen = lastEditedWhen;
        this.content = content;
    }

    public String getJournalName() {
        return journalName;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    public Date getCreatedWhen() {
        return createdWhen;
    }

    public void setCreatedWhen(Date createdWhen) {
        this.createdWhen = createdWhen;
    }

    public Date getLastEditedWhen() {
        return lastEditedWhen;
    }

    public void setLastEditedWhen(Date lastEditedWhen) {
        this.lastEditedWhen = lastEditedWhen;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
