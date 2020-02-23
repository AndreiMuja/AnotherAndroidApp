package com.example.andreimuja.books;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

public class Carti extends AppCompatActivity {

    ListView listacarti;
    List<String> lista=new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carti);
        listacarti=(ListView)findViewById(R.id.listaCarti);
        populare();


    }

    private void populare() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_carti,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.adauga: {
                if (item.isEnabled()) {
                    Intent intent = new Intent(Carti.this, Adauga.class);
                    intent.putExtra("operatie","adauga");
                    startActivity(intent);
                    finish();
                }
                return true;
            }
            case R.id.despre: {
                if(item.isEnabled()) {
                    Toast.makeText(Carti.this, getString(R.string.autorAplicatie), Toast.LENGTH_SHORT).show();
                }
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }
}
