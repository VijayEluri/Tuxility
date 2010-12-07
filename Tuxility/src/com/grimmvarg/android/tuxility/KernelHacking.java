package com.grimmvarg.android.tuxility;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class KernelHacking extends Activity implements OnClickListener{
	private TuxHelper tuxHelper = TuxHelper.getInstance();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
    	setContentView(R.layout.kernelhacking);
    	
    	Button backupKernel = (Button)findViewById(R.id.backupKernel);
    	Button installKernel = (Button)findViewById(R.id.installKernel);
    	
    	backupKernel.setOnClickListener(this);
    	installKernel.setOnClickListener(this);
	}

	public void onClick(View view) {		
		switch (view.getId()) {
		case R.id.backupKernel:
			tuxHelper.backupKernel("backup");
			break;
		case R.id.installKernel:
			Intent nextIntent = new Intent(Intent.ACTION_VIEW);
			nextIntent.setClassName(this, FileChooser.class.getName());
			nextIntent.putExtra("kernelPath", "");
			startActivityForResult(nextIntent, 1);
			break;
		}

		
	}
	
	@Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  if(resultCode==RESULT_OK && requestCode==1){
	   tuxHelper.installKernel(tuxHelper.getChoosenFile());
	  }
	}
}
