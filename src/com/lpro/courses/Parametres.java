package com.lpro.courses;

import java.util.HashSet;

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

	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setListAdapter(ArrayAdapter.createFromResource(getApplicationContext(), R.array.liste_parametres, R.layout.liste_params));
        		
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
    				String id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
     				
    				TextView tv = (TextView)findViewById(R.id.liste_params);
    				tv.setText(Html.fromHtml(name + "<br /><small>Choisir un autre ami</small>"));
    				
    				//sauvegarde de l'ami choisi
    				SharedPreferences prefs = getSharedPreferences("ami", MODE_PRIVATE);
    		        SharedPreferences.Editor editeur = prefs.edit();
    		        HashSet<String> list = new HashSet<String>();
    		        list.add(name);
    		        list.add(id);
    		        editeur.putStringSet("liste de courses", list);
    		        editeur.commit();
    			}
    		}
    		break;
    	}
    }
}