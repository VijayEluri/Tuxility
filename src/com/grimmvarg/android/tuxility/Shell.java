package com.grimmvarg.android.tuxility;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import android.util.Log;

public class Shell {
	private Process userShell = null;
	private Process suShell = null;
	
	public Shell(){
		
	}
	
	synchronized int execute(String command, int mode) {
		Process shell;
		DataOutputStream toProcess = null;
		DataInputStream fromProcess = null;
		int result = -1;

		try {
			if (mode == 0) {
				if (userShell == null)
					userShell = Runtime.getRuntime().exec("sh");
				shell = userShell;
			} else {
				if (suShell == null)
					suShell = Runtime.getRuntime().exec("su");
				shell = suShell;
			}
			toProcess = new DataOutputStream(shell.getOutputStream());
			fromProcess = new DataInputStream(shell.getInputStream());

			if(mode != 2) command += " > /dev/null";
			
			toProcess.writeBytes(command + "\n");
			toProcess.flush();
			toProcess.writeBytes("echo $?\n");
			toProcess.flush();
			
			if(mode != 2) result = Integer.parseInt(fromProcess.readLine());
			Log.v("<--- CLIHandler - Execute() --->", command + " result: " + result);

		} catch (IOException e) {
			Log.v("<--- CLIHandler - Execute() --->", e.toString()); 
		} 
		
		return result;
	}

}
