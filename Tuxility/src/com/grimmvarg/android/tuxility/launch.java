package com.grimmvarg.android.tuxility;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class launch extends Activity {
    CLIHandler cliHandler = new CLIHandler();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.v("TEEEEEEEST", "" + cliHandler.runCommand("ls", false));
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
}