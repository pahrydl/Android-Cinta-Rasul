package com.grontol.mediacinta;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class ActSetting extends Activity implements OnCheckedChangeListener
{
	CheckBox ckbAyat;
	CheckBox ckbEjaan;
	CheckBox ckbTerjemah;
	
	SharedPreferences prefs;
	SharedPreferences.Editor prefEd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_setting);
		
		prefs = getSharedPreferences("com.grontol.mediacinta.setting", Context.MODE_PRIVATE);
		prefEd = prefs.edit();
		
		ckbAyat = (CheckBox)findViewById(R.id.ckbAyat);
		ckbEjaan = (CheckBox)findViewById(R.id.ckbEjaan);
		ckbTerjemah = (CheckBox)findViewById(R.id.ckbTerjemah);
		
		ckbAyat.setOnCheckedChangeListener(this);
		ckbEjaan.setOnCheckedChangeListener(this);
		ckbTerjemah.setOnCheckedChangeListener(this);
		
		initPrefs();
	}

	private void initPrefs()
	{
		ckbAyat.setChecked(prefs.getBoolean("ayat", true));
		ckbEjaan.setChecked(prefs.getBoolean("ejaan", true));
		ckbTerjemah.setChecked(prefs.getBoolean("terjemah", true));
	}
	
	@Override
	public void onCheckedChanged(CompoundButton v, boolean b)
	{
		if (v == ckbAyat)
		{
			prefEd.putBoolean("ayat", b);
			prefEd.commit();
		}
		else if (v == ckbEjaan)
		{
			prefEd.putBoolean("ejaan", b);
			prefEd.commit();
		}
		else if (v == ckbTerjemah)
		{
			prefEd.putBoolean("terjemah", b);
			prefEd.commit();
		}
	}
}
