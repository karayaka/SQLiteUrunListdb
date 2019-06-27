package com.example.kisar.sqliteurunlist.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kisar.sqliteurunlist.Model.Urun;
import com.example.kisar.sqliteurunlist.Model.dbUrun;
import com.example.kisar.sqliteurunlist.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lsturunler;
    List<Urun> urunObjList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lsturunler= (ListView) findViewById(R.id.lsturunler);
        lsturunler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.alerttitle);
                builder.setMessage(R.string.alertMesaj);
                builder.setPositiveButton(R.string.alertbtnguncelle, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(MainActivity.this,UrunDetayActivity.class);
                        i.putExtra("guncelle",urunObjList.get(position));
                        startActivity(i);
                    }
                });
                builder.setNegativeButton(R.string.alertbtniptal, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNeutralButton(R.string.alertbtnsil, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbUrun urun=new dbUrun(MainActivity.this);
                        urun.urunSil(urunObjList.get(position).getId());
                        kayitListele();

                    }
                });

                builder.show();
            }
        });
            try {
                kayitListele();
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),R.string.lstuyari,Toast.LENGTH_SHORT).show();
            }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,UrunDetayActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void kayitListele(){
        dbUrun db=new dbUrun(getApplicationContext());

        List<String> urunList=new ArrayList<>();
        urunObjList=db.kayitListele();
        for(Urun urun:urunObjList){
            urunList.add("ID: "+urun.getId()+"-Adı: "+urun.getUrunAdi()+"-Fiyatı: "+urun.getBirimFiyat()+"-Stok: "+urun.getStokSayisi());
        }
        ArrayAdapter<String >adapter=
                new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,urunList);

           lsturunler.setAdapter(adapter);
    }
}
