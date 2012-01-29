package com.lpro.courses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Courses extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //bouton 
        Button faireCourses = (Button)findViewById(R.id.faireCourses);
        faireCourses.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Courses.this, Emplettes.class);
				startActivity(i);				
			}
		});
        
        //bouton de création de liste
        Button creerListe = (Button)findViewById(R.id.creerListe);
        creerListe.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Courses.this, Liste.class);
				startActivity(i);				
			}
		});
       
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.options_menu, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch (item.getItemId()){
    	case R.id.parametres:
    		this.afficherParametres();
    		return true;
    	case R.id.quitter:
    		this.quitter();
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
    
    public void afficherParametres()
    {
    	Intent i = new Intent(Courses.this, Parametres.class);
		startActivity(i);
    }
    
    public void quitter()
    {
    	Intent i = new Intent(Intent.ACTION_MAIN);
    	i.addCategory(Intent.CATEGORY_HOME);
    	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(i);
    }
}