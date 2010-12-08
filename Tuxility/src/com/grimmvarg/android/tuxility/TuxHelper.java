package com.grimmvarg.android.tuxility;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.content.res.AssetManager;
import android.provider.UserDictionary.Words;
import android.util.Log;
import android.widget.Toast;

public class TuxHelper {
	private String settingsDB = "/dbdata/databases/com.android.providers.settings/settings.db";
	private String backupPath = "/sdcard/.tuxility/backup/";
	private String tuxilityPath = "/sdcard/.tuxility/";
	private Process suShell = null;
	private Process userShell = null;
	private Context tuxilityContext;
	private static TuxHelper instance = null;
	private String choosenFile = "";

	private TuxHelper(Context context) {
		tuxilityContext = context;
		File tuxilityDir = new File(tuxilityPath);
		File backupDir = new File(backupPath);

		if (!tuxilityDir.exists()) {
			Log.v("<--- CLIHandler - Setup() --->", "Creating our folders in "
					+ tuxilityDir.toString());
			tuxilityDir.mkdir();
			backupDir.mkdir();
			AssetManager assetManager = context.getAssets();
			InputStream inputStream = null;
			try {
				inputStream = assetManager.open("redbend_ua");
				OutputStream out = new FileOutputStream(tuxilityPath
						+ "redbend_ua");
				byte buf[] = new byte[1024];
				int len;
				while ((len = inputStream.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.close();
				inputStream.close();
				showMessage("Setup Completed");
			} catch (IOException e) {
				Log.v("<--- CLIHandler - Setup() --->", e.toString());
			}

		} else {
			Log.v("<--- CLIHandler - Setup() --->", "Found file: "
					+ tuxilityDir.toString());
		}

	}

	public void backupSettingsDB() {
		Log.v("<--- CLIHandler - BackupSettings() --->",
				"creating settings.db backup");
		execute("cp " + settingsDB + " " + backupPath + "settings.db", true);

	}

	public void restoreSettingsDB() {
		Log.v("<--- CLIHandler - RestoreSettings() --->",
				"restoring settings.db");
		execute("cp " + settingsDB + " " + backupPath + "settings.db", true);

	}

	public void backupEFS() {
		Log.v("<--- CLIHandler - BackupEFS() --->", "creating EFS backup");
		execute("tar zcvf " + backupPath + "efs-backup.tar.gz /efs", true);
	}

	public void restoreEFS() {
		Log.v("<--- CLIHandler - RestoreEFS() --->", "restoring EFS");
		execute("tar xvvf " + backupPath + "efs-backup.tar.gz /efs", true);

	}

	public String unTar(String tarFile) {
		String timeStamp = now();
		String path = "/cache/" + timeStamp + "/";
		execute("mkdir " + path, true);
		execute("tar " + "-C " + path + " -xf " + tarFile, true);
		return path;
	}

	public void installKernel(String kernelPath) {
		if (kernelPath.contains(".tar")){
			kernelPath = unTar(kernelPath) + "zImage";
		}
		showMessage("installing" + kernelPath);
		execute("cat " + tuxilityPath + "redbend_ua > /data/redbend_ua", true);
		execute("chmod 755 /data/redbend_ua", true);
		execute("/data/redbend_ua restore " + kernelPath + " /dev/block/bml7",true);
	}

	public void backupKernel(String name) {
		name = now();
		execute("cat /dev/block/bml7 > " + backupPath + name + "-kernel", true);
		showMessage("Backed up as: " + name + "-kernel");
	}

	private void execute(String command, Boolean su) {
		Process shell;
		DataOutputStream toProcess = null;

		try {
			if (su) {
				if (suShell == null)
					suShell = Runtime.getRuntime().exec("su");
				shell = suShell;
			} else {
				if (userShell == null)
					userShell = Runtime.getRuntime().exec("sh");
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

	public void reboot(String type) {
		execute("reboot " + type, true);
	}

	public static TuxHelper getInstance(Context applicationContext) {
		if (instance == null) {
			instance = new TuxHelper(applicationContext);
		}

		return instance;
	}

	public static TuxHelper getInstance() {
		return instance;
	}

	public void showMessage(String message) {
		CharSequence text = message;
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(tuxilityContext, text, duration);
		toast.show();
	}

	public String getChoosenFile() {
		return choosenFile;
	}

	public void setChoosenFile(String choosenFile) {
		this.choosenFile = choosenFile;
	}

	public static String now() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHHmmss");
		return sdf.format(cal.getTime());

	}

	public void clearBatteryStats() {
		execute("rm /data/system/batterystats.bin", true);

	}

	public void toggleMediaScanner(Boolean state) {
		if (state) {
			execute(
					"pm disable com.android.providers.media/com.android.providers.media.MediaScannerReceiver",
					true);
		} else {
			execute(
					"pm enable com.android.providers.media/com.android.providers.media.MediaScannerReceiver",
					true);
		}
	}

}
