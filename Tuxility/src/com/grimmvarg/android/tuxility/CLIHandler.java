package com.grimmvarg.android.tuxility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import android.content.Context;
import android.util.Log;

public class CLIHandler {
	private static String settingsDB = "/dbdata/databases/com.android.providers.settings/settings.db";
	private static String tuxility = "/sdcard/.tuxility";
	private final Runtime RUNTIME = Runtime.getRuntime();
	private Process process;

	public void setup() {
		File tuxilityDir = new File("/mnt/sdcard/.tuxility");
		Log.v("<--- CLIHandler - Setup() --->", "Getting root");
		if (!tuxilityDir.exists()) {
			Log.v("<--- CLIHandler - Setup() --->", "Creating our folders: "+ tuxilityDir.toString());
			execute("mkdir " + tuxility, false);
			execute("mkdir " + tuxility + "/backup", false);
		} else {
			Log.v("<--- CLIHandler - Setup() --->", "Found file: "+ tuxilityDir.toString());
		}

	}

	public boolean backupSettingsDB() {
		Log.v("<--- CLIHandler - BackupSettings() --->", "creating settings.db backup");
		execute("cp " + settingsDB + " " + tuxility + "/backup/settings.db", true);

		return true;
	}
	
	private void execute(String command, Boolean su){
		if(su) command = "su -c \"" + command  + "\"";
		try {
			Log.v("<--- CLIHandler - Execute() --->", "Executing: " + command);
			process = RUNTIME.exec(command);
			int result = process.waitFor();
			Log.v("<--- CLIHandler - Execute() --->", "Result: " + result + "from: " + command);
		} catch (IOException e) {
			Log.v("<--- CLIHandler - Execute() --->", e.toString());
		} catch (InterruptedException e) {
			Log.v("<--- CLIHandler - Execute() --->", e.toString());
		}
	}
}
