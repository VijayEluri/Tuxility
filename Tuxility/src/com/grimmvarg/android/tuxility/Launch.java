package com.grimmvarg.android.tuxility;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.RemoteViews.ActionException;

public class Launch extends Activity implements OnClickListener {
    TuxHelper tuxHelper;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	TuxHelper.getInstance(getApplicationContext());
        setContentView(R.layout.main);
        
    	Button backupManagement = (Button)findViewById(R.id.backupManagement);
    	Button kernelHacking = (Button)findViewById(R.id.kernelHacking);
    	Button rebootManager = (Button)findViewById(R.id.rebootManager);
    	Button editSettings = (Button)findViewById(R.id.editSettings);
    	Button cheatCodes = (Button)findViewById(R.id.cheatCodes);
    	Button calibrationTools = (Button)findViewById(R.id.calibrationTools);
    	
    	
    	backupManagement.setOnClickListener(this);
    	kernelHacking.setOnClickListener(this);
    	rebootManager.setOnClickListener(this);
    	editSettings.setOnClickListener(this);
    	cheatCodes.setOnClickListener(this);
    	calibrationTools.setOnClickListener(this);
    }

    
    public void showMessage(String message){
    	Context context = getApplicationContext();
    	CharSequence text = message;
    	int duration = Toast.LENGTH_SHORT;

    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
    }

	public void onClick(View view) {
		Intent nextIntent = new Intent(Intent.ACTION_VIEW);
		Boolean hack = true;
		
		switch (view.getId()) {
		case R.id.backupManagement:
			nextIntent.setClassName(this, BackupManagement.class.getName());
			break;
		case R.id.kernelHacking:
			nextIntent.setClassName(this, KernelHacking.class.getName());
			break;
		case R.id.rebootManager:
			nextIntent.setClassName(this, RebootManager.class.getName());
			break;
		case R.id.editSettings:
//			nextIntent.setClassName(this, EditSettings.class.getName());
			showMessage("not yet done");
			hack = false;
			break;
		case R.id.cheatCodes:
//			nextIntent.setClassName(this, CheatCodes.class.getName());
			showMessage("not yet done");
			hack = false;
			break;
		case R.id.calibrationTools:
			nextIntent.setClassName(this, CalibrationTools.class.getName());
			break;
		}
		
		if(hack) startActivity(nextIntent);
		
	}
	
	public void notDone(){
		tuxHelper.showMessage("Not yet done");
	}
    
    
}