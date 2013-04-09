package com.white.gorilla.pokemonlist;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailInfoActivity extends Activity {
	String TEXT_ENCODING = "UTF-8";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail_view);

        String title = (String)getIntent().getSerializableExtra(ConstantData.SELECTED_ITEM_TITLE);
        String body = (String)getIntent().getSerializableExtra(ConstantData.SELECTED_ITEM_BODY);
        byte[] binary = (byte[])getIntent().getSerializableExtra(ConstantData.SELECTED_ITEM_IMAGE);
        Bitmap bitmap = BitmapFactory.decodeByteArray(binary, 0, binary.length);

        loadTitleView(title, bitmap);
        loadMainView(body);
    }

    private void loadTitleView(String title, Bitmap bitmap){
        ImageView image = (ImageView)findViewById(R.id.detailviewimage);
        image.setImageBitmap(bitmap);
        TextView text = (TextView)findViewById(R.id.detailViewText);
        text.setText(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_pokemon_detail_view, menu);
        return true;
    }

    public void loadMainView(String body){
        WebView v  = (WebView)findViewById(R.id.htmlLenderView);
        v.loadDataWithBaseURL(null, body, "text/html" , TEXT_ENCODING, null );
    }
}
