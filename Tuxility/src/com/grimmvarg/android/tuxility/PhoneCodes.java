package com.grimmvarg.android.tuxility;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PhoneCodes extends ListActivity {

	private ArrayList<String> commands = new ArrayList<String>();
	private HashMap<String, String> commandMap = new HashMap<String, String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
    	setContentView(R.layout.phonecodes);
		fillList();
		display();
	}

	private void display() {
		ArrayAdapter<String> fileAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, commands);
		setListAdapter(fileAdapter);

	}

	private void fillList() {
		commandMap.put("Service Mode", "*#*#197328640#*#*");
		commandMap.put("Phone Version", "*#*#44336#*#*");

		commands.add("Service Mode");
		commands.add("Phone Version");

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent nextIntent = new Intent(Intent.ACTION_DIAL);
		nextIntent.setData(Uri.parse(commandMap.get(commands.get(position))));
		startActivity(nextIntent);
	}

}
