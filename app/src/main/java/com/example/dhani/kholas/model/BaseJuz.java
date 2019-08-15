package com.example.dhani.kholas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseJuz {
    @SerializedName("juz_ke")
    @Expose
    private String juzKe;
    @SerializedName("juz")
    @Expose
    private String juz;

    public String getJuzKe() {
        return juzKe;
    }

    public void setJuzKe(String juzKe) {
        this.juzKe = juzKe;
    }

    public String getJuz() {
        return juz;
    }

    public void setJuz(String juz) {
        this.juz = juz;
    }
}
