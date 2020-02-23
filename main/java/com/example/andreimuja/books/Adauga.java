package com.example.andreimuja.books;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class Adauga extends AppCompatActivity {

    EditText autor;
    EditText titlu;
    EditText gen;
    EditText pret;
    RadioGroup aspect;
    DatePicker data;
    Button actiuneB;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga);
        autor=(EditText)findViewById(R.id.autor);
        titlu=(EditText)findViewById(R.id.titlu);
        gen=(EditText)findViewById(R.id.gen);
        pret=(EditText)findViewById(R.id.pret);
        aspect=(RadioGroup)findViewById(R.id.aspect);
        data=(DatePicker)findViewById(R.id.data);
        actiuneB=(Button)findViewById(R.id.actiuneB);
        Intent intent=getIntent();
        bundle=intent.getExtras();
        if(bundle.get("operatie").equals("adauga")){
            actiuneB.setText(R.string.adauga);
            actiuneB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adauga();
                }
            });
        }else if(bundle.get("operatie").equals("editare")){
            autor.setText(bundle.get("autor").toString());
            titlu.setText(bundle.get("titlu").toString());
            gen.setText(bundle.get("gen").toString());
            pret.setText(bundle.get("pret").toString());
            if(bundle.get("aspect").equals("nou")){
                aspect.check(R.id.nouRB);
            }else if(bundle.get("aspect").equals("deteriorat")){
                aspect.check(R.id.deterioratRB);
            }
            data.updateDate(Integer.parseInt(bundle.get("an").toString()),Integer.parseInt(bundle.get("luna").toString()),Integer.parseInt(bundle.get("zi").toString()));
            actiuneB.setText(R.string.editare);
            actiuneB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editare();
                }
            });
        }
    }

    private void editare() {
        if(autor.getText().toString().equals("")||titlu.getText().toString().equals("")||gen.getText().toString().equals("")
                ||pret.getText().toString().equals("")||aspect.getCheckedRadioButtonId()==-1)
            Toast.makeText(Adauga.this,getString(R.string.faraDate),Toast.LENGTH_LONG).show();
        else{
            Carte carte=new Carte();
            Calendar calendar=Calendar.getInstance();
            carte.setAutor(autor.getText().toString());
            carte.setTitlu(titlu.getText().toString());
            carte.setGen(gen.getText().toString());
            carte.setPret(Double.parseDouble(pret.getText().toString()));
            if(((RadioButton)findViewById(aspect.getCheckedRadioButtonId())).getText().toString().equals("nou")){
                carte.setAspect(Aspect.nou);
            }else if(((RadioButton)findViewById(aspect.getCheckedRadioButtonId())).getText().toString().equals("deteriorat")){
                carte.setAspect(Aspect.deteriorat);
            }
            carte.setDataPublicarii(calendar);
            MainActivity.list.set(Integer.parseInt(bundle.get("pozitie").toString()),carte);
            Toast.makeText(Adauga.this,getString(R.string.confirmareEditare),Toast.LENGTH_LONG).show();
        }
    }

    private void adauga() {
        if(autor.getText().toString().equals("")||titlu.getText().toString().equals("")||gen.getText().toString().equals("")
                ||pret.getText().toString().equals("")||aspect.getCheckedRadioButtonId()==-1)
            Toast.makeText(Adauga.this,getString(R.string.faraDate),Toast.LENGTH_LONG).show();
        else{
            Carte carte=new Carte();
            Calendar calendar=Calendar.getInstance();
            carte.setAutor(autor.getText().toString());
            carte.setTitlu(titlu.getText().toString());
            carte.setGen(gen.getText().toString());
            carte.setPret(Double.parseDouble(pret.getText().toString()));
            if(((RadioButton)findViewById(aspect.getCheckedRadioButtonId())).getText().toString().equals("nou")){
                carte.setAspect(Aspect.nou);
            }else if(((RadioButton)findViewById(aspect.getCheckedRadioButtonId())).getText().toString().equals("deteriorat")){
                carte.setAspect(Aspect.deteriorat);
            }
            carte.setDataPublicarii(calendar);
            MainActivity.list.add(carte);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_adauga,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.carti: {
                if(item.isEnabled()) {
                    Intent intent = new Intent(Adauga.this, Carti.class);
                    startActivity(intent);
                    finish();
                }
                return true;
            }
            case R.id.despre: {
                if(item.isEnabled()) {
                    Toast.makeText(Adauga.this, getString(R.string.autorAplicatie), Toast.LENGTH_SHORT).show();
                }
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }
}
