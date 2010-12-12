/*
* This file is part of Justify.
* Copyright (C) 2010 Alexander Bjerkan <alexander.bjerkan@gmail.com>
*
* Justify is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Justify is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Jusitfy. If not, see <http://www.gnu.org/licenses/>.
*/
package com.grimmvarg.android.tuxility;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Launch extends Activity implements OnClickListener {
    TuxHelper tuxHelper;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	TuxHelper.getInstance(getApplicationContext());
        setContentView(R.layout.main);

    	Button backupManagement = (Button)findViewById(R.id.backupManagement);
    	Button kernelHacking = (Button)findViewById(R.id.kernelHacking);
    	Button rebootManager = (Button)findViewById(R.id.rebootManager);
    	Button phoneSettings = (Button)findViewById(R.id.phonesettingsandtweaks);
    	Button support = (Button)findViewById(R.id.support);
    	Button donations = (Button)findViewById(R.id.donations);
    	
    	
    	
    	backupManagement.setOnClickListener(this);
    	kernelHacking.setOnClickListener(this);
    	rebootManager.setOnClickListener(this);
    	phoneSettings.setOnClickListener(this);
    	support.setOnClickListener(this);
    	donations.setOnClickListener(this);
    }

	public void onClick(View view) {
		Intent nextIntent = new Intent(Intent.ACTION_VIEW);
		
		switch (view.getId()) {
		case R.id.backupManagement:
			nextIntent.setClassName(this, BackupManagement.class.getName());
			break;
		case R.id.kernelHacking:
			nextIntent.setClassName(this, KernelHacking.class.getName());
			break;
		case R.id.rebootManager:
			nextIntent.setClassName(this, RebootManager.class.getName());
			break;
		case R.id.phonesettingsandtweaks:
			nextIntent.setClassName(this, PhoneSettingsAndTweaks.class.getName());
			break;
		case R.id.support:
			nextIntent.setData(Uri.parse("http://forum.xda-developers.com/showthread.php?t=864001"));
			break;
		case R.id.donations:
			nextIntent.setData(Uri.parse("http://forum.xda-developers.com/donatetome.php?u=3064407"));
			break;
		}
		
	startActivity(nextIntent);
		
	}
    
}