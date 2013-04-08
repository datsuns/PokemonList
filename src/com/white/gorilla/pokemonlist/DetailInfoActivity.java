package com.white.gorilla.pokemonlist;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import com.white.gorilla.pokemonlist.data.PokemonData;

import java.io.IOException;
import java.io.InputStream;

public class DetailInfoActivity extends Activity {
	String TEXT_ENCODING = "UTF-8";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail_view);
        PokemonData data = (PokemonData)getIntent().getSerializableExtra(ConstantData.SELECTED_ITEM_DATA);
        loadTitleView(data);
        loadMainView(data);
    }

    private void loadTitleView(PokemonData data) {
        ImageView image = (ImageView)findViewById(R.id.detailviewimage);
        image.setImageBitmap(data.getImage());
        TextView text = (TextView)findViewById(R.id.detailViewText);
        text.setText(data.getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_pokemon_detail_view, menu);
        return true;
    }

    public void loadMainView(PokemonData data){
        WebView v  = (WebView)findViewById(R.id.htmlLenderView);
        v.loadDataWithBaseURL(null, data.getBody(), "text/html" , TEXT_ENCODING, null );
    }
}
