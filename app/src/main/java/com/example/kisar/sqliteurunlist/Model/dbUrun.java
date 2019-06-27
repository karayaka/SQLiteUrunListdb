package com.example.kisar.sqliteurunlist.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kisar on 27.06.2019.
 */

public class dbUrun extends SQLiteOpenHelper {
    //Db bilgileri
    private static final String VERITABANI="urundb";
    private static final int VERSION=1;
    private static final String TABLOADI="urunler";
    //Tablo bilgileri
    private static final String ID="id";
    private static final String URUNADI="urunadi";
    private static final String URUNBIRIMFIYATI="urunbirimfiyat";
    private static final String URUNSTOKSAYISI="stoksayisi";

    public dbUrun(Context context) {
        super(context, VERITABANI, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tablo_olustur="CREATE TABLE "+TABLOADI+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                URUNADI+" TEXT, "+
                URUNBIRIMFIYATI+" REAL NOT NULL,"+
                URUNSTOKSAYISI+" REAL NOT NULL);";
        db.execSQL(tablo_olustur);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLOADI);
        onCreate(db);
    }

    public long kayitEkle(Urun urun){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(URUNADI,urun.getUrunAdi());
        cv.put(URUNBIRIMFIYATI,urun.getBirimFiyat());
        cv.put(URUNSTOKSAYISI,urun.getStokSayisi());
        long id=db.insert(TABLOADI,null,cv);
        db.close();
        return id;
    }
    public List<Urun> kayitListele(){
        SQLiteDatabase db=this.getReadableDatabase();
        String[] kolonlar={ID,URUNADI,URUNSTOKSAYISI,URUNBIRIMFIYATI};
        Cursor c=db.query(TABLOADI,kolonlar,null,null,null,null,null);
        int indexOfId=c.getColumnIndex(ID);
        int indexOfUrunAdi=c.getColumnIndex(URUNADI);
        int indexOfUrunBirimFitai=c.getColumnIndex(URUNBIRIMFIYATI);
        int indexOfUrunStok=c.getColumnIndex(URUNSTOKSAYISI);
        List<Urun> urunlist=new ArrayList<Urun>();
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            Urun urun=new Urun();
            urun.setId(c.getInt(indexOfId));
            urun.setUrunAdi(c.getString(indexOfUrunAdi));
            urun.setBirimFiyat(c.getLong(indexOfUrunBirimFitai));
            urun.setStokSayisi(c.getLong(indexOfUrunStok));
            urunlist.add(urun);
        }

        db.close();
        return urunlist;
    }
    public long urunSil(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        long silinenUrunId= db.delete(TABLOADI,ID+"="+id,null);
        db.close();
        return silinenUrunId;
    }
    public long urunGuncelle(Urun urun){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(URUNADI,urun.getUrunAdi());
        cv.put(URUNBIRIMFIYATI,urun.getBirimFiyat());
        cv.put(URUNSTOKSAYISI,urun.getStokSayisi());
       long donenId= db.update(TABLOADI,cv,ID+"="+urun.getId(),null);
        db.close();
        return donenId;

    }
}

