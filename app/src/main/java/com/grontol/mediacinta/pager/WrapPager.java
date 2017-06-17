package com.grontol.mediacinta.pager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class WrapPager extends ViewPager
{
	View v;
	
	public WrapPager(Context context)
	{
		super(context);
	}
	
	public WrapPager(Context context, AttributeSet attrs) 
	{
        super(context, attrs);
    }
	
	@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
        try 
        {
        	View child = v;
	        
        	if (child == null && getChildCount() > 0)
        		child = getChildAt(0);
        	
            if (child != null) 
            {
                child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                int h = child.getMeasuredHeight();
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY);
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
	
	public void reMeasure(View v)
	{
		this.v = v;
	}
}
