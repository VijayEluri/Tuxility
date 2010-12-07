package com.grimmvarg.android.tuxility;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CalibrationTools extends Activity implements OnClickListener {

	private TuxHelper tuxHelper = TuxHelper.getInstance();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calibrationtools);

		Button clearBatteryStats = (Button) findViewById(R.id.clearBatteryStats);
		Button showGradient = (Button) findViewById(R.id.showGradient);

		clearBatteryStats.setOnClickListener(this);
		showGradient.setOnClickListener(this);
	}

	public void onClick(View view) {
		if (view.getId()== R.id.clearBatteryStats) {
			tuxHelper.showMessage("Clearing batterystats");
			tuxHelper.clearBatteryStats();
		} else if (view.getId()== R.id.showGradient) {
			tuxHelper.showMessage("Not done yet");
		}

	}

}
