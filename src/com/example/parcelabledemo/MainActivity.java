package com.example.parcelabledemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	private ArrayList<GetterSetter> list = new ArrayList<GetterSetter>();
	private LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
	public static final String extra = "extra";
	public static final String mymapExtra = "mymapExtra";
	public static final String bundleExtra = "bundleExtra";
	public static final String bitmapExtra = "bitmapExtra";
	public static final String drawableExtra = "drawableExtra";
	public static final String intArrayExtra = "intArrayExtra";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        fillArrayList();
        fillHashMap();
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getDataForListView());
        setListAdapter(adapter);
    }
    
    private ArrayList<String> getDataForListView() {
    	
    	Log.d("Map keySet",map.keySet().toString());
    	Log.d("Map Values", map.values().toString());
    	
    	ArrayList<String> arrayList = new ArrayList<String>(map.keySet());
    	
		return arrayList;
	}
    
    private void fillArrayList() {
    	list.add(new GetterSetter("First", "Password 1"));
    	list.add(new GetterSetter("Second", "Password 2"));
    	list.add(new GetterSetter("Third", "Password 3"));
    	list.add(new GetterSetter("Fourth", "Password 4"));
    	list.add(new GetterSetter("Fifth", "Password 5"));
    	
    	sortList(list);
	}
    
    private void sortList(ArrayList<GetterSetter> list) {
    	Collections.sort(list, comparator);
    	for(GetterSetter s : list){
    		Log.v("Sorted List",s.getName());
    	}
	}
    
    Comparator<GetterSetter> comparator = new Comparator<GetterSetter>() {
		
		public int compare(GetterSetter lhs, GetterSetter rhs) {
			return lhs.getName().compareToIgnoreCase(rhs.getName());
		}
	};
    
    private void fillHashMap() {
    	map.put("Object", 1);
    	map.put("ArrayList", 2);
    	map.put("HashMap", 3);
    	map.put("Bitmap", 4);
    	map.put("Drawable", 5);
	}
    
    private Bitmap getBimtap() {
    	Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		return bitmap;
	}
    
    private Drawable getDrawable() {
    	Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher);
		return drawable;
    }
    
    private int[] getIntegerArray() {
    	int[] intArray = new int[2];
    	intArray[0] = 1;
    	intArray[1] = 2;
		return intArray;
    }
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String selected_value = l.getAdapter().getItem(position).toString();
		
		MyParcelable myParcelable = new MyParcelable();
		myParcelable.setSelected(selected_value);
		myParcelable.setList(list);
		
		GetterSetter setter = new GetterSetter("Lalit", "Poptani");
		
		Bundle bundle = new Bundle();
		bundle.putSerializable(mymapExtra, map);
		bundle.putParcelable(bitmapExtra, getBimtap());
		bundle.putParcelable(drawableExtra, ((BitmapDrawable)getDrawable()).getBitmap());
		Intent intent = new Intent(v.getContext(), ReadDataActivity.class);
		intent.putExtra(extra, myParcelable);
		intent.putExtra("setter", setter);
		bundle.putIntArray(intArrayExtra, getIntegerArray());
		intent.putExtra(bundleExtra, bundle);
		startActivity(intent);
	}
}
