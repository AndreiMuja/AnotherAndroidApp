package com.example.andreimuja.books;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    static List<Carte> list;
    static Context context;
    static PreluareXML preluareXML;
    static boolean ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        list=new Vector<>();
        preluareXML=new PreluareXML();
        ok=true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.adauga: {
                if (item.isEnabled()) {
                    Intent intent = new Intent(MainActivity.this, Adauga.class);
                    intent.putExtra("operatie", "adauga");
                    startActivity(intent);
                }
                return true;
            }
            case R.id.despre: {
                if (item.isEnabled()) {
                    Toast.makeText(MainActivity.this, getString(R.string.autorAplicatie), Toast.LENGTH_LONG).show();
                }
            }
            case R.id.carti: {
                if (item.isEnabled()) {
                    Intent intent = new Intent(MainActivity.this, Carti.class);
                    startActivity(intent);
                }
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

}