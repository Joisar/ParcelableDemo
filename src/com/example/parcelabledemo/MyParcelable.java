package com.example.parcelabledemo;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class MyParcelable implements Parcelable{

	private String strArray[][];
	private String selected;
	private List<GetterSetter> list = new ArrayList<GetterSetter>();
	
	public String[][] getStrArray() {
		return strArray;
	}

	public void setStrArray(String[][] strArray) {
		this.strArray = strArray;
	}
	
	public List<GetterSetter> getList() {
		return list;
	}

	public void setList(List<GetterSetter> list) {
		this.list = list;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public MyParcelable(String[][] strArray) {
		this.strArray = strArray;
	}
	
	public MyParcelable(Parcel parcel) {
		selected = parcel.readString();
		parcel.readTypedList(list, GetterSetter.CREATOR);
		strArray = (String[][]) parcel.readSerializable();
	}
	
	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(selected);
		dest.writeTypedList(list);
		dest.writeSerializable(strArray);
	}
	
	public static final Creator<MyParcelable> CREATOR = new Creator<MyParcelable>() {
		
		public MyParcelable[] newArray(int size) {
			return new MyParcelable[size];
		}
		
		public MyParcelable createFromParcel(Parcel source) {
			return new MyParcelable(source);
		}
	};
}
