package com.example.andreimuja.books;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;


public class PreluareXML extends AsyncTask<String,Integer,Boolean>{

    List<String> autor=new Vector<>();
    List<String> titlu = new Vector<>();
    List<String> gen = new Vector<>();
    List<String> pret = new Vector<>();
    List<String> aspect = new Vector<>();
    List<String> data = new Vector<>();
    XmlPullParserFactory xmlfactoryObject;

    public List<String> getAutor() {
        return autor;
    }

    public List<String> getTitlu() {
        return titlu;
    }

    public List<String> getGen() {
        return gen;
    }

    public List<String> getPret() {
        return pret;
    }

    public List<String> getAspect() {
        return aspect;
    }

    public List<String> getData() {
        return data;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            InputStream stream=conn.getInputStream();
            xmlfactoryObject=XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser=xmlfactoryObject.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            xmlPullParser.setInput(stream,null);
            int event;
            try{
                event=xmlPullParser.getEventType();
                while(event!=XmlPullParser.END_DOCUMENT){
                    String name=xmlPullParser.getName();
                    switch(event) {
                        case XmlPullParser.START_TAG:break;
                        case XmlPullParser.TEXT:break;
                        case XmlPullParser.END_TAG:
                        if (name.equals("autor")) {
                            autor.add(xmlPullParser.getAttributeValue(null, "value"));
                        }else if (name.equals("titlu")) {
                            titlu.add(xmlPullParser.getAttributeValue(null, "value"));
                        } else if (name.equals("gen")) {
                            gen.add(xmlPullParser.getAttributeValue(null, "value"));
                        } else if (name.equals("pret")) {
                            pret.add(xmlPullParser.getAttributeValue(null, "value"));
                        } else if (name.equals("aspect")) {
                            aspect.add(xmlPullParser.getAttributeValue(null, "value"));
                        } else if (name.equals("data")) {
                            data.add(xmlPullParser.getAttributeValue(null, "value"));
                        }
                            break;
                    }
                    event=xmlPullParser.next();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            stream.close();
            for(int i=0;i<getAutor().size();i++){
                Carte carte =new Carte();
                Calendar calendar=Calendar.getInstance();
                StringTokenizer stringTokenizer=new StringTokenizer(getData().get(i),":");
                carte.setAutor(getAutor().get(i));
                carte.setTitlu(getTitlu().get(i));
                carte.setGen(getGen().get(i));
                carte.setPret(Double.parseDouble(getPret().get(i)));
                if(getAspect().get(i).equals("nou")){
                    carte.setAspect(Aspect.nou);
                }else if(getAspect().get(i).equals("deteriorat")){
                    carte.setAspect(Aspect.deteriorat);
                }
                calendar.set(Integer.parseInt(stringTokenizer.nextToken()),Integer.parseInt(stringTokenizer.nextToken()),Integer.parseInt(stringTokenizer.nextToken()));
                carte.setDataPublicarii(calendar);
                MainActivity.list.add(carte);
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
