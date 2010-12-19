package com.grimmvarg.android.tuxility;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
		commandMap.put("Service Mode", "tel:*%23*%23197328640%23*%23*");
		commandMap.put("Build Info", "tel:*%23*%2344336%23*%23*");
		commandMap.put("Phone And Battery Info", "tel:*%23*%234636%23*%23*");
		commandMap.put("Audio Test", "tel:*%23*%230673%23*%23*");
		commandMap.put("Wlan Tests", "tel:*%23*%23232339%23*%23*");
		commandMap.put("Light Sensor", "tel:*%23*%230589%23*%23*");

		commands.add("Service Mode");
		commands.add("Build Info");
		commands.add("Phone And Battery Info");
		commands.add("Audio Test");
		commands.add("Wlan Tests");
		commands.add("Light Sensor");
		
	}
	

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent nextIntent = new Intent(Intent.ACTION_DIAL);
		nextIntent.setData(Uri.parse(commandMap.get(commands.get(position))));
		startActivity(nextIntent);
	}

}
