package com.thiennvph2351.ph22351_crud_product.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thiennvph2351.ph22351_crud_product.DTO.CatDTO;
import com.thiennvph2351.ph22351_crud_product.DbHelper.MyDbHelper;

import java.util.ArrayList;
import java.util.List;

public class CatDAO {
    MyDbHelper myDbHelper;
    SQLiteDatabase db;

    public CatDAO (Context context){
        myDbHelper = new MyDbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }

    public int AddRow(CatDTO objCat){
        ContentValues values = new ContentValues();
        values.put("name", objCat.getName());
        return (int) db.insert("tb_cat",null,values);
    }

    public int UpdateRow(CatDTO objCat){
        ContentValues values = new ContentValues();
        values.put("name", objCat.getName());

        String[] dieukien = new String[]{String.valueOf(objCat.getId())};

        return db.update("tb_cat",values,"id = ?", dieukien);
    }

    public int DeleteRow(CatDTO objCat){

        String[] dieukien = new String[]{String.valueOf(objCat.getId())};

        return db.delete("tb_cat","id = ?", dieukien);
    }

    public List<CatDTO> getAll(){
        List<CatDTO> list_cat = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT *FROM tb_cat ORDER BY id ASC", null);
        if (c!=null && c.getCount()>0){
            c.moveToFirst();
            do {
                CatDTO objCat = new CatDTO();
                objCat.setId(  c.getInt(0 )  );
                objCat.setName(  c.getString(1 )  );
                list_cat.add( objCat );
            }while (c.moveToNext());
        }
        return list_cat;
    }
}
