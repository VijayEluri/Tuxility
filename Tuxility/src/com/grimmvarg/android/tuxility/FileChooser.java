package com.grimmvarg.android.tuxility;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class FileChooser extends ListActivity {
	
    protected ArrayList<String> fileList;
    protected File root = new File("/sdcard/download/");
    protected TuxHelper tuxHelper = TuxHelper.getInstance();
    protected File workingDir;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.filechooser);
		refresh(root);
	}
	
	private void refresh(File file){
		workingDir = file;
		setTitle(getString(R.string.app_name) + " > " + workingDir);
	    fileList = new ArrayList<String>();
	    fileList.add("..");
        getFiles(workingDir);
        displayFiles();
	}
 
	private void getFiles(File path) {
        if (path.isDirectory()) {
            File[] directory = path.listFiles();
            for (File file : directory) {
            	fileList.add(file.getName());
            }
        }
    }

    private void displayFiles() {
        Collections.sort(fileList, String.CASE_INSENSITIVE_ORDER);
        ArrayAdapter<String> fileAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fileList);
        setListAdapter(fileAdapter);

    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        File pickedFile = new File(workingDir + "/" + fileList.get(position));
        if(!pickedFile.isDirectory()){
        	tuxHelper.setChoosenFile(pickedFile.getAbsolutePath());
        	setResult(RESULT_OK);
            finish();
        }else{
        	refresh(pickedFile);
        }
        
    }



}
