package com.grimmvarg.android.tuxility;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import android.util.Log;

public class CLIHandler {
	
	public void setup() {
		File tuxilityDir = new File("/mnt/sdcard/.tuxility");
		if(!tuxilityDir.exists()){
			Log.v("<--- CLIHandler - Setup() --->", "Creating folder: " + tuxilityDir.toString());
		}
		else {
			Log.v("<--- CLIHandler - Setup() --->", "Found file: " + tuxilityDir.toString());
		}
		
	}
	
    public String runCommand(String command){
    	String result = "";
    	String buffer = "";
    	BufferedReader stdInput = null;
    	try {
			Process process = Runtime.getRuntime().exec(command);
			stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while((buffer=stdInput.readLine()) !=null){
				result += buffer + "\n";
			}
			stdInput.close();

		} catch (IOException e) {
			Log.v("runCommand", e.toString());
		}
    	return result;
    }

}
