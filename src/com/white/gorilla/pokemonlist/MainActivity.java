package com.white.gorilla.pokemonlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.white.gorilla.pokemonlist.data.PokemonDataStorage;

import java.sql.RowId;


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
                ListAdapter adapter = new ListAdapter(MainActivity.this, R.layout.pokemon_list_icon, storage.get(filter));
                ListView v = (ListView)findViewById(R.id.pokemonList);
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
                Intent intent = new Intent(getApplicationContext(), DetailInfoActivity.class);
                // TODO 0 origin ??
                intent.putExtra(ConstantData.SELECTED_ITEM_DATA, storage.get(position + 1));
                startActivity(intent);
            }
        });
	}
}
