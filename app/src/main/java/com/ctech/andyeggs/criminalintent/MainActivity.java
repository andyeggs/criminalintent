package com.ctech.andyeggs.criminalintent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment myCrimeFragment = fm.findFragmentById(R.id.fragment_container);

        if (myCrimeFragment == null) {
            myCrimeFragment = new CrimeFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, myCrimeFragment)
                    .commit();
        }
    }
}
