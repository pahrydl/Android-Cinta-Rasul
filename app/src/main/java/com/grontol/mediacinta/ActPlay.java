package com.grontol.mediacinta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.grontol.lib.classes.QueryBuilder;
import com.grontol.lib.data.SQLiteDataManager;
import com.grontol.lib.utility.Convert;
import com.grontol.mediacinta.adapters.AdpAyatPager;
import com.grontol.mediacinta.items.ItemAyat;
import com.grontol.mediacinta.pager.WrapPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class ActPlay extends FragmentActivity implements OnClickListener, OnPageChangeListener, OnCompletionListener
{
	FrameLayout vAyat;
	
	TextView txtTitle;
	
	List<ItemAyat> lItems;
	WrapPager pager;
	AdpAyatPager adpAyatPager;
	
	ImageView imgPrev;
	ImageView imgPlay;
	ImageView imgNext;
	
	int curPage = 0;
	int curSurat = 0;
	
	MediaPlayer player;
	
	boolean isPaused = true;
	int curDur;
	
	SharedPreferences prefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_play);
		
		vAyat = (FrameLayout)findViewById(R.id.vAyat);
		
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		
		imgPrev = (ImageView)findViewById(R.id.imgPrev);
		imgPlay = (ImageView)findViewById(R.id.imgPlay);
		imgNext = (ImageView)findViewById(R.id.imgNext);
		
		imgPrev.setOnClickListener(this);
		imgPlay.setOnClickListener(this);
		imgNext.setOnClickListener(this);
		
		player = new MediaPlayer();
		player.setOnCompletionListener(this);
		player.setLooping(false);
		
		prefs = getSharedPreferences("com.grontol.mediacinta.setting", Context.MODE_PRIVATE);
		
		curSurat = Convert.getInt(getIntent().getStringExtra("idSurat"));
		
		setupVisibility();
		setupAll();
	}

	private void setupVisibility()
	{
		if (!prefs.getBoolean("ayat", true))
		{
			vAyat.setVisibility(View.GONE);
		}
	}
	
	private void setupAll()
	{
		lItems = new ArrayList<ItemAyat>();
		
		QueryBuilder q = new QueryBuilder("SELECT id_ayat, surat.id_surat, nama_surat, ayat, terjemah, ejaan, audio FROM ayat");
		q.append("JOIN surat ON surat.id_surat = ayat.id_surat");
		String[][] res = SQLiteDataManager.read(this, q);
		
		lItems.clear();
		
		for (String[] s : res)
		{
			lItems.add(new ItemAyat(s));
		}
		
		QueryBuilder q2 = new QueryBuilder("SELECT id_ayat FROM ayat WHERE id_surat = {0}", getIntent().getStringExtra("idSurat"));
		String sPage[][] = SQLiteDataManager.read(this, q2);
		curPage = Convert.getInt(sPage[0][0]) - 1;
		
		pager = (WrapPager)findViewById(R.id.viewPager);
		adpAyatPager = new AdpAyatPager(getSupportFragmentManager(), lItems, prefs);
		pager.setAdapter(adpAyatPager);
		pager.setOnPageChangeListener(this);
		
		if (curPage == 0)
			onPageSelected(0);
		else
			pager.setCurrentItem(curPage);
		
		pause();
	}
	
	private void setupPage(int pIndex)
	{
		ItemAyat it = lItems.get(pIndex);
		
		txtTitle.setText(it.getNamaSurat());
		
		try
		{
			player.reset();
			curDur = 0;
			
			AssetFileDescriptor afd = getAssets().openFd("sounds/" + it.getAudio());
			player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
			player.prepare();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	private void pause()
	{
		isPaused = true;
		
		if (player.isPlaying())
		{
			player.pause();
			curDur = player.getCurrentPosition();
		}
		
		imgPlay.setImageDrawable(getResources().getDrawable(R.drawable.play_draw));
	}
	
	private void resume()
	{
		isPaused = false;
		
		player.seekTo(curDur);
		player.start();
		
		imgPlay.setImageDrawable(getResources().getDrawable(R.drawable.pause_draw));
	}
	
	@Override
	public void onClick(View v)
	{
		if (v == imgPrev)
		{
			if (curPage > 0)
				pager.setCurrentItem(--curPage, true);
		}
		else if (v == imgPlay)
		{
			if (isPaused)
			{
				resume();
			}
			else
			{
				pause();
			}
		}
		else if (v == imgNext)
		{
			if (curPage < lItems.size() - 1)
				pager.setCurrentItem(++curPage, true);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0)
	{
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2)
	{
	}

	@Override
	public void onPageSelected(int pIndex)
	{
		curPage = pIndex;
		setupPage(pIndex);
		
		if (!isPaused)
			player.start();
		
		pager.reMeasure(adpAyatPager.getItem(pIndex).getView());
	}

	@Override
	public void onCompletion(MediaPlayer mp)
	{
		if (curPage < lItems.size() - 1)
			pager.setCurrentItem(++curPage, true);
		
	}
	
	@Override
	protected void onDestroy()
	{
		player.release();
		
		super.onDestroy();
	}
}
