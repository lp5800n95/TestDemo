package com.liupeng3.systemappinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button all_app_btn=(Button) findViewById(R.id.button_all_app);
        //all_app_btn.setOnClickListener(this);
    }

    public void btnPM(View view){
        startActivity(new Intent(this,PMTestActivity.class));
    }

    public void btnAM(View view){
        startActivity(new Intent(this,AMProcessTestActivity.class));

    }

//    public void btnAllApp(View view){
//        Toast.makeText(MainActivity.this, "显示all app", Toast.LENGTH_LONG).show();
//    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.button_all_app:
//                Toast.makeText(MainActivity.this, "显示什么", Toast.LENGTH_LONG).show();
//                break;
//            default:
//                return;
//
//
//        }
//
//    }
}
