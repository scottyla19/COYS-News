package com.example.android.coys;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import static android.R.attr.format;

public class Result {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("sectionId")
    @Expose
    private String sectionId;
    @SerializedName("sectionName")
    @Expose
    private String sectionName;
    @SerializedName("webPublicationDate")
    @Expose
    private String webPublicationDate;
    @SerializedName("webTitle")
    @Expose
    private String webTitle;

    @Override
    public String toString() {
        return "Result{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", sectionId='" + sectionId + '\'' +
                ", sectionName='" + sectionName + '\'' +
                ", webPublicationDate='" + webPublicationDate + '\'' +
                ", webTitle='" + webTitle + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", apiUrl='" + apiUrl + '\'' +
                ", isHosted=" + isHosted +
                '}';
    }

    @SerializedName("webUrl")
    @Expose
    private String webUrl;
    @SerializedName("apiUrl")
    @Expose
    private String apiUrl;
    @SerializedName("isHosted")
    @Expose
    private Boolean isHosted;

    /**
     * No args constructor for use in serialization
     *
     */
    public Result() {
    }

    /**
     *
     * @param id
     * @param webUrl
     * @param sectionId
     * @param isHosted
     * @param apiUrl
     * @param sectionName
     * @param webTitle
     * @param type
     * @param webPublicationDate
     */
    public Result(String id, String type, String sectionId, String sectionName, String webPublicationDate, String webTitle, String webUrl, String apiUrl, Boolean isHosted) {
        super();
        this.id = id;
        this.type = type;
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.webPublicationDate = webPublicationDate;
        this.webTitle = webTitle;
        this.webUrl = webUrl;
        this.apiUrl = apiUrl;
        this.isHosted = isHosted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public void setWebPublicationDate(String webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public Boolean getIsHosted() {
        return isHosted;
    }

    public void setIsHosted(Boolean isHosted) {
        this.isHosted = isHosted;
    }

    public String getTimeOnly(){
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-dd-MM'T'hh:mm:ss'Z'");
        Date date = null;
        try {
            date = inFormat.parse(this.getWebPublicationDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outFormat = new SimpleDateFormat("hh:mm aaa z");
        return outFormat.format(date);

    }

    public String getDateOnly(){
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        Date date = null;
        try {
            date = inFormat.parse(this.getWebPublicationDate());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat outFormat = new SimpleDateFormat("MMM dd, yyyy");
        return outFormat.format(date);

    }

}