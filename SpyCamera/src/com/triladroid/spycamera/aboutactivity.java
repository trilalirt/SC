package com.triladroid.spycamera;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class aboutactivity extends Activity {
    /** Called when the activity is first created. */
    
	//String desc = "<p style=\"text-align:justify\";  > <h3> Phone screen will be black after launch (because this is Spy Camera and everyone around should think that your phone is off :) </h3> </p> <ul> <li>Take photos by pressing volume up or volume down buttons </li> <li>To exit application press Back or Home button </li> <li>To enter Settings press Menu button </li> <li> Taken photos will be saved on the SD card at SpyCamera folder </li> </ul><p style=\"text-align:justify\"> <h4> Please, don’t downvote this app if something does not work, just write all your questions and suggestions to <a href=\"mailto:triladroid@gmail.com\">triladroid@gmail.com</a> and I’ll try to help with all your problems. </h4> </p>";	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        
        AdView ad = (AdView) findViewById(R.id.adView);
        ad.loadAd(new AdRequest());
        
        //TextView myText = (TextView) findViewById(R.id.textView1);
        //myText.setText(Html.fromHtml(getString(R.string.desc)));
        
        
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