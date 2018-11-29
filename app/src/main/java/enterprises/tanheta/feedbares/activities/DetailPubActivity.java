package enterprises.tanheta.feedbares.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import enterprises.tanheta.feedbares.R;
import enterprises.tanheta.feedbares.adapter.CommentAdapter;
import enterprises.tanheta.feedbares.model.CommentModel;

public class DetailPubActivity extends Activity implements View.OnClickListener{

    private SharedPreferences sharedPreferences;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<CommentModel> comments;
    private RecyclerView commentRecyclerView;
    private String pubId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        pubId = getIntent().getStringExtra("pubId");
        comments = new ArrayList<>();
        commentRecyclerView = findViewById(R.id.recycler_view_comment);
        setViews();
        setOnClickListener(R.id.comment_btn);
        jsonRequest();
    }
    private void setOnClickListener(int id) {
        findViewById(id).setOnClickListener(this);
    }


    private void setViews() {
        TextView pubName = findViewById(R.id.pub_name_detail);
        TextView pubAddress = findViewById(R.id.pub_address_detail);
        TextView pubRating = findViewById(R.id.pub_rating_detail);
        ImageView pubImg = findViewById(R.id.pub_image_detail);
        pubName.setText(getIntent().getStringExtra("pubName"));
        pubAddress.setText(getIntent().getStringExtra("pubAddress"));
        pubRating.setText(Double.toString(getIntent().getDoubleExtra("pubRating", 5.0)));
        Picasso.with(this).load(getIntent().getStringExtra("pubPhotoUrl")).placeholder(R.mipmap.ic_launcher_round).into(pubImg);
    }

    private void jsonRequest() {
        String jsonUrl = "http://35.199.110.220:2403/comment?idRestaurant=" + pubId;
        request = new JsonArrayRequest(jsonUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        CommentModel comment = new CommentModel();
                        comment.setUserName(jsonObject.getString("nameuser"));
                        comment.setCommentary(jsonObject.getString("comment"));
                        comments.add(comment);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setupCommentView(comments);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(DetailPubActivity.this);
        requestQueue.add(request);
    }

    private void setupCommentView(List<CommentModel> comments) {
        CommentAdapter commentAdapter = new CommentAdapter(getApplicationContext(), comments);
        commentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        commentRecyclerView.setAdapter(commentAdapter);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.comment_btn: {
                Intent intent = new Intent(this, CommentActivity.class);
                intent.putExtra("pubId", pubId);
                startActivity(intent);
            }
        }
    }
}
