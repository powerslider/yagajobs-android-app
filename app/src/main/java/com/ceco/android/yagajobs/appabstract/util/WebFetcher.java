package com.ceco.android.yagajobs.appabstract.util;

import android.util.Log;
import android.widget.Toast;

import com.ceco.android.yagajobs.app.model.Vacancy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import javax.xml.transform.Result;

/**
 * Created by ceco on 26.05.14.
 */
public class WebFetcher {

    private List response;

    private HttpEntity entity;

    private Type listType;

    private WebFetcher(String url) {
        retrieveStream(url);
    }

    public static WebFetcher newInstance(String url) {
        return new WebFetcher(url);
    }

    public List getResponse() {
        return response;
    }

    public WebFetcher setListType(Type listType) {
        this.listType = listType;
        return this;
    }

    public void retrieveStream(String url) {
        HttpClient client = new DefaultHttpClient();
        HttpGet getRequest = new HttpGet(url);
        try {
            HttpResponse getResponse = client.execute(getRequest);
            final int statusCode = getResponse.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {
                Log.w("WebError", "Error " + statusCode + " for URL " + url);
            }
            entity = getResponse.getEntity();

        } catch (IOException e) {
            getRequest.abort();
            Log.w("WebError", "Error for URL " + url, e);
        }
    }

    public WebFetcher parseJsonResponse() {
        String json = null;
        try {
            json = EntityUtils.toString(entity);
        } catch (IOException e) {
            Log.e("IOError", "Cannot resolve html entity to string!");
        }
        Gson gson = new GsonBuilder()
            .enableComplexMapKeySerialization()
            .setPrettyPrinting()
            .create();
        response = gson.fromJson(json, listType);

        return this;
    }
}
