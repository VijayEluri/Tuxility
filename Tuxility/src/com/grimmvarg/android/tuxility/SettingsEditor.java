package com.grimmvarg.android.tuxility;

import java.util.ArrayList;
import java.util.Collections;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class SettingsEditor extends ListActivity {
	TuxHelper tuxHelper = TuxHelper.getInstance();
	protected ArrayList<String> settingsList;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settingseditor);
		tuxHelper.checkoutSettings();
		refreshList();
	}
    
	private void refreshList() {
		settingsList = tuxHelper.getSettingsList();
		
//		Collections.sort(settingsList, String.CASE_INSENSITIVE_ORDER);
        ArrayAdapter<String> fileAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, settingsList);
        setListAdapter(fileAdapter);
		
	}

}
