package com.white.gorilla.pokemonlist;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.white.gorilla.pokemonlist.data.PokemonData;
/**
 * Created with IntelliJ IDEA.
 * User: satoshi
 * Date: 13/03/18
 * Time: 23:16
 * To change this template use File | Settings | File Templates.
 */
/**
 * アイコンとテキストを表示するためのアダプタ.
 */
public class ListAdapter extends ArrayAdapter<PokemonData> {
	/** XMLからViewを生成するのに使うヤツ. */
	private LayoutInflater inflater;
	/** リストアイテムのレイアウト. */
	private int textViewResourceId;
	/** 表示するアイテム. */
	private List<PokemonData> items;

	/**
	 * コンストラクタ.
	 */
	public ListAdapter(Context context, int textViewResourceId, List<PokemonData> items){
		super(context, textViewResourceId, items);
		// リソースIDと表示アイテムを保持っておく
		this.textViewResourceId = textViewResourceId;
		this.items = items;
		// ContextからLayout inflatorを取得
		inflater = (LayoutInflater)context.getSystemService(
				Context.LAYOUT_INFLATER_SERVICE
		);
	}

	/**
	 * 1アイテム分のビューを取得.
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View view;
		// convertViewなんか入ってたら、それを使う
		if(convertView != null){
			view = convertView;
		}
		else{
			view = inflater.inflate(textViewResourceId, null);
		}
		// 対象のアイテムを取得
		PokemonData item = items.get(position);
		// アイコンにアレを設定
		ImageView imageView = (ImageView)view.findViewWithTag("icon");
        imageView.setImageBitmap(item.getImage());
        TextView textView = (TextView)view.findViewWithTag("text");
        textView.setText(item.getTitle());
		return view;
	}
}
