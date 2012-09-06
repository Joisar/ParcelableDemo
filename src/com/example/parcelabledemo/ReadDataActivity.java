package com.example.parcelabledemo;

import java.util.HashMap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class ReadDataActivity extends Activity{

	ImageView ImageViewBitmap, ImageViewDrawable;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.readdata);
		
		initComponents();

		// only for parcelable
		Bundle bundleParcelable = getIntent().getExtras();
		MyParcelable myParcelable = bundleParcelable.getParcelable(MainActivity.extra);
		String valueOfSelecteditem = myParcelable.getSelected();
		Log.v("value", valueOfSelecteditem);
		
		String[][] strArray = myParcelable.getStrArray();
		for (int i = 0; i < strArray.length; i++) {
			for (int j = 0; j < strArray.length; j++) {
				Log.d("String Array", strArray[i][j]);
			}
		}

		// for other Parcelables.
		Bundle bundleMapAndBitmap = getIntent().getBundleExtra(MainActivity.bundleExtra);
		
		@SuppressWarnings("unchecked")
		HashMap<String, Integer> map = (HashMap<String, Integer>) bundleMapAndBitmap.getSerializable(MainActivity.mymapExtra);
		int position = map.get(valueOfSelecteditem);
		
		switch (position) {
		case 1:
			ReadObject(bundleParcelable);
			break;
		case 2:
			readArrayList(myParcelable);
			break;
		case 3:
			readHashMap(bundleMapAndBitmap);
			break;
		case 4:
			readBitmap(bundleMapAndBitmap);
			break;
		case 5:
			readDrawable(bundleMapAndBitmap);
			break;
		}
	} 
	
	private void initComponents() {
		ImageViewBitmap = (ImageView) findViewById(R.id.ImageViewBitmap);
		ImageViewDrawable = (ImageView) findViewById(R.id.ImageViewDrawable);
	}
	
	private void setImageViewBitmap(Bitmap bitmap) {
		ImageViewBitmap.setImageBitmap(bitmap);
	}
	
	private void setImageViewDrawable(Drawable drawable) {
		ImageViewDrawable.setImageDrawable(drawable);
	}
	
	private void ReadObject(Bundle bundle) {
		GetterSetter setter = bundle.getParcelable("setter");
		Log.d("Object", setter.getName()+" "+setter.getPassword());
	}
	
	private void readArrayList(MyParcelable myParcelable) {
		for(GetterSetter gs : myParcelable.getList()){
			Log.d("List", gs.getName()+" "+gs.getPassword());
		}
	}
	
	private void readHashMap(Bundle bundle) {
		@SuppressWarnings("unchecked")
		HashMap<String, Integer> map = (HashMap<String, Integer>) bundle.getSerializable(MainActivity.mymapExtra);
    	Log.d("Map keySet",map.keySet().toString());
    	Log.d("Map Values", map.values().toString());
	}
	
	private void readBitmap(Bundle bundle) {
		Bitmap bitmap = bundle.getParcelable(MainActivity.bitmapExtra);
		setImageViewBitmap(bitmap);
	}
	
	private void readDrawable(Bundle bundle) {
		Drawable drawable = new BitmapDrawable(getResources(), (Bitmap)bundle.getParcelable(MainActivity.drawableExtra));
		setImageViewDrawable(drawable);
	}
}
