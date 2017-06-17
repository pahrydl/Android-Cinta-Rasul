package com.grontol.mediacinta.fragments;

import com.grontol.lib.utility.Imaging;
import com.grontol.mediacinta.R;
import com.grontol.mediacinta.items.ItemAyat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FrgAyat extends Fragment
{
	View view;
	ItemAyat it;
	
	SharedPreferences prefs;
	
	public FrgAyat(ItemAyat it, SharedPreferences prefs)
	{
		this.it = it;
		this.prefs = prefs;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		view = inflater.inflate(R.layout.page_ayat, container, false);
		ImageView img = (ImageView)view.findViewById(R.id.imgAyat);
		TextView txtEjaan = (TextView)view.findViewById(R.id.txtEjaan);
		TextView txtArti = (TextView)view.findViewById(R.id.txtArti);
		
		if (!prefs.getBoolean("ejaan", true))
		{
			txtEjaan.setVisibility(View.GONE);
		}
		
		if (!prefs.getBoolean("terjemah", true))
		{
			txtArti.setVisibility(View.GONE);
		}
		
		if (it != null)
		{
			img.setImageBitmap(Imaging.getBitmapFromAsset(getActivity(), "images/" + it.getAyat()));
			txtEjaan.setText(it.getEjaan());
			txtArti.setText(it.getTerjemah());
		}
		
		return view;
	}
}
