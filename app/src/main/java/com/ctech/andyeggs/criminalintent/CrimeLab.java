package com.ctech.andyeggs.criminalintent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ctech.andyeggs.criminalintent.database.CrimeBaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//CrimeLab - Singleton Class
public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context){
        mCrimes = new ArrayList<>();
        /*for (int i = 0; i < 100; i++) { CREATE 100 RANDOM CRIMES
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0); //every other one
            mCrimes.add(crime);
        }*/

        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
    }

    public void addCrime(Crime c) {
        mCrimes.add(c);
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime thisCrime : mCrimes) {
            if (thisCrime.getId().equals(id)) {
                return thisCrime;
            }
        }
        return null;
    }
}
