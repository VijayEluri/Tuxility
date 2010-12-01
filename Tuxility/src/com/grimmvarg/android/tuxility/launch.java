package com.grimmvarg.android.tuxility;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class launch extends Activity {
    CLIHandler cliHandler = new CLIHandler();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	cliHandler.runCommand("ls");
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
}