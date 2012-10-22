package com.white.gorilla.pokemonlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PokemonListActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);
        addItem();
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
    		Intent intent = new Intent( getApplicationContext(),PokemonListInfoActivity.class );
    		startActivity( intent );
    		break;
    	}
    	return true;
    }

	private void addItem() {
		PokemonListAccessor accessor = new PokemonListAccessor(this);
		ListView v = (ListView)findViewById(R.id.pokemonList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 );
        for( String title : accessor.getTitles() ){
        	adapter.add( title );
        }
        v.setAdapter(adapter);
        v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent( getApplicationContext(), PokemonDetailView.class );
                intent.putExtra( ConstantData.INTENT_FILTER_SELECTED_ITEM , position );
                startActivity( intent );
            }
        });
	}
}
