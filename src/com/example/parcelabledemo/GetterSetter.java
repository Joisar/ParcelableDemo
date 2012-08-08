package com.example.parcelabledemo;

import android.os.Parcel;
import android.os.Parcelable;

public class GetterSetter implements Parcelable{

	private String name;
	private String password;
	
	public GetterSetter(String name, String password){
		this.name = name;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
	
	public GetterSetter() {}
	
	public GetterSetter(Parcel parcel){
		name = parcel.readString();
		password = parcel.readString();
	}
	
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(password);
	}
	
	public static final Creator<GetterSetter> CREATOR = new Creator<GetterSetter>() {
		
		public GetterSetter[] newArray(int size) {
			return new GetterSetter[size];
		}
		
		public GetterSetter createFromParcel(Parcel source) {
			return new GetterSetter(source);
		}
	};
}
