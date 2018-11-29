package enterprises.tanheta.feedbares.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
        EditText emailEditText = findViewById(R.id.userInput);
        String email = emailEditText.getText().toString();
        EditText passwordEditText = findViewById(R.id.passwordInput);
        String password = passwordEditText.getText().toString();
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            loginError().show();
        } else {
            Handler handler = new Handler();
            new Thread(requestLogin(email, password)).start();
        }
    }

    private AlertDialog loginError() {
        AlertDialog alert;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Erro");
        builder.setMessage("E-mail ou senha incorretos");
        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        alert = builder.create();
        return alert;
    }

    private Runnable requestLogin(final String email, final String password) {
        final LoginService loginService = new LoginService();
        final Intent intent = new Intent(this, MainActivity.class);
        final Activity thisActivity = this;
        return new Runnable() {
            @Override
            public void run() {
                boolean loginSuccessful = loginService.doLogin(email, password);
                if (loginSuccessful) {
                    thisActivity.startActivity(intent);
                } else loginError().show();
            }
        };
    }
}
