package com.triladroid.spycamera;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.Preference.OnPreferenceClickListener;
import android.widget.Toast;
 
public class prefer extends PreferenceActivity {
	   
	
	@Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                addPreferencesFromResource(R.xml.preferences);
                // Get the custom preference
                //ok
        
                Preference customPref = (Preference) findPreference("help");
                customPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {

                                        public boolean onPreferenceClick(Preference preference) {
                                        	
                                        	Intent myIntent = new Intent(getApplicationContext(), aboutactivity.class);
                                            startActivity(myIntent);
                                            return true;
                                        }

                                });                                 
        
                
        }
}