package com.ctech.andyeggs.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    private static final String EXTRA_CRIME_ID = "com.ctech.andyeggs.criminalintent.crime_id";

    public static Intent newIntent(Context packageContext, UUID crimeID) {
        Intent myIntent = new Intent(packageContext, CrimeActivity.class);
        myIntent.putExtra(EXTRA_CRIME_ID, crimeID);
        return myIntent;
    }

    @Override
    protected Fragment createFragment() {
        //get the crimeId that was passed to us from the CrimeList via the Intent
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        //call the "NewInstance" method which will create a new CrimeFragment and store the crimeId inside
        return CrimeFragment.newInstance(crimeId);
    }
}
