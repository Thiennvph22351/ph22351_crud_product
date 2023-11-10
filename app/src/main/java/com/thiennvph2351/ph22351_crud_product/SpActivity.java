package com.thiennvph2351.ph22351_crud_product;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.thiennvph2351.ph22351_crud_product.Adapter.CatAdapter;
import com.thiennvph2351.ph22351_crud_product.DAO.CatDAO;
import com.thiennvph2351.ph22351_crud_product.DTO.CatDTO;

import java.util.List;

public class SpActivity extends AppCompatActivity {

    TextInputLayout edtTensp;
    Button btnAdd, btnUpdate, btnDelete;
    ListView lvSP;

    CatAdapter catAdapter;
    CatDTO objCurrentCat;
    CatDAO catDAO;
    List<CatDTO> list_cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);

        edtTensp = findViewById(R.id.ed_tenSp);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        lvSP =findViewById(R.id.lvSP);

        catDAO = new CatDAO(this);
        list_cat = catDAO.getAll();
        catAdapter = new CatAdapter(list_cat,this);
        lvSP.setAdapter(catAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenCat = edtTensp.getEditText().getText().toString();
                CatDTO catDTO = new CatDTO(tenCat);
                int id_new = catDAO.AddRow(catDTO);
                if (id_new>0){
                    list_cat.clear();
                    list_cat.addAll(catDAO.getAll());
                    catAdapter.notifyDataSetChanged();
                    Toast.makeText(SpActivity.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SpActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lvSP.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                objCurrentCat = list_cat.get(i);
                edtTensp.getEditText().setText(objCurrentCat.getName());
                return true;
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenCat = edtTensp.getEditText().getText().toString();
                objCurrentCat.setName(tenCat);
                int kq = catDAO.UpdateRow(objCurrentCat);
                if (kq>0){
                    list_cat.clear();
                    list_cat.addAll(catDAO.getAll());
                    catAdapter.notifyDataSetChanged();
                    Toast.makeText(SpActivity.this, "Đã update", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SpActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int kq = catDAO.DeleteRow(objCurrentCat);
                if (kq>0){
                    list_cat.clear();
                    list_cat.addAll(catDAO.getAll());
                    catAdapter.notifyDataSetChanged();
                    Toast.makeText(SpActivity.this, "Đã xóa", Toast.LENGTH_SHORT).show();
                    edtTensp.getEditText().setText("");
                    objCurrentCat = null;
                }else {
                    Toast.makeText(SpActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}