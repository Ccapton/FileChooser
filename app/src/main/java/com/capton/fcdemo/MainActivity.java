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

      /*  FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        final DemoFragment demoFragment = new DemoFragment();
        transaction.add(R.id.fragment,demoFragment);
        transaction.commitNow();
     */

        findViewById(R.id.hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileChooser fileChooser = new FileChooser(MainActivity.this, new FileChooser.FileChoosenListener() {
                    @Override
                    public void onFileChoosen(String filePath) {
                        ((TextView)findViewById(R.id.hello)).setText(filePath);
                    }
                });
                 fileChooser.open();
             }
        });


    }


}
