package com.think.bga;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.demonstrate.DialogUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn) {
            showListDg();
        }
    }

    private void showListDg() {
        DialogUtil.showListDialog(this, "", new String[]{
                "0,列表0",
                "1,列表,RETROGIT!",
                "2,列表,rxjava!",
                "3,列表,rxjava操作符!",
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        startActivity(new Intent(MainActivity.this,BGARefreshLayout0Activity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this,BGARefreshLayout1Activity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this,BGARefreshLayout2Activity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this,BGARefreshLayout3Activity.class));
                        break;
                }
            }
        });
    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(MainActivity.this);
    }
}
