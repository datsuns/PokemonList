package com.white.gorilla.pokemonlist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.AssetManager;

public class ListAccessor {
	public ListAccessor(Context context){
		accessor = context.getApplicationContext().getResources().getAssets();
		if( pokemonList.isEmpty() ){
			setList();
		}
	}

	private void setList() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(accessor.open("poke_list.html")));
			while( true ){
				String s = br.readLine();
				if( s == null ){
					break;
				}
				pokemonList.add(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getItem( Integer item_num ){
		return pokemonList.get(item_num);
	}

	public ArrayList<String> getTitles(){
		ArrayList<String> titles = new ArrayList<String>();
		for( String s : pokemonList){
			titles.add(s.substring(0, s.indexOf("<")));
		}
		return titles;
	}

	private AssetManager accessor;
	static private ArrayList<String> pokemonList = new ArrayList<String>();
}
