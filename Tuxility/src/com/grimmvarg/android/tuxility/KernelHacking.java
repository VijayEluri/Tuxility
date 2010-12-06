package com.grimmvarg.android.tuxility;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
		if (findViewById(view.getId()).equals(findViewById(R.id.backupKernel))) {
			tuxHelper.showMessage("Backing up kernel");
			tuxHelper.backupKernel("backup");
		}
		else if (findViewById(view.getId()).equals(findViewById(R.id.installKernel))) {
			tuxHelper.showMessage("Installing kernel");
			tuxHelper.installKernel("");
		}
		
	}

}
