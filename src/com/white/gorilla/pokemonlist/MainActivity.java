package com.white.gorilla.pokemonlist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.white.gorilla.pokemonlist.data.PokemonData;
import com.white.gorilla.pokemonlist.data.PokemonDataStorage;

import java.io.ByteArrayOutputStream;


public class MainActivity extends Activity {
    PokemonDataStorage storage;
    ListAdapter adapter;
    EditText searchInput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);


        storage = new PokemonDataStorage(this);
        adapter = new ListAdapter(this, R.layout.pokemon_list_icon, storage.get() );
        addItem();
        searchInput = (EditText)findViewById(R.id.searchInput);
        Button b = (Button)findViewById(R.id.searchButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filter = searchInput.getText().toString();
                ListAdapter adapter = new ListAdapter(MainActivity.this, R.layout.pokemon_list_icon, storage.search(filter));
                ListView v = (ListView) findViewById(R.id.pokemonList);
                v.setAdapter(adapter);
            }
        });

    }

    // オプションメニュー選択時に一度だけ呼ばれる
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_pokemon_list, menu);
        menu.add( Menu.NONE, ConstantData.MENU_ID_INFO, Menu.NONE, getString(R.string.info_menu_title));
        return true;
    }

    // オプションメニューアイテムが選択された時呼ばれる
    @Override
    public boolean onOptionsItemSelected( MenuItem item ){
    	switch( item.getItemId() ){
    	case ConstantData.MENU_ID_INFO:
    		Intent intent = new Intent( getApplicationContext(),InformationActivity.class );
    		startActivity( intent );
    		break;
    	}
    	return true;
    }

	private void addItem() {
		ListView v = (ListView)findViewById(R.id.pokemonList);
        v.setAdapter(adapter);
        v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView v = (TextView)view.findViewWithTag("text");
                Intent intent = new Intent(getApplicationContext(), DetailInfoActivity.class);
                PokemonData data = storage.get(v.getText().toString());

                intent.putExtra(ConstantData.SELECTED_ITEM_TITLE, data.getTitle());
                intent.putExtra(ConstantData.SELECTED_ITEM_BODY, data.getBody());

                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                data.getImage().compress(Bitmap.CompressFormat.PNG, 100, bout);
                byte[] binImage = bout.toByteArray();
                intent.putExtra(ConstantData.SELECTED_ITEM_IMAGE, binImage);

                startActivity(intent);
            }
        });
	}
}
