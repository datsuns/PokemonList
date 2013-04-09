package com.white.gorilla.pokemonlist.debug;

import android.text.format.Time;

/**
 * Created with IntelliJ IDEA.
 * User: datsuns
 * Date: 13/04/09
 * Time: 21:41
 * To change this template use File | Settings | File Templates.
 */
public class TimeStamp {
    private String title;
    private Long time;
    private Long diff;

    public TimeStamp( String title, Long time, Long diff){
        this.title = title;
        this.time = time;
        this.diff = diff;
    }

    public Long getTime(){
        return this.time;
    }

    public Long getDiff(){
        return this.diff;
    }

    public String getTitle() {
        return title;
    }
}
