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
        setOnClickListener(R.id.btn_beach);
        setOnClickListener(R.id.btn_buffet);
        setOnClickListener(R.id.btn_college);
        setOnClickListener(R.id.btn_family);
        setOnClickListener(R.id.btn_fastfood);
        setOnClickListener(R.id.btn_foodtruck);
        setOnClickListener(R.id.btn_junky);
        setOnClickListener(R.id.btn_narguile);
        setOnClickListener(R.id.btn_pub);
        setOnClickListener(R.id.btn_romantic);
        setOnClickListener(R.id.btn_soft);
        setOnClickListener(R.id.search_btn);
    }

    private void setOnClickListener(int id) {
        findViewById(id).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditText editText = findViewById(R.id.search_name);
        Intent intent = new Intent(this, SearchActivity.class);
        switch (view.getId()) {
            case R.id.search_name: {
                editText.setText("");
                break;
            }
            case R.id.search_btn: {
                intent.putExtra("custom", "true");
                intent.putExtra("search", editText.getText().toString());
                this.startActivity(intent);
                break;
            }
            case R.id.btn_beach: {
                intent.putExtra("search", "PRAIANO");
                this.startActivity(intent);
                break;
            }
            case R.id.btn_buffet: {
                intent.putExtra("search", "BUFFET");
                this.startActivity(intent);
                break;
            }
            case R.id.btn_college: {
                intent.putExtra("search", "UNIVERSITARIO");
                this.startActivity(intent);
                break;
            }
            case R.id.btn_family: {
                intent.putExtra("search", "FAMILIA");
                this.startActivity(intent);
                break;
            }
            case R.id.btn_fastfood: {
                intent.putExtra("search", "FASTFOOD");
                this.startActivity(intent);
                break;
            }
            case R.id.btn_foodtruck: {
                intent.putExtra("search", "FOODTRUCK");
                this.startActivity(intent);
                break;
            }
            case R.id.btn_junky: {
                intent.putExtra("search", "PODRAO");
                this.startActivity(intent);
                break;
            }
            case R.id.btn_narguile: {
                intent.putExtra("search", "NARGUILE");
                this.startActivity(intent);
                break;
            }
            case R.id.btn_pub: {
                intent.putExtra("search", "PUB");
                this.startActivity(intent);
                break;
            }
            case R.id.btn_romantic: {
                intent.putExtra("search", "ROMANTICO");
                this.startActivity(intent);
                break;
            }
            case R.id.btn_soft: {
                intent.putExtra("search", "SOFISTICADO");
                this.startActivity(intent);
                break;
            }

        }
    }
}
