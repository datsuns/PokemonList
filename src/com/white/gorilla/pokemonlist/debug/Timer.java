package com.white.gorilla.pokemonlist.debug;

import android.text.format.Time;
import com.white.gorilla.pokemonlist.Logger;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: datsuns
 * Date: 13/04/09
 * Time: 21:36
 * To change this template use File | Settings | File Templates.
 */
public class Timer {
    public Timer(){
        points = new ArrayList<TimeStamp>();
        prev = new TimeStamp("start", System.currentTimeMillis(), new Long(0));
        points.add(prev);
    }

    public void push( String title ){
        Long current = System.currentTimeMillis() ;
        TimeStamp now = new TimeStamp(title, current, current - prev.getTime());
        points.add(now);
        prev = now;
    }

    public void dump(){
        for( TimeStamp t : points ){
            Logger.log( "TIME:" + t.getTitle() + " : " + t.getTime() + " diff:" + t.getDiff() );
        }
    }

    private ArrayList<TimeStamp> points;
    private TimeStamp prev;
}

