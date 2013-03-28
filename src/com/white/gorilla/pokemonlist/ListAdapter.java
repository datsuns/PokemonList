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
public class ListAdapter extends ArrayAdapter<ListItem> {
	/** XMLからViewを生成するのに使うヤツ. */
	private LayoutInflater inflater;
	/** リストアイテムのレイアウト. */
	private int textViewResourceId;
	/** 表示するアイテム. */
	private List<ListItem> items;
    private Context context;

	/**
	 * コンストラクタ.
	 */
	public ListAdapter(Context context, int textViewResourceId, List<ListItem> items){
		super(context, textViewResourceId, items);
		// リソースIDと表示アイテムを保持っておく
		this.textViewResourceId = textViewResourceId;
		this.items = items;
        this.context = context;
		// ContextからLayoutinflatorを取得
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
		ListItem item = items.get(position);

		// アイコンにアレを設定
		ImageView imageView = (ImageView)view.findViewWithTag("icon");
        InputStream s = null;
        try {
            s = openIconStream(item.getIconNumber());
            Bitmap bitmap = BitmapFactory.decodeStream(s);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        TextView textView = (TextView)view.findViewWithTag("text");
        textView.setText(item.getText());

		return view;
	}

    public InputStream openIconStream( int iconNumber ) throws IOException {
        String name = "icon/" + iconNumber + ".gif";
        try {
            InputStream s = this.context.getResources().getAssets().open(name);
            Logger.log("position:" + iconNumber + "icon:" + name);
            return s;
        } catch (IOException e) {
            InputStream s = this.context.getResources().getAssets().open("icon/icon.png");
            Logger.log("load default icon to " + iconNumber);
            return s;
        }
    }

}
