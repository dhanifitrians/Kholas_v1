package com.example.dhani.kholas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseSurat {

    @SerializedName("arti")
    @Expose
    private String arti;
    @SerializedName("asma")
    @Expose
    private String asma;
    @SerializedName("audio")
    @Expose
    private String audio;
    @SerializedName("ayat")
    @Expose
    private Integer ayat;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("nomor")
    @Expose
    private String nomor;
    @SerializedName("rukuk")
    @Expose
    private String rukuk;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("urut")
    @Expose
    private String urut;

    public String getArti() {
        return arti;
    }

    public void setArti(String arti) {
        this.arti = arti;
    }

    public String getAsma() {
        return asma;
    }

    public void setAsma(String asma) {
        this.asma = asma;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public Integer getAyat() {
        return ayat;
    }

    public void setAyat(Integer ayat) {
        this.ayat = ayat;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getRukuk() {
        return rukuk;
    }

    public void setRukuk(String rukuk) {
        this.rukuk = rukuk;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrut() {
        return urut;
    }

    public void setUrut(String urut) {
        this.urut = urut;
    }
}
