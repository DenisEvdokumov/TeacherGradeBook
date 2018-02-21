package com.example.teachergradebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity {

    private MainFragment mainFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = new MainFragment();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.activity_container,
                    mainFragment, mainFragment.getClass().getSimpleName()).commit();
        }

    }

    @Override
    protected void onDestroy() {
        mainFragment.onDestroy();
        super.onDestroy();
    }


}
