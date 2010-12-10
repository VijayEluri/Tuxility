package com.grimmvarg.android.tuxility;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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
    	Button phoneSettings = (Button)findViewById(R.id.phonesettingsandtweaks);
    	
    	
    	backupManagement.setOnClickListener(this);
    	kernelHacking.setOnClickListener(this);
    	rebootManager.setOnClickListener(this);
    	phoneSettings.setOnClickListener(this);
    }

	public void onClick(View view) {
		Intent nextIntent = new Intent(Intent.ACTION_VIEW);
		
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
		case R.id.phonesettingsandtweaks:
			nextIntent.setClassName(this, PhoneSettingsAndTweaks.class.getName());
			break;
//		case R.id.support:
//			//nextIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://forum.xda-developers.com/showthread.php?t=864001"));
//			nextIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			nextIntent.setData(android.net.Uri.parse("http://forum.xda-developers.com/showthread.php?t=864001"));
//			break;
//		case R.id.donate:
//			//nextIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://forum.xda-developers.com/donatetome.php?u=3064407"));
//			nextIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			nextIntent.setData(android.net.Uri.parse("http://forum.xda-developers.com/donatetome.php?u=3064407"));
//			break;
		}
		
	startActivity(nextIntent);
		
	}
    
}