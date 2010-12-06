package com.grimmvarg.android.tuxility;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.RemoteViews.ActionException;

public class launch extends Activity implements OnClickListener {
    CLIHandler cliHandler = new CLIHandler();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	cliHandler.setup();
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void backupManagement(View view){
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
    
    public void kernelHacking(View view){
    	setContentView(R.layout.kernelhacking);
    	
    	Button backupKernel = (Button)findViewById(R.id.backupKernel);
    	Button installKernel = (Button)findViewById(R.id.installKernel);
    	Button latestVoodoo = (Button)findViewById(R.id.getLatestVoodoo);
    	
    	backupKernel.setOnClickListener(this);
    	installKernel.setOnClickListener(this);
    	latestVoodoo.setOnClickListener(this);
    }
    
    public void rebootManager(View view){
    	setContentView(R.layout.reboot);
    	
    	Button reboot = (Button)findViewById(R.id.normalReboot);
    	Button recovery = (Button)findViewById(R.id.recoveryMode);
    	Button download = (Button)findViewById(R.id.downloadMode);
    	
    	reboot.setOnClickListener(this);
    	recovery.setOnClickListener(this);
    	download.setOnClickListener(this);
    }
    
    public void showMessage(String message){
    	Context context = getApplicationContext();
    	CharSequence text = message;
    	int duration = Toast.LENGTH_SHORT;

    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
    }

	public void onClick(View view) {
		if(findViewById(view.getId()).equals(findViewById(R.id.backupSettingsDB))){
			showMessage("Backing up settings.db");
			cliHandler.backupSettingsDB();
		}
		else if (findViewById(view.getId()).equals(findViewById(R.id.restoreSettingsDB))) {
			showMessage("Restoring settings.db");
			cliHandler.restoreSettingsDB();
		}
		else if (findViewById(view.getId()).equals(findViewById(R.id.backupEFS))) {
			showMessage("Backing up /efs");
			cliHandler.backupEFS();
		}
		else if (findViewById(view.getId()).equals(findViewById(R.id.restoreEFS))) {
			showMessage("Restoring /efs");
			cliHandler.restoreEFS();
		}
		else if (findViewById(view.getId()).equals(findViewById(R.id.backupKernel))) {
			showMessage("Backing up kernel");
			cliHandler.backupKernel();
		}
		else if (findViewById(view.getId()).equals(findViewById(R.id.installKernel))) {
			showMessage("Installing kernel");
			cliHandler.installKernel("");
		}
		else if (findViewById(view.getId()).equals(findViewById(R.id.getLatestVoodoo))) {
			showMessage("Fetching voodoo snapshot");
			cliHandler.getLatestVoodoo();
		}
		else if (findViewById(view.getId()).equals(findViewById(R.id.normalReboot))) {
			showMessage("Normal Reboot");
			cliHandler.reboot("");
		}
		else if (findViewById(view.getId()).equals(findViewById(R.id.recoveryMode))) {
			showMessage("Recovery Mode");
			cliHandler.reboot("recovery");
		}
		else if (findViewById(view.getId()).equals(findViewById(R.id.downloadMode))) {
			showMessage("Download Mode");
			cliHandler.reboot("download");
		}
		showMessage("DONE!");
		
	}
    
    
}