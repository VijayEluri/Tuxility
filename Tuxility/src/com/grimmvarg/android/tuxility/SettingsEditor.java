package com.grimmvarg.android.tuxility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.grimmvarg.android.tuxility.R.string;

import android.R.integer;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class SettingsEditor extends ListActivity implements OnClickListener {
	TuxHelper tuxHelper = TuxHelper.getInstance();
	protected Cursor settingsCursor;
	protected Dialog valueDialog = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settingslist);
		tuxHelper.checkoutSettings();
		refreshList();

		Button save = (Button) findViewById(R.id.saveSettings);
		Button discard = (Button) findViewById(R.id.discardSettings);

		save.setOnClickListener(this);
		discard.setOnClickListener(this);
	}

	private void refreshList() {
		settingsCursor = tuxHelper.getSettingsCursor();
		settingsCursor.moveToLast();
		if(settingsCursor.getString(1) != "wifi_idle_ms"){
			tuxHelper.addSettingsValues();
			tuxHelper.showMessage("Adding");
			settingsCursor = tuxHelper.getSettingsCursor();
		}

		String columns[] = new String[] { "name", "value" };
		int[] to = new int[] { R.id.name_entry };
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.settingslistentry, settingsCursor, columns, to);
		setListAdapter(adapter);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		settingsCursor.moveToPosition(position);
		showValueDialog(settingsCursor.getString(1), settingsCursor.getString(2));
		
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.saveSettings:
			tuxHelper.checkinSettings();
			settingsCursor.close();
			tuxHelper.settingsClose();
			finish();
			break;
		case R.id.discardSettings:
			settingsCursor.close();
			tuxHelper.settingsClose();
			finish();
			break;
		case R.id.ok:
			EditText textField = (EditText)valueDialog.findViewById(R.id.new_value);
			String value = textField.getText().toString();
			int id = settingsCursor.getInt(1);
			tuxHelper.updateSettings("secure", id, "value", value);
			valueDialog.dismiss();
			refreshList();
			break;
		case R.id.cancel:
			valueDialog.cancel();
			break;
		}

	}

	private void showValueDialog(String name, String value) {

		valueDialog = new Dialog(this);
		valueDialog.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
				WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		valueDialog.setTitle("Name: " + name);

		LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View dialogView = li.inflate(R.layout.valuedialog, null);
		valueDialog.setContentView(dialogView);
		EditText textField = (EditText)valueDialog.findViewById(R.id.new_value);
		textField.setText(value);

		valueDialog.show();

		Button okButton = (Button) dialogView.findViewById(R.id.ok);
		Button cancelButton = (Button) dialogView.findViewById(R.id.cancel);
		
		okButton.setOnClickListener(this);
		cancelButton.setOnClickListener(this);

	}

}
