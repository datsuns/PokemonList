package com.white.gorilla.pokemonlist;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class DetailInfoActivity extends Activity {
	String TEXT_ENCODING = "UTF-8";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail_view);
        ListAccessor accessor = new ListAccessor(this);
        int num = getIntent().getExtras().getInt(ConstantData.SELECTED_ITEM_NUMBER);
        loadTitleView(num, accessor);
        loadMainView(num, accessor);
    }

    private void loadTitleView(int num, ListAccessor accessor) {
        ImageView image = (ImageView)findViewById(R.id.detailviewimage);
        try {
            // TODO 1オリジン？
            InputStream s = openIconStream(num + 1);
            Bitmap bitmap = BitmapFactory.decodeStream(s);
            image.setImageBitmap(bitmap);
            s.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        TextView text = (TextView)findViewById(R.id.detailViewText);
        text.setText(accessor.getTitles().get(num));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_pokemon_detail_view, menu);
        return true;
    }

    public void loadMainView(int num, ListAccessor accessor){
        WebView v  = (WebView)findViewById(R.id.htmlLenderView);
        v.loadDataWithBaseURL(null, accessor.getItem(num), "text/html" , TEXT_ENCODING, null );
    }

    public InputStream openIconStream( int iconNumber ) throws IOException {
        String name = "icon/" + String.format("%03d", iconNumber) + ".gif";
        try {
            InputStream s = this.getResources().getAssets().open(name);
            Logger.log("position:" + iconNumber + "icon:" + name);
            return s;
        } catch (IOException e) {
            InputStream s = this.getResources().getAssets().open("icon/icon.png");
            Logger.log("load default icon to " + iconNumber);
            return s;
        }
    }
}
