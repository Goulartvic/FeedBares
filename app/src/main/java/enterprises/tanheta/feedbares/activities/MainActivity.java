package enterprises.tanheta.feedbares.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import enterprises.tanheta.feedbares.R;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOnClickListener(R.id.search_name);
    }

    private void setOnClickListener(int id) {
        findViewById(id).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_name: {
                EditText editText = findViewById(R.id.search_name);
                editText.setText("");
                break;
            }
        }
    }
}
