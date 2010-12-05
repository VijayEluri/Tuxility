package com.grimmvarg.android.tuxility;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import android.content.Context;
import android.os.Message;
import android.util.Log;

public class CLIHandler {
	private static String settingsDB = "/dbdata/databases/com.android.providers.settings/settings.db";
	private static String backupDir = "/sdcard/.tuxility/backup/";
	private static String tuxilityDir = "/sdcard/.tuxility/";
	private Process suShell = null;
	private Process userShell = null;

	public void setup() {
		File tuxilityDir = new File("/mnt/sdcard/.tuxility");
		if (!tuxilityDir.exists()) {
			Log.v("<--- CLIHandler - Setup() --->", "Creating our folders in " + tuxilityDir.toString());
			execute("mkdir " + tuxilityDir, false);
			execute("mkdir " + backupDir, false);
		} else {
			Log.v("<--- CLIHandler - Setup() --->", "Found file: " + tuxilityDir.toString());
		}

	}

	public void backupSettingsDB() {
		Log.v("<--- CLIHandler - BackupSettings() --->", "creating settings.db backup");
		execute("cp " + settingsDB + " " + backupDir + "settings.db", true);

	}
	
	public void restoreSettingsDB() {
		Log.v("<--- CLIHandler - RestoreSettings() --->", "restoring settings.db");
		execute("cp " + settingsDB + " " + backupDir + "settings.db", true);

	}
	
	public void backupEFS(){
		Log.v("<--- CLIHandler - BackupEFS() --->", "creating EFS backup");
		execute("tar zcvf " + backupDir + "efs-backup.tar.gz /efs", true);
	}
	
	public void restoreEFS(){
		Log.v("<--- CLIHandler - RestoreEFS() --->", "restoring EFS");
		execute("tar xvvf " + backupDir + "efs-backup.tar.gz /efs", true);

	}
	
	public void installKernel(String path){
		execute("cat " + tuxilityDir + "redbend_ua > /data/redbend_ua", true);
		execute("chmod 755 /data/redbend_ua", true);
		execute("/data/redbend_ua restore " + tuxilityDir + "zImage /dev/block/bml7", true);
	}
	
	public void backupKernel(){
		execute("cat /dev/block/bml7 > " + backupDir + "kernel-backup", true);
	}

	private void execute(String command, Boolean su){
		Process shell;
		DataOutputStream toProcess = null;
		
		try {
			if(su){
				if(suShell == null) suShell = Runtime.getRuntime().exec("su");
				shell = suShell;
			}else {
				if(userShell == null) userShell = Runtime.getRuntime().exec("sh");
				shell = userShell;
			}

			toProcess = new DataOutputStream(shell.getOutputStream());
			Log.v("<--- CLIHandler - Execute() --->", "Executing: " + command);
			toProcess.writeBytes(command + "\n");
			toProcess.flush();
		} catch (IOException e) {
			Log.v("<--- CLIHandler - Execute() --->", e.toString());
		} 
	}
	
	public void reboot(String type){
		execute("reboot" + type , true);
	}
}
