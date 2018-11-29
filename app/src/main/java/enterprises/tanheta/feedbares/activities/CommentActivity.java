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

public class CommentActivity extends Activity implements View.OnClickListener{

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        setOnClickListener(R.id.submit_comment);
        setOnClickListener(R.id.redirect_main);
        sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
    }

    private void setOnClickListener(int id) {
        findViewById(id).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.redirect_main: {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.submit_comment: {
                doComment();
                break;
            }
        }
    }

    private void doComment() {
        Handler handler = new Handler(Looper.myLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                new Thread(registerComment()).start();
            }
        });
    }

    private Runnable registerComment() {
        final RequestService requestService = new RequestService();
        final Intent back = new Intent(this, MainActivity.class);
        final Activity thisActivity = this;
        final String username = sharedPreferences.getString("username", "Gabriel");
        final String id = getIntent().getStringExtra("pubId");
        EditText commentEditText = findViewById(R.id.do_comment_input);
        final String commentText = commentEditText.getText().toString();

        return new Runnable() {
            @Override
            public void run() {
                getMainLooper().prepare();
                boolean registerSuccessful = requestService.registerComment(commentText, id, username);
                if (registerSuccessful) thisActivity.startActivity(back);
                else thisActivity.startActivity(back);
            }
        };
    }
}
