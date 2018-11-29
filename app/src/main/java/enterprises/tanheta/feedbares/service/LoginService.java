package enterprises.tanheta.feedbares.service;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginService {

    OkHttpClient client;

    public boolean doLogin(String email, String password) {

        client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://35.199.110.220:2403").newBuilder();
        urlBuilder.addPathSegment("user");
        urlBuilder.addQueryParameter("email", email);
        urlBuilder.addQueryParameter("password", password);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            Log.e("LOGIN", e.getMessage());
            return false;
        }

        if (response.isSuccessful()) {
            return true;
        }
        else return false;
    }
}
