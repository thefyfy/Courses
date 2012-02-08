package com.lpro.courses;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class Liste extends ListActivity {
	
	private final int ADD_PRODUCT = 0;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        String[] produits = getResources().getStringArray(R.array.liste_produits);
        //produits = getResources().getStringArray(R.string.ajout_produit);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.produit, produits);
		setListAdapter(adapter);
		
		
		getListView().setOnItemClickListener(new OnItemClickListener() 
		{
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
	    	{
				if(position == 0) //ajouter un produit
				{
					//When clicked, show a toast with the TextView text				
			    	//Intent i = new Intent(Intent.ACTION_PICK, Ajout_produit.class);        
			        //Liste.this.startActivityForResult(i, ADD_PRODUCT);
					Intent i = new Intent(Liste.this, Ajout_produit.class);
					startActivity(i);
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
    	case (ADD_PRODUCT):
    		if(resultCode == Activity.RESULT_OK)
    		{
    			
    		}
    		break;
    	}
    }
}
