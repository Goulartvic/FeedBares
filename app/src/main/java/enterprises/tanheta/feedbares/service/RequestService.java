package enterprises.tanheta.feedbares.service;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RequestService {

    OkHttpClient client;

    public boolean doLogin(String username, String password) {

        client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://35.199.110.220:2403").newBuilder();
        urlBuilder.addPathSegment("user");
        urlBuilder.addPathSegment("login");
        String url = urlBuilder.build().toString();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), "{\"username\": \""+ username +"\", \"password\": \""+password+"\"}");

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            Log.e("LOGIN", e.getMessage());
            return false;
        }
        if (response.code()==200) {
            return true;
        }
        else return false;
    }

    public boolean registerUser(String username, String password, String email) {

        client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://35.199.110.220:2403").newBuilder();
        urlBuilder.addPathSegment("user");
        String url = urlBuilder.build().toString();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),
                "{\"username\": \""+ username +"\",\"email\": \""+email+"\", \"password\": \""+password+"\"}");

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            Log.e("LOGIN", e.getMessage());
            return false;
        }
        if (response.code()==200) {
            return true;
        }
        else return false;
    }

    public boolean registerComment(String comment, String pubId, String userName) {

        client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://35.199.110.220:2403").newBuilder();
        urlBuilder.addPathSegment("comment");
        String url = urlBuilder.build().toString();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),
                "{\"idRestaurant\": \""+ pubId +"\",\"comment\": \""+comment+"\", \"nameuser\": \""+userName+"\"}");

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            Log.e("LOGIN", e.getMessage());
            return false;
        }
        if (response.code()==200) {
            return true;
        }
        else return false;
    }
}
