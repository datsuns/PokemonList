package com.white.gorilla.pokemonlist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class InformationActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list_info);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_pokemon_list_info, menu);
        return true;
    }
}
