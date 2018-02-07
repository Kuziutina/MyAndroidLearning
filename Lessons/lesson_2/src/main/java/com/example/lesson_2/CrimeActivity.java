package com.example.lesson_2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CrimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        FragmentManager fm = getSupportFragmentManager();

        Fragment mCrimeFragment = fm.findFragmentById(R.id.crime_fragment);
        if (mCrimeFragment == null) {
            mCrimeFragment = new CrimeFragment();
            fm.beginTransaction()
                    .add(R.id.crime_fragment, mCrimeFragment)
                    .commit();
        }
    }
}
