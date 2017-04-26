package com.example.android.coys;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Article {

    @SerializedName("response")
    @Expose
    private Response response;

    /**
     * No args constructor for use in serialization
     *
     */
    public Article() {
    }

    /**
     *
     * @param response
     */
    public Article(Response response) {
        super();
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Article{" +
                "response=" + response +
                '}';
    }
}

