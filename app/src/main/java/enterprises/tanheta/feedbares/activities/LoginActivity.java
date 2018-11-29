package enterprises.tanheta.feedbares.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import enterprises.tanheta.feedbares.R;
import enterprises.tanheta.feedbares.service.RequestService;

public class LoginActivity extends Activity implements View.OnClickListener{

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setOnClickListener(R.id.btn_login);
        setOnClickListener(R.id.redirect_register);
        sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        editor = sharedPreferences.edit();
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
            case R.id.btn_login: {
                doLogin();
                break;
            }
        }
    }

    private void doLogin() {
        EditText usernameEditText = findViewById(R.id.userInput);
        final String username = usernameEditText.getText().toString();
        EditText passwordEditText = findViewById(R.id.passwordInput);
        final String password = passwordEditText.getText().toString();
        Handler handler = new Handler(Looper.myLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                new Thread(requestLogin(username, password)).start();
            }
        });
    }

    private Runnable requestLogin(final String username, final String password) {
        final RequestService requestService = new RequestService();
        final Intent main = new Intent(this, MainActivity.class);
        final Intent initial = new Intent(this, InitialActivity.class);
        final Activity thisActivity = this;
        return new Runnable() {
            @Override
            public void run() {
                getMainLooper().prepare();
                boolean loginSuccessful = requestService.doLogin(username, password);
                if (loginSuccessful) {
                    editor.putString("username", username);
                    editor.commit();
                    thisActivity.startActivity(main);}
                else thisActivity.startActivity(initial);
            }
        };
    }
}
