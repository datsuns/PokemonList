package com.white.gorilla.pokemonlist;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class PokemonDetailView extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail_view);
        WebView v  = (WebView)findViewById(R.id.htmlRendorView);
        int num = getIntent().getExtras().getInt("ItemNumber");
        PokemonListAccessor accessor = new PokemonListAccessor(this);
        v.loadDataWithBaseURL(null, accessor.getItem(num), "text/html" , "UTF-8", null );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_pokemon_detail_view, menu);
        return true;
    }
}
