package com.grontol.mediacinta;

import java.util.ArrayList;
import java.util.List;

import com.grontol.lib.classes.QueryBuilder;
import com.grontol.lib.data.SQLiteDataManager;
import com.grontol.mediacinta.adapters.AdpSurat;
import com.grontol.mediacinta.items.ItemSurat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ActRawi extends Activity implements OnItemClickListener
{
	ListView lsvSurat;
	List<ItemSurat> lItems;
	AdpSurat adpSurat;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_rawi);
		
		lsvSurat = (ListView)findViewById(R.id.lsvSurat);
		lItems = new ArrayList<ItemSurat>();
		adpSurat = new AdpSurat(this, R.layout.item_surat, lItems);
		
		lsvSurat.setAdapter(adpSurat);
		lsvSurat.setOnItemClickListener(this);
		
		refreshList();
	}

	private void refreshList()
	{
		QueryBuilder q = new QueryBuilder("SELECT * FROM surat");
		
		String[][] res = SQLiteDataManager.read(this, q);
		
		lItems.clear();
		
		for (String[] s : res)
		{
			lItems.add(new ItemSurat(s));
		}
		
		adpSurat.notifyDataSetChanged();
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
	{
		ItemSurat it = lItems.get(arg2);
		
		Intent i = new Intent(this, ActPlay.class);
		
		i.putExtra("idSurat", it.getIdSurat());
		i.putExtra("namaSurat", it.getNamaSurat());
		
		startActivity(i);
	}
}
