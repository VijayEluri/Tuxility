package com.grimmvarg.android.tuxility;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RebootManager extends Activity implements OnClickListener {
	private CLIHandler cliHandler = CLIHandler.getInstance();

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
		if (findViewById(view.getId()).equals(findViewById(R.id.normalReboot))) {
			cliHandler.showMessage("Normal Reboot");
			cliHandler.reboot("");
		} else if (findViewById(view.getId()).equals(findViewById(R.id.recoveryMode))) {
			cliHandler.showMessage("Recovery Mode");
			cliHandler.reboot("recovery");
		} else if (findViewById(view.getId()).equals(findViewById(R.id.downloadMode))) {
			cliHandler.showMessage("Download Mode");
			cliHandler.reboot("download");
		}

	}

}
