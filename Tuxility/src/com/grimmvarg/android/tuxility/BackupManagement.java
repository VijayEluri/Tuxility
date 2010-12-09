package com.grimmvarg.android.tuxility;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BackupManagement extends Activity implements OnClickListener {
	private TuxHelper tuxHelper = TuxHelper.getInstance();

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
    	setContentView(R.layout.backupmanagement);
    	
    	Button backupSettingsDB = (Button)findViewById(R.id.backupSettingsDB);
    	Button restoreSettingsDB = (Button)findViewById(R.id.restoreSettingsDB);
    	Button backupEFS = (Button)findViewById(R.id.backupEFS);
    	Button restoreEFS = (Button)findViewById(R.id.restoreEFS);
    	
    	backupSettingsDB.setOnClickListener(this);
    	restoreSettingsDB.setOnClickListener(this);
    	backupEFS.setOnClickListener(this);
    	restoreEFS.setOnClickListener(this);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.backupSettingsDB:
			tuxHelper.backupSettingsDB();
			break;
		case R.id.restoreSettingsDB:
			tuxHelper.restoreSettingsDB();
			break;
		case R.id.backupEFS:
			tuxHelper.backupEFS();
			break;
		case R.id.restoreEFS:
			tuxHelper.restoreEFS();
			break;
		}
		
	}

}
