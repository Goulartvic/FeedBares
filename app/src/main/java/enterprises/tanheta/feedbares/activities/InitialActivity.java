package enterprises.tanheta.feedbares.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import enterprises.tanheta.feedbares.R;

public class InitialActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        setOnClickListener(R.id.sign_in);
        setOnClickListener(R.id.sign_up);
    }

    private void setOnClickListener(int id) {
        findViewById(id).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in: {
                Intent intent = new Intent(this, LoginActivity.class);
                this.startActivity(intent);
                break;
            }
            case R.id.sign_up: {
                Intent intent = new Intent(this, RegisterActivity.class);
                this.startActivity(intent);
                break;
            }
        }

    }
}
