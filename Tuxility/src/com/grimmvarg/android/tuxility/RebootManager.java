package com.grimmvarg.android.tuxility;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RebootManager extends Activity implements OnClickListener {
	private TuxHelper tuxHelper = TuxHelper.getInstance();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reboot);

		Button reboot = (Button) findViewById(R.id.normalReboot);
		Button recovery = (Button) findViewById(R.id.recoveryMode);
		Button download = (Button) findViewById(R.id.downloadMode);

		reboot.setOnClickListener(this);
		recovery.setOnClickListener(this);
		download.setOnClickListener(this);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.normalReboot:
			tuxHelper.showMessage("Normal Reboot");
			tuxHelper.reboot("");
			break;
		case R.id.recoveryMode:
			tuxHelper.showMessage("Recovery Mode");
			tuxHelper.reboot("recovery");
			break;
		case R.id.downloadMode:
			tuxHelper.showMessage("Download Mode");
			tuxHelper.reboot("download");
			break;
		}

	}

}
