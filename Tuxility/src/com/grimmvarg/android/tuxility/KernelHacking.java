package com.grimmvarg.android.tuxility;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class KernelHacking extends Activity implements OnClickListener{
	private CLIHandler cliHandler = CLIHandler.getInstance();
	
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
			cliHandler.showMessage("Backing up kernel");
			cliHandler.backupKernel("backup");
		}
		else if (findViewById(view.getId()).equals(findViewById(R.id.installKernel))) {
			cliHandler.showMessage("Installing kernel");
			cliHandler.installKernel("");
		}
		
	}

}
