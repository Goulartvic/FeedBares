package enterprises.tanheta.feedbares.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import enterprises.tanheta.feedbares.R;

public class LoginActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setOnClickListener(R.id.btn_login);
        setOnClickListener(R.id.redirect_register);
    }

    private void setOnClickListener(int id) {
        findViewById(id).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.redirect_register: {
                Intent intent = new Intent(this, RegisterActivity.class);
                this.startActivity(intent);
                break;
            }
        }
    }
}
