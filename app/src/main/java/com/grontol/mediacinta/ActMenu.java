package com.grontol.mediacinta;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActMenu extends Activity implements OnClickListener
{
	Button btnRawi;
	Button btnSetting;
	Button btnAbout;
	Button btnExit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_menu);
		
		btnRawi = (Button)findViewById(R.id.btnRawi);
		btnSetting = (Button)findViewById(R.id.btnSetting);
		btnAbout = (Button)findViewById(R.id.btnAbout);
		btnExit = (Button)findViewById(R.id.btnExit);
		
		btnRawi.setOnClickListener(this);
		btnSetting.setOnClickListener(this);
		btnAbout.setOnClickListener(this);
		btnExit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		if (v == btnRawi)
		{
			Intent i = new Intent(this, ActRawi.class);
			startActivity(i);
		}
		else if (v == btnSetting)
		{
			Intent i = new Intent(this, ActSetting.class);
			startActivity(i);
		}
		else if (v == btnAbout)
		{
			Intent i = new Intent(this, ActAbout.class);
			startActivity(i);
		}
		else if (v == btnExit)
		{
			alertClose();
		}
	}
	
	private void alertClose()
	{
		new AlertDialog.Builder(this)
		.setTitle("Konfirmasi Keluar")
		.setMessage("Apakah anda ingin keluar?")
		.setPositiveButton("Ya", new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface arg0, int arg1)
			{
				finish();
			}
		})
		.setNegativeButton("Tidak", null)
		.show();
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			alertClose();
			return true;
		}
		else
			return super.onKeyUp(keyCode, event);
	}
}
