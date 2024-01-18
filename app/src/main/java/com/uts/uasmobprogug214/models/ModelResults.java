package com.uts.uasmobprogug214.models;

import javax.annotation.Generated;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ModelResults {
    @SerializedName("skor")
    @Expose
    private String score;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("away")
    @Expose
    private String away;
    @SerializedName("home")
    @Expose
    private String home;

    public String getScore() { return score; }
    public void setScore(String score) {
        this.score = score;
    }
    public String getDate() {
        try {
            // Buat objek SimpleDateFormat untuk format input
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

            // Parse string tanggal input menjadi objek Date
            Date dateObject = inputDateFormat.parse(date);

            // Set zona waktu ke zona waktu Indonesia (WIB)
            inputDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));

            // Buat objek SimpleDateFormat untuk format output dan set zona waktu ke WIB
            SimpleDateFormat outputDateFormatObject = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            outputDateFormatObject.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));

            // Format objek Date ke string dengan format output
            return outputDateFormatObject.format(dateObject);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getAway() { return away; }
    public void setAway(String away) {
        this.away = away;
    }
    public String getHome() { return home; }
    public void setHome(String home) {this.home = home;
    }
}
