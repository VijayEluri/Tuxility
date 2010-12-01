package com.grimmvarg.android.tuxility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.util.Log;

public class CLIHandler {
	
    public String runCommand(String command, Boolean su){
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
