package com.grimmvarg.android.tuxility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.content.res.AssetManager;
import android.location.Address;
import android.util.Log;
import android.widget.Toast;

public class TuxHelper {
	private String backupPath = "/sdcard/.tuxility/backup/";
	private String tuxilityPath = "/sdcard/.tuxility/";
	private Context tuxilityContext;
	private static TuxHelper instance = null;
	private Shell shell = new Shell();
	

	private TuxHelper(Context context) {
		tuxilityContext = context;

		File tuxilityDir = new File(tuxilityPath);
		File backupDir = new File(backupPath);
		
		// getting root and cleaning up our mess in cache
		shell.execute("rm -r /cache/tuxility-*", 1);

		if (!tuxilityDir.exists()) {
			Log.v("<--- CLIHandler - Setup() --->", "Creating our folders in " + tuxilityDir.toString());
			tuxilityDir.mkdir();
			backupDir.mkdir();
			getAssets();

		} else if(!(new File(tuxilityPath+"assets").exists())){
			getAssets();
		}
	}
	
	public void getAssets(){
		AssetManager assetManager = tuxilityContext.getAssets();
		InputStream inputStream = null;
		try {
			inputStream = assetManager.open("assets.tar");
			OutputStream out = new FileOutputStream(tuxilityPath
					+ "assets.tar");
			byte buf[] = new byte[1024];
			int len;
			while ((len = inputStream.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			inputStream.close();
			shell.execute("tar " + "-C " + tuxilityPath + " -xf " + tuxilityPath + "assets.tar", 1);
			shell.execute("rm " + tuxilityPath + "assets.tar", 1);
			showMessage("Setup Completed");
		} catch (IOException e) {
			Log.v("<--- CLIHandler - Setup() --->", e.toString());
		}
	}

	public void backupEFS() {
		File efsBackup = new File( backupPath + "efs-backup.tar.gz");
		if(efsBackup.exists()){
			showMessage("Backup exist!");
		} else {
			int result = shell.execute("tar zcvf " + backupPath + "efs-backup.tar.gz /efs", 1);
			if(result == 0) showMessage("Success!");
		}
	}

	public void restoreEFS() {
		File efsBackup = new File( backupPath + "efs-backup.tar.gz");
		if(efsBackup.exists()){
			shell.execute("tar xvvf " + backupPath + "efs-backup.tar.gz /efs", 1);
			showMessage("Restoring /efs");
		} else {
			showMessage("No backup found!");
		}

	}

	public String unTar(String tarFile) {
		String timeStamp = now();
		String path = tuxilityPath + ".tmp/";
		shell.execute("mkdir " + path, 0);
		shell.execute("tar " + "-C " + path + " -xf " + tarFile, 0);
		return path;
	}

	public void installKernel(String kernelPath) {
		showMessage("Preparing for flash..");
		if (kernelPath.contains(".tar")){
			kernelPath = unTar(kernelPath) + "zImage";
		}
		
		showMessage("Flashing");
		
		shell.execute("cat " + tuxilityPath + "assets/" + "flash_image > /data/flash_image", 2);
		shell.execute("chmod 755 /data/flash_image", 2);
		//shell.execute("chown system.system /data/flash_image", 2);
		shell.execute("/data/flash_image" + " boot " + kernelPath , 2);
		
		showMessage("Reboot to try out your new kernel :)");
		
	}

	public void backupKernel(String name) {
		name = now();
		File kernelBackup = new File(backupPath + name + "-kernelBackup");
		shell.execute("cat /dev/block/bml7 > " + backupPath + name + "-kernelBackup", 1);
		if(kernelBackup.exists()){
			showMessage("Backed up as: " + name + "-kernelBackup");
		}
		else {
			showMessage("Backed up failed!");
		}
	}
	

	public void reboot(String type) {
		shell.execute("reboot " + type, 1);
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

	public static String now() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHHmmss");
		return sdf.format(cal.getTime());

	}

	public void clearBatteryStats() {
		File batteryStats = new File("/data/system/batterystats.bin");
		shell.execute("rm /data/system/batterystats.bin", 1);
		if(!batteryStats.exists()) showMessage("Batterystats cleared!");

	}

	public void toggleMediaScanner(Boolean state) {
		if (state) {
			shell.execute("pm disable com.android.providers.media/com.android.providers.media.MediaScannerReceiver",1);
		} else {
			shell.execute("pm enable com.android.providers.media/com.android.providers.media.MediaScannerReceiver", 1);
		}
	}

}
