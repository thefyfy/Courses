package com.lpro.courses;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Parametres extends ListActivity {
    
	private final int PICK_CONTACT = 0;
	private final String PREFS_NAME = "prefsListeCourse";
	private SharedPreferences prefs = null;

	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);               
        
        String[] preferences = getResources().getStringArray(R.array.liste_parametres);
        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String preference = prefs.getString("nomAmi", preferences[0]);
        String[] values = new String[] { preference };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.liste_params, values);
		setListAdapter(adapter);
        
        System.out.println(preference);
		
		getListView().setOnItemClickListener(new OnItemClickListener() 
		{
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
	    	{
				if(position == 0)	//choisir un ami
				{
					//When clicked, show a toast with the TextView text				
			    	Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);        
			        Parametres.this.startActivityForResult(i, PICK_CONTACT);
				}				
	        }
		});
    }
    
    @Override
    public void  onActivityResult(int reqCode, int resultCode, Intent data)
    {
    	super.onActivityResult(reqCode, resultCode, data);
    	switch (reqCode)
    	{
    	case (PICK_CONTACT):
    		if(resultCode == Activity.RESULT_OK)
    		{
    			Uri contactData = data.getData();
    			Cursor c = managedQuery(contactData, null, null, null, null);
    			if(c.moveToFirst())
    			{
    				String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
     				
    				TextView tv = (TextView)findViewById(R.id.liste_params);
    				tv.setText(Html.fromHtml(name + "<br /><small>Choisir un autre ami</small>"));
    				
    				//sauvegarde de l'ami choisi
    				prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
    		        SharedPreferences.Editor editeur = prefs.edit();
    		        editeur.putString("nomAmi", name);
    		        editeur.commit();
    			}
    		}
    		break;
    	}
    }
}