package com.ctech.andyeggs.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ctech.andyeggs.criminalintent.database.CrimeBaseHelper;
import com.ctech.andyeggs.criminalintent.database.CrimeDbSchema.CrimeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//CrimeLab - Singleton Class
public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
    }

    public void addCrime(Crime c) {
        ContentValues newValues = getContentValues(c);
        mDatabase.insert(CrimeTable.NAME, null, newValues);
    }

    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();
        return crimes;
    }

    public Crime getCrime(UUID id) {
        return null;
    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues myContentValues = new ContentValues();
        myContentValues.put(CrimeTable.Columns.UUID, crime.getId().toString());
        myContentValues.put(CrimeTable.Columns.TITLE, crime.getTitle());
        myContentValues.put(CrimeTable.Columns.DATE, crime.getDate().getTime());
        myContentValues.put(CrimeTable.Columns.SOLVED, crime.isSolved() ? 1 : 0);

        return myContentValues;
    }

    public void updateCrime(Crime c) {
        String crimeId = c.getId().toString();
        ContentValues newValues = getContentValues(c);

        String searchString = CrimeTable.Columns.UUID + " = ?";
        String[] searchArgs = new String[] { crimeId };

        mDatabase.update(CrimeTable.NAME, newValues, searchString, searchArgs);
    }

    private Cursor queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CrimeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return cursor;
    }
}
