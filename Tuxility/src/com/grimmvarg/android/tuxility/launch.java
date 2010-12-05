package com.grimmvarg.android.tuxility;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class launch extends Activity {
    CLIHandler cliHandler = new CLIHandler();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	cliHandler.setup();
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void BackupManagement(View view){
    	setContentView(R.layout.backupmanagement);
    }
    
    public void KernelHacking(View view){
    	setContentView(R.layout.kernelhacking);
    }
    
    public void showMessage(String message){
    	Context context = getApplicationContext();
    	CharSequence text = message;
    	int duration = Toast.LENGTH_SHORT;

    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
    }
    
    
}