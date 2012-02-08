package com.lpro.courses;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class Ajout_produit extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ajout_produit);
		
		
		String[] produits = getResources().getStringArray(R.array.liste_produits);
		AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.produit_item, produits);
	    textView.setAdapter(adapter);
	    
	}
}
