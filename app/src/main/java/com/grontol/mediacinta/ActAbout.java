package com.grontol.mediacinta;

import com.grontol.lib.views.MyWebView;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class ActAbout extends Activity
{
	MyWebView myWebView;
	String about = "Tentang Aplikasi :\n\nAplikasi ini adalah aplikasi yang dibuat untuk menambah kecintaan kita terhadap rosul. Di dalam aplikasi ini terdapat beberapa surat dan sholawat yang patut untuk kita dengar dan hafalkan.";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_about);
		
		myWebView = new MyWebView((WebView)findViewById(R.id.wbvAbout), this);
		myWebView.setText(about);
	}
}
