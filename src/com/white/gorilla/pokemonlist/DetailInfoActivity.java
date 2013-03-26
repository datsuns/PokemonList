package com.white.gorilla.pokemonlist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;

public class DetailInfoActivity extends Activity {
	String TEXT_ENCODING = "UTF-8";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail_view);
        WebView v  = (WebView)findViewById(R.id.htmlRendorView);
        int num = getIntent().getExtras().getInt(ConstantData.INTENT_FILTER_SELECTED_ITEM);
        ListAccessor accessor = new ListAccessor(this);
        v.loadDataWithBaseURL(null, accessor.getItem(num), "text/html" , TEXT_ENCODING, null );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_pokemon_detail_view, menu);
        return true;
    }
}
