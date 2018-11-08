package enterprises.tanheta.feedbares.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import enterprises.tanheta.feedbares.R;

public class RegisterActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setOnClickListener(R.id.btn_register);
        setOnClickListener(R.id.redirect_login);
    }

    private void setOnClickListener(int id) {
        findViewById(id).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.redirect_login: {
                Intent intent = new Intent(this, LoginActivity.class);
                this.startActivity(intent);
                break;
            }
        }
    }
}
