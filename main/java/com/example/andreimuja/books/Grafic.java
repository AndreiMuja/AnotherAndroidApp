package com.example.andreimuja.books;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

public class Grafic extends AppCompatActivity {

    LinearLayout linearLayout;
    List<String> listaGenuri = new Vector<>();
    List<Integer> numarCartiGen = new Vector<>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafic);
        context = this;
        linearLayout = (LinearLayout) findViewById(R.id.afisareGrafic);
        Graficut graficut = new Graficut(this);
        linearLayout.addView(graficut);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_grafic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.adauga: {
                if (item.isEnabled()) {
                    Intent intent = new Intent(Grafic.this, Adauga.class);
                    intent.putExtra("operatie","adauga");
                    startActivity(intent);
                    finish();
                }
                return true;
            }
            case R.id.despre: {
                if(item.isEnabled()) {
                    Toast.makeText(Grafic.this, getString(R.string.autorAplicatie), Toast.LENGTH_SHORT).show();
                }
                return true;
            }
            case R.id.carti: {
                if(item.isEnabled()) {
                    Intent intent = new Intent(Grafic.this, Carti.class);
                    startActivity(intent);
                    finish();
                }
                return true;
            }
            case R.id.preluare_carti_xml: {
                if(item.isEnabled()) {
                    if(Acasa.ok) {
                        Acasa.preluareXML.execute("https://proiect-dam-svalentin.c9users.io/carti.xml");
                        Acasa.ok = false;
                        finish();
                    } else {
                        Toast.makeText(Grafic.this, getString(R.string.eroareCitit), Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    class Graficut extends View {

        Rect dreptunghi;
        Paint culoare;
        int inaltime;
        boolean ok;

        public Graficut(Context context) {
            super(context);
            culoare = new Paint();
            culoare.setColor(Color.BLUE);
            culoare.setStyle(Paint.Style.FILL);
            inaltime = 0;
        }

        private void genuri() {
            if (Acasa.list.size() != 0) {
                for (int i = 0; i < Acasa.list.size(); i++) {
                    ok = true;
                    if (listaGenuri.size() == 0) {
                        listaGenuri.add(Acasa.list.get(i).getGen());
                    } else {
                        for (int j = 0; j < listaGenuri.size(); j++) {
                            if (Acasa.list.get(i).getGen().equalsIgnoreCase(listaGenuri.get(j))) {
                                ok = false;
                            }
                        }
                        if (ok) {
                            listaGenuri.add(Acasa.list.get(i).getGen());
                        }
                    }
                }
                numarCartiGen();
            } else {
                Toast.makeText(context, getString(R.string.faraDate), Toast.LENGTH_SHORT).show();
            }
        }

        private void numarCartiGen() {
            for (int i = 0; i < listaGenuri.size(); i++) {
                numarCartiGen.add(0);
            }
            for (int i = 0; i < listaGenuri.size(); i++) {
                for (int j = 0; j < Acasa.list.size(); j++) {
                    if (listaGenuri.get(i).equalsIgnoreCase(Acasa.list.get(j).getGen())) {
                        numarCartiGen.set(i, numarCartiGen.get(i) + 1);
                    }
                }
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            genuri();

            for (int i = 0; i < numarCartiGen.size(); i++) {
                dreptunghi = new Rect();
                dreptunghi.set(0, inaltime, numarCartiGen.get(i) * 40, inaltime + 10);
                canvas.drawRect(dreptunghi, culoare);
                inaltime += 40;

            }
        }
    }
}
