package com.triladroid.spycamera;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class aboutactivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        
        Button closebut = (Button) findViewById(R.id.button1);
        closebut.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		finish();
        	}
        	
        });
        
        
    }
}