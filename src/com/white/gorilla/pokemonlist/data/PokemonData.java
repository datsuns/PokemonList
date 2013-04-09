package com.white.gorilla.pokemonlist.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: datsuns
 * Date: 13/04/05
 * Time: 20:03
 * To change this template use File | Settings | File Templates.
 */

// bitmapは直接はserializeできないのでbyte[]に変換して保持する
public class PokemonData implements Serializable{
    private static final long serialVersionUID = 20130408L;

    public PokemonData(int index, String title, String body, Bitmap image){
        this.index = index;
        this.title = title;
        this.body = body;
        this.bitmap = image;
    }

    public String getTitle(){
        return this.title;
    }

    public String getBody(){
        return this.body;
    }

    public Bitmap getImage(){
        return this.bitmap;
    }

    public int getIndex(){
        return this.index;
    }

    private int index;
    String title;
    Bitmap bitmap;
    String body;
}
