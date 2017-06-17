package com.grontol.mediacinta.adapters;

import java.util.List;

import com.grontol.mediacinta.fragments.FrgAyat;
import com.grontol.mediacinta.items.ItemAyat;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class AdpAyatPager extends FragmentPagerAdapter
{
	List<ItemAyat> lItems;
	FrgAyat[] frg;
	
	SharedPreferences prefs;
	
	public AdpAyatPager(FragmentManager fm, List<ItemAyat> lItems, SharedPreferences prefs)
	{
		super(fm);
		this.lItems = lItems;
		this.frg = new FrgAyat[lItems.size()];
		this.prefs = prefs;
	}

	@Override
	public Fragment getItem(int pos)
	{
		if (frg[pos] == null)
			frg[pos] = new FrgAyat(lItems.get(pos), prefs);
		
		return frg[pos];
	}

	@Override
	public int getCount()
	{
		return lItems.size();
	}

}
