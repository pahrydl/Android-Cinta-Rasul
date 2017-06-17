package com.grontol.mediacinta;

import com.grontol.lib.data.SQLiteDataManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class ActSplash extends Activity 
{
	ProgressBar progressBar;
	int progress = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_splash);
		
		progressBar = (ProgressBar)findViewById(R.id.progressBar);
		
		SQLiteDataManager.setDbName("mediacinta.db");
		
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				progress++;
				
				if (progress < 100)
				{
					handler.postDelayed(this, 20);
					progressBar.setProgress(progress);
				}
				else
				{
					Intent i = new Intent(ActSplash.this, ActMenu.class);
					startActivity(i);
					
					finish();
				}
			}
		}, 20);
	}
}
