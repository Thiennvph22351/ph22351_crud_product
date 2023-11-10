package com.thiennvph2351.ph22351_crud_product.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thiennvph2351.ph22351_crud_product.DTO.CatDTO;
import com.thiennvph2351.ph22351_crud_product.DTO.ProductDTO;
import com.thiennvph2351.ph22351_crud_product.DTO.ProductDTO;
import com.thiennvph2351.ph22351_crud_product.DbHelper.MyDbHelper;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    MyDbHelper myDbHelper;
    SQLiteDatabase db;

    public ProductDAO(Context context){
        myDbHelper = new MyDbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }

    public int AddRow(ProductDTO objProduct){
        ContentValues values = new ContentValues();
        values.put("name2", objProduct.getName2());
        values.put("price",objProduct.getPrice());
        values.put("id_cat", objProduct.getId_cat());
        return (int) db.insert("tb_product",null,values);
    }

    public int UpdateRow(ProductDTO objProduct){
        ContentValues values = new ContentValues();
        values.put("name2", objProduct.getName2());
        values.put("price",objProduct.getPrice());
        values.put("id_cat", objProduct.getId_cat());
        String[] dieukien = new String[]{String.valueOf(objProduct.getId())};

        return db.update("tb_product",values,"id = ?", dieukien);
    }

    public int DeleteRow(ProductDTO objProduct){

        String[] dieukien = new String[]{String.valueOf(objProduct.getId())};

        return db.delete("tb_product","id = ?", dieukien);
    }

    public List<ProductDTO> getAll(){
        List<ProductDTO> listProduct = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT tb_product.id, name2, price, id_cat, name FROM tb_product INNER JOIN tb_cat ON tb_cat.id = product.id_cat",null, null);
        if (c!=null && c.getCount()>0){
            c.moveToFirst();
            while (!c.isAfterLast()){
                int id_product = c.getInt(0);
                String name2 = c.getString(1);
                int price = c.getInt(2);
                int id_cat = c.getInt(3);
                String name = c.getString(4);

                ProductDTO objProduct = new ProductDTO(id_product, name2, price,id_cat, name);
                listProduct.add(objProduct);
                c.moveToNext();
            }
        }
        return listProduct;
    }
}
