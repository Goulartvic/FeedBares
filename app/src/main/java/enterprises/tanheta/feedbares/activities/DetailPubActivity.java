package enterprises.tanheta.feedbares.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import enterprises.tanheta.feedbares.R;

public class DetailPubActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setViews();
    }

    private void setViews() {
        TextView pubName = findViewById(R.id.pub_name_detail);
        TextView pubAddress = findViewById(R.id.pub_address_detail);
        TextView pubRating = findViewById(R.id.pub_rating_detail);
        pubName.setText(getIntent().getStringExtra("pubName"));
        pubAddress.setText(getIntent().getStringExtra("pubAddress"));
        pubRating.setText(getIntent().getStringExtra("pubRating"));
    }
}
