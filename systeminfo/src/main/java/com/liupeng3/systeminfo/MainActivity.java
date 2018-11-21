package com.liupeng3.systeminfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btnAM(View view){
        startActivity(new Intent(this,AMProcessTestActivity.class));
    }

    public void btnPM(View view){
        startActivity(new Intent(this,PMTestActivity.class));
    }
}
