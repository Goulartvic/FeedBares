package enterprises.tanheta.feedbares.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PatternMatcher;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import enterprises.tanheta.feedbares.R;
import enterprises.tanheta.feedbares.service.LoginService;

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
        final LoginService loginService = new LoginService();
        final Intent main = new Intent(this, MainActivity.class);
        final Intent initial = new Intent(this, InitialActivity.class);
        final Activity thisActivity = this;
        return new Runnable() {
            @Override
            public void run() {
                getMainLooper().prepare();
                boolean loginSuccessful = loginService.doLogin(username, password);
                if (loginSuccessful) thisActivity.startActivity(main);
                else thisActivity.startActivity(initial);
            }
        };
    }
}
