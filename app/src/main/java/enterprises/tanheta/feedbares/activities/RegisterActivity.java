package enterprises.tanheta.feedbares.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import enterprises.tanheta.feedbares.R;
import enterprises.tanheta.feedbares.service.LoginService;

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
            case R.id.btn_register: {
                doRegister();
                break;
            }
        }
    }

    private void doRegister() {
        EditText usernameEditText = findViewById(R.id.register_name_input);
        final String username = usernameEditText.getText().toString();
        EditText passwordEditText = findViewById(R.id.register_password);
        final String password = passwordEditText.getText().toString();
        EditText emailEditText = findViewById(R.id.register_email_input);
        final String email = emailEditText.getText().toString();
        EditText confirmEditText = findViewById(R.id.confirm_password_input);
        final String confirmPassword = confirmEditText.getText().toString();
        if (password.equals(confirmPassword) && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Handler handler = new Handler(Looper.myLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    new Thread(registerUser(username, password, email)).start();
                }
            });
        } else registerError().show();
    }

    private AlertDialog registerError() {
        AlertDialog alert;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Erro");
        builder.setMessage("E-mail inválido ou senhas não conferem");
        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        alert = builder.create();
        return alert;
    }

    private Runnable registerUser(final String email, final String password, final String username) {
        final LoginService loginService = new LoginService();
        final Intent login = new Intent(this, LoginActivity.class);
        final Intent initial = new Intent(this, InitialActivity.class);
        final Activity thisActivity = this;
        return new Runnable() {
            @Override
            public void run() {
                getMainLooper().prepare();
                boolean registerSuccessful = loginService.registerUser(username, password, email);
                if (registerSuccessful) thisActivity.startActivity(login);
                else thisActivity.startActivity(initial);
            }
        };
    }
}
