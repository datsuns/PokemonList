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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainActivity extends Activity {

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
    		Intent intent = new Intent( getApplicationContext(),InformationActivity.class );
    		startActivity( intent );
    		break;
    	}
    	return true;
    }

	private void addItem() {
		ListAccessor accessor = new ListAccessor(this);
		ListView v = (ListView)findViewById(R.id.pokemonList);
        List<ListItem> items = new ArrayList<ListItem>();

        ListAdapter new_adapter = new ListAdapter(this, R.layout.pokemon_list_icon, items );
        int i = 1;
        for( String title : accessor.getTitles() ){
            items.add(new ListItem(i++, title) );
        }
        v.setAdapter(new_adapter);
        v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent( getApplicationContext(), DetailInfoActivity.class );
                intent.putExtra( ConstantData.INTENT_FILTER_SELECTED_ITEM , position );
                startActivity( intent );
            }
        });
	}
}
