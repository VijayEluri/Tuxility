package com.grimmvarg.android.tuxility;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;

public class SettingsEditor  extends Activity implements OnClickListener  {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		
		Settings settings = new Settings();

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
