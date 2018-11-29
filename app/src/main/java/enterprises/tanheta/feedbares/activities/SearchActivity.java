package enterprises.tanheta.feedbares.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import enterprises.tanheta.feedbares.R;
import enterprises.tanheta.feedbares.adapter.SearchAdapter;
import enterprises.tanheta.feedbares.model.PubModel;

public class SearchActivity extends Activity {

    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<PubModel> pubsList;
    private RecyclerView searchRecyclerView;
    private String category;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        category = getIntent().getStringExtra("search");
        pubsList = new ArrayList<>();
        searchRecyclerView = findViewById(R.id.recycler_view_pubs);
        jsonRequest();
    }

    private void jsonRequest() {
        String jsonUrl = "http://35.199.110.220:2403/restaurant?category=" + category;
        request = new JsonArrayRequest(jsonUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        PubModel pub = new PubModel();
                        pub.setName(jsonObject.getString("name"));
                        pub.setAddress(jsonObject.getString("address"));
                        pub.setRating(jsonObject.getDouble("rating"));
                        pub.setCategory(jsonObject.getString("category"));
                        pub.setPhotoUrl(jsonObject.getString("photoUrl"));
                        pub.setId(jsonObject.getString("id"));
                        pubsList.add(pub);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setupSearchView(pubsList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(SearchActivity.this);
        requestQueue.add(request);
    }

    private void setupSearchView(List<PubModel> pubs) {
        SearchAdapter searchAdapter = new SearchAdapter(getApplicationContext(), pubs, this);
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchRecyclerView.setAdapter(searchAdapter);
    }
}
