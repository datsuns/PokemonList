package com.white.gorilla.pokemonlist;

import android.graphics.Bitmap;

/**
 * Created with IntelliJ IDEA.
 * User: satoshi
 * Date: 13/03/18
 * Time: 23:18
 * To change this template use File | Settings | File Templates.
 */
public class ListItem {
    /** 表示するアイコンのリソースID. */
    private int iconNumber;
    /** 表示するテキスト. */
    private String text;

    public ListItem(int iconNumber, String text){
        this.iconNumber = iconNumber;
        this.text = text;
    }
    public int getIconNumber() {
        return iconNumber;
    }

    public void setIconNumber(int iconNumber) {
        this.iconNumber = iconNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}


