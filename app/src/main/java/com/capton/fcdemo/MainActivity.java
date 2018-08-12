package com.capton.fcdemo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.capton.fc.FileChooser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileChooser fileChooser = new FileChooser(MainActivity.this, new FileChooser.FileChoosenListener() {
                    @Override
                    public void onFileChoosen(String filePath) {
                        ((TextView) findViewById(R.id.path)).setText(filePath);
                    }
                });

                fileChooser.setThemeColor(R.color.colorPrimary)
                        .setTitle("选择目录")
                        .setCurrentPath("/storage/emulated/0")
                        .setBackIconRes(R.drawable.back_white)
                        .setDoneText("OK")
                        .showFile(true)
                        .open();
            }
        });

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileChooser fileChooser = new FileChooser(MainActivity.this, new FileChooser.FileChoosenListener() {
                    @Override
                    public void onFileChoosen(String filePath) {
                        ((TextView) findViewById(R.id.path)).setText(filePath);
                    }
                });

                fileChooser.setThemeColor(R.color.colorPrimary)
                        .setTitle("选择目录")
                        .setCurrentPath("/storage/emulated/0")
                        .setBackIconRes(R.drawable.back_white)
                        .setDoneText("OK")
                        .showFile(false)
                        .open();
            }
        });
    }
}
