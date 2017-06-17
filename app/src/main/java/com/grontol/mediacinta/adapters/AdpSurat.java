package com.grontol.mediacinta.adapters;

import java.util.List;

import com.grontol.mediacinta.R;
import com.grontol.mediacinta.items.ItemSurat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdpSurat extends ArrayAdapter<ItemSurat>
{

	public AdpSurat(Context context, int resource, List<ItemSurat> objects)
	{
		super(context, resource, objects);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LinearLayout v = (LinearLayout)convertView;
		
		if (v == null)
		{
			LayoutInflater inflater = LayoutInflater.from(getContext());
			v = (LinearLayout)inflater.inflate(R.layout.item_surat, null);
		}
		
		ItemSurat it = getItem(position);
		
		if (it != null)
		{
			TextView txtSurat = (TextView)v.findViewById(R.id.txtSurat);
			
			if (txtSurat != null)
				txtSurat.setText(it.getNamaSurat());
		}
		
		return v;
	}
}
