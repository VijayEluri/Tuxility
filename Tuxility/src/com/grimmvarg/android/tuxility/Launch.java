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

public class Launch extends Activity implements OnClickListener {
    CLIHandler cliHandler;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    	if(cliHandler == null){
    		CLIHandler.getInstance(getApplicationContext());
    	}
        
    	Button backupManagement = (Button)findViewById(R.id.backupManagement);
    	Button kernelHacking = (Button)findViewById(R.id.kernelHacking);
    	Button rebootManager = (Button)findViewById(R.id.rebootManager);
    	
    	backupManagement.setOnClickListener(this);
    	kernelHacking.setOnClickListener(this);
    	rebootManager.setOnClickListener(this);
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
		
		if(findViewById(view.getId()).equals(findViewById(R.id.backupManagement))){
			nextIntent.setClassName(this, BackupManagement.class.getName());
		}
		else if (findViewById(view.getId()).equals(findViewById(R.id.kernelHacking))) {
			nextIntent.setClassName(this, KernelHacking.class.getName());
		}
		else if (findViewById(view.getId()).equals(findViewById(R.id.rebootManager))) {
			nextIntent.setClassName(this, RebootManager.class.getName());
		}
		
		startActivity(nextIntent);
		
	}
    
    
}