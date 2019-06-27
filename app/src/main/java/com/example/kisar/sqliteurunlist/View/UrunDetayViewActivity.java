package com.example.kisar.sqliteurunlist.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.kisar.sqliteurunlist.Model.Urun;
import com.example.kisar.sqliteurunlist.R;

public class UrunDetayViewActivity extends AppCompatActivity {

    TextView eturunadi,eturustok,eturunfiyat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_detay_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Urun urun= (Urun) getIntent().getSerializableExtra("urunDetay");
        eturunadi= (TextView) findViewById(R.id.etviewurunadi);
        eturustok= (TextView) findViewById(R.id.etviewstokmiktarı);
        eturunfiyat= (TextView) findViewById(R.id.etviewbirimfiyat);
        if(urun!=null){
            eturunadi.setText(urun.getUrunAdi());
            eturustok.setText(String.valueOf(urun.getStokSayisi()));
            eturunfiyat.setText(String.valueOf(urun.getBirimFiyat()));
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home) {
            Intent i = new Intent(UrunDetayViewActivity.this, MainActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
