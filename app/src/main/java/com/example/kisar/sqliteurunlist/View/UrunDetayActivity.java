package com.example.kisar.sqliteurunlist.View;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.kisar.sqliteurunlist.Model.Urun;
import com.example.kisar.sqliteurunlist.Model.dbUrun;
import com.example.kisar.sqliteurunlist.R;

public class UrunDetayActivity extends AppCompatActivity  {

    Button btnKaydet,btnIptal;
    EditText eturunAdi,eturunBirimfiyat,eturunstok;
    LinearLayout lybase;
    Urun urun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_detay);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        eturunAdi = (EditText) findViewById(R.id.eturunAdi);
        eturunBirimfiyat= (EditText) findViewById(R.id.eturunbirifiyat);
        eturunstok= (EditText) findViewById(R.id.eturunmiktar);

        btnKaydet= (Button) findViewById(R.id.btnkaydet);
        btnIptal= (Button) findViewById(R.id.btniptal);

        lybase= (LinearLayout) findViewById(R.id.lybaselayout);
        //TANIMLAMALAR
        urun= (Urun) getIntent().getSerializableExtra("guncelle");

        if(urun!=null){
            eturunAdi.setText(urun.getUrunAdi());
            eturunstok.setText(String.valueOf(urun.getStokSayisi()));
            eturunBirimfiyat.setText(String.valueOf(urun.getBirimFiyat()));

        }

        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urunKaydet();
            }
        });
        btnIptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UrunDetayActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home) {
            Intent i = new Intent(UrunDetayActivity.this, MainActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }





    public void urunKaydet(){

        if(eturunAdi.getText().toString().equals("")){
            formHata(R.string.detayformhata);

        }else if(eturunstok.getText().equals("")){
            formHata(R.string.detayformhata);
        }else if(eturunBirimfiyat.getText().toString().equals("")){
            formHata(R.string.detayformhata);
        }
        if(urun==null){
            dbUrun db=new dbUrun(UrunDetayActivity.this);
            urun=new Urun();
            urun.setUrunAdi(eturunAdi.getText().toString());
            urun.setStokSayisi((Float.valueOf(eturunstok.getText().toString())));
            urun.setBirimFiyat(Float.valueOf(eturunBirimfiyat.getText().toString()));
            float deger= db.kayitEkle(urun);

            if(deger==-1){
                formHata(R.string.detaykayithata);
            }else {
                eturunAdi.setText("");
                eturunBirimfiyat.setText("");
                eturunstok.setText("");
            }

        }else{
            dbUrun db=new dbUrun(UrunDetayActivity.this);
            urun.setUrunAdi(eturunAdi.getText().toString());
            urun.setStokSayisi((Float.valueOf(eturunstok.getText().toString())));
            urun.setBirimFiyat(Float.valueOf(eturunBirimfiyat.getText().toString()));
            float deger= db.urunGuncelle(urun);
            if(deger==-1){
                formHata(R.string.detaykayithata);
            }else {
                Intent i = new Intent(UrunDetayActivity.this,MainActivity.class);
                startActivity(i);
            }




        }
        Snackbar snb= Snackbar.make((View)lybase,R.string.dateyKayitBsarili,Snackbar.LENGTH_LONG);
        snb.setAction(R.string.dateyUruneGit, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UrunDetayActivity.this,UrunDetayViewActivity.class);
                i.putExtra("urunDetay",urun);
                startActivity(i);
            }
        });
        snb.show();

    }

    private void formHata(int s) {
        Snackbar.make((View)lybase,s,Snackbar.LENGTH_LONG).show();
    }
}
