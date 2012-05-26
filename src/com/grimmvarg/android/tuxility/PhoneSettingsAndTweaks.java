package com.grimmvarg.android.tuxility;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PhoneSettingsAndTweaks extends Activity implements OnClickListener {

	private TuxHelper tuxHelper = TuxHelper.getInstance();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phonesettingsandtweaks);

		Button clearBatteryStats = (Button) findViewById(R.id.clearBatteryStats);
		Button showGradient = (Button) findViewById(R.id.showGradient);
    	Button editSettings = (Button)findViewById(R.id.editSettings);
    	Button phoneCodes = (Button)findViewById(R.id.phoneCodes);

		clearBatteryStats.setOnClickListener(this);
		showGradient.setOnClickListener(this);
    	editSettings.setOnClickListener(this);
    	phoneCodes.setOnClickListener(this);
	}

	public void onClick(View view) {
		Intent nextIntent = new Intent(Intent.ACTION_VIEW);
		
		switch (view.getId()) {
		case R.id.clearBatteryStats:
			tuxHelper.showMessage("Clearing batterystats");
			tuxHelper.clearBatteryStats();
			break;
		case R.id.showGradient:
			tuxHelper.showMessage("Not done yet");
			break;
		case R.id.editSettings:
			tuxHelper.showMessage("Not done yet");
			break;
		case R.id.phoneCodes:
			nextIntent.setClassName(this, PhoneCodes.class.getName());
			startActivity(nextIntent);
			break;
		}

	}

}
