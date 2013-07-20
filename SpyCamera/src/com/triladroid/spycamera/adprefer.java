package com.triladroid.spycamera;

import android.app.Activity;
import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.google.ads.AdView;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
 
public class adprefer extends Preference {
	
	public adprefer(Context context, AttributeSet attrs, int defStyle) {super    (context, attrs, defStyle);}
    public adprefer(Context context, AttributeSet attrs) {super(context, attrs);}
    public adprefer(Context context) {super(context);}
	
	@Override
        protected View onCreateView(ViewGroup parent) {
                
		View view = super.onCreateView(parent);

        // the context is a PreferenceActivity
        Activity activity = (Activity)getContext();

        // Create the adView
        AdView adView = new AdView(activity, AdSize.BANNER, "a151ea6c135b761");
                // Get the custom preferencen                a151ea6c135b761  
                //ok
                ((LinearLayout)view).addView(adView);

                // Initiate a generic request to load it with an ad
                AdRequest request = new AdRequest();
                adView.loadAd(request);     

                return view;    
            }
        }