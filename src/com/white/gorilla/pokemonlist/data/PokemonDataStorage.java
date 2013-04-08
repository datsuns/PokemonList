package com.white.gorilla.pokemonlist.data;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.white.gorilla.pokemonlist.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: datsuns
 * Date: 13/04/08
 * Time: 12:34
 * To change this template use File | Settings | File Templates.
 */
public class PokemonDataStorage {

    public PokemonDataStorage( Context context ){
        this.context = context;
        init(context);
    }

    public void init( Context context ){
        this.accessor = context.getApplicationContext().getResources().getAssets();
        createList();
    }

    public ArrayList<PokemonData> get(){
        return this.data;
    }

    public PokemonData get( String title ){
        for( PokemonData d : this.data ){
            if( d.getTitle().equals(title) ){
                return d;
            }

        }
        return new PokemonData(0, "", "", null);
    }


    public ArrayList<PokemonData> search(String filter){
        ArrayList<PokemonData> result = new ArrayList<PokemonData>();
        for( PokemonData d : this.data ){
            if( d.getTitle().contains(filter) ){
                result.add(d);
            }
        }
        return result;
    }

    private void createList() {
        this.data = new ArrayList<PokemonData>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(accessor.open("poke_list.html")));
            // TODO 0 origin ??
            int index = 1;
            while( true ){
                String s = br.readLine();
                if( s == null ){
                    break;
                }
                String title = s.substring(0, s.indexOf("<"));
                InputStream stream = openIconStream(index);
                Bitmap bitmap = BitmapFactory.decodeStream(stream);
                this.data.add( new PokemonData(index, title, s, bitmap) );
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public InputStream openIconStream( int iconNumber ) throws IOException {
        String name = "icon/" + String.format("%03d", iconNumber) + ".gif";
        try {
            InputStream s = this.context.getResources().getAssets().open(name);
            //Logger.log("d: position:" + iconNumber + "icon:" + name);
            return s;
        } catch (IOException e) {
            InputStream s = this.context.getResources().getAssets().open("icon/icon.png");
            Logger.log("load default icon to " + iconNumber);
            return s;
        }
    }
    private final Context context;
    private ArrayList<PokemonData> data;
    private AssetManager accessor;
}
