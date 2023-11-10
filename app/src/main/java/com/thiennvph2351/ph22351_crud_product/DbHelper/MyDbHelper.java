package com.thiennvph2351.ph22351_crud_product.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {

    static String DB_NAME = "nv1_crup";
    static int DB_VERSION = 1;
    public MyDbHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_tb_cat = "CREATE TABLE tb_cat ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE NOT NULL );";
        db.execSQL(sql_tb_cat);


        String sql_tb_product = "CREATE TABLE tb_product ( id INTEGER PRIMARY KEY AUTOINCREMENT, name2 TEXT NOT NULL, price INTEGER DEFAULT (0) NOT NULL,id_cat INTEGER NOT NULL CONSTRAINT fk_pro_cat REFERENCES tb_cat (id) );";
        db.execSQL(sql_tb_product);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
