/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abdobean.wdcalendar.model;

import java.util.List;

/**
 *
 * @author abdo.talaat
 */
public class Lists {
    
    List<String> events;
    boolean issort = true;
    String start="";
    String end="";
    Object error = null;

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    public boolean isIssort() {
        return issort;
    }

    public void setIssort(boolean issort) {
        this.issort = issort;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
    
    
}
