package com.example.kisar.sqliteurunlist.Model;

import java.io.Serializable;

/**
 * Created by kisar on 27.06.2019.
 */

public class Urun implements Serializable {
    private int Id;
    private String urunAdi;
    private float birimFiyat;
    private float stokSayisi;

    public Urun() {
    }


    public Urun(String urunAdi, float birimFiyat, float stokSayisi, int Id) {
        this.setUrunAdi(urunAdi);
        this.setBirimFiyat(birimFiyat);
        this.setStokSayisi(stokSayisi);
        this.setId(Id);

    }


    public String getUrunAdi() {
        return urunAdi;
    }

    public void setUrunAdi(String urunAdi) {
        this.urunAdi = urunAdi;
    }

    public float getBirimFiyat() {
        return birimFiyat;
    }

    public void setBirimFiyat(float birimFiyat) {
        this.birimFiyat = birimFiyat;
    }

    public float getStokSayisi() {
        return stokSayisi;
    }

    public void setStokSayisi(float stokSayisi) {
        this.stokSayisi = stokSayisi;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
