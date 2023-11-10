package com.thiennvph2351.ph22351_crud_product;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.thiennvph2351.ph22351_crud_product.Adapter.CatAdapter;
import com.thiennvph2351.ph22351_crud_product.Adapter.ProductAdapter;
import com.thiennvph2351.ph22351_crud_product.DAO.CatDAO;
import com.thiennvph2351.ph22351_crud_product.DAO.ProductDAO;
import com.thiennvph2351.ph22351_crud_product.DTO.CatDTO;
import com.thiennvph2351.ph22351_crud_product.DTO.ProductDTO;

import java.util.List;

public class ProductActivity extends AppCompatActivity {

    RecyclerView rcPro;
    ProductAdapter productAdapter;
    ProductDAO productDAO;
    ProductDTO productDTO;
    List<ProductDTO> listProduct;
    Button btnAdd2, btnUPdate2, btnDELETE2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        rcPro = findViewById(R.id.rcPro);

        productDAO = new ProductDAO(this);
        listProduct = productDAO.getAll();
        productAdapter = new ProductAdapter(this, listProduct);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcPro.setLayoutManager(linearLayoutManager);
        rcPro.setAdapter(productAdapter);

        btnAdd2 = findViewById(R.id.btnAdd2);
        btnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogAdd2();
            }
        });

        btnUPdate2 = findViewById(R.id.btnUPdate2);
        btnUPdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogUPdate2();
            }
        });

        btnDELETE2 = findViewById(R.id.btnDELETE2);
        btnDELETE2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogDELETE2();
            }
        });
    }

    void  ShowDialogAdd2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ProductActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_dialog_product, null);
        builder.setView(v);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();

        EditText edtName2 = v.findViewById(R.id.edt_Name2);
        EditText edtPrice = v.findViewById(R.id.edtPrice);
        Button btnSave = v.findViewById(R.id.btnSave);
        Button btnOut  = v.findViewById(R.id.btnOut);
        Spinner spinner_cat = v.findViewById(R.id.spinner_cat);

        CatDAO catDAO = new CatDAO(ProductActivity.this);
        List<CatDTO> listCat = catDAO.getAll();
        CatAdapter catAdapter = new CatAdapter(listCat, ProductActivity.this);
        spinner_cat.setAdapter(catAdapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name2 = edtName2.getText().toString();
                int price = Integer.parseInt(edtPrice.getText().toString());
                int id_cat = (int) spinner_cat.getSelectedItemId();

                ProductDAO dao = new ProductDAO(ProductActivity.this);
                ProductDTO objProduct = new ProductDTO(name2, price, id_cat);
                int id = dao.AddRow(objProduct);
                if (id>0){
                    listProduct.clear();
                    listProduct.addAll(dao.getAll());
                    productAdapter.notifyDataSetChanged();
                    Toast.makeText(ProductActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else {
                    Toast.makeText(ProductActivity.this, "Lỗi ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    void  ShowDialogUPdate2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ProductActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_dialog_product, null);
        builder.setView(v);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();

        EditText edtName2 = v.findViewById(R.id.edt_Name2);
        EditText edtPrice = v.findViewById(R.id.edtPrice);
        Button btnSave = v.findViewById(R.id.btnSave);
        Button btnOut  = v.findViewById(R.id.btnOut);
        Spinner spinner_cat = v.findViewById(R.id.spinner_cat);

        CatDAO catDAO = new CatDAO(ProductActivity.this);
        List<CatDTO> listCat = catDAO.getAll();
        CatAdapter catAdapter = new CatAdapter(listCat, ProductActivity.this);
        spinner_cat.setAdapter(catAdapter);
        spinner_cat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                productDTO = listProduct.get(i);
                edtName2.setText(productDTO.getName2());
                edtPrice.setText((int) productDTO.getPrice());
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name2 = edtName2.getText().toString();
                int price = Integer.parseInt(edtPrice.getText().toString());
                int id_cat = (int) spinner_cat.getSelectedItemId();

                ProductDAO dao = new ProductDAO(ProductActivity.this);
                ProductDTO objProduct = new ProductDTO(name2, price, id_cat);
                int id = dao.UpdateRow(objProduct);
                if (id>0){
                    listProduct.clear();
                    listProduct.addAll(dao.getAll());
                    productAdapter.notifyDataSetChanged();
                    Toast.makeText(ProductActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else {
                    Toast.makeText(ProductActivity.this, "Lỗi ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    void  ShowDialogDELETE2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ProductActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_dialog_xoa_product, null);
        builder.setView(v);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();

        EditText edtName2 = v.findViewById(R.id.edt_Name2);
        EditText edtPrice = v.findViewById(R.id.edtPrice);
        Button btnDeleteDialog = v.findViewById(R.id.btnDeleteDialog);
        Button btnOut  = v.findViewById(R.id.btnOut);
        Spinner spinner_cat = v.findViewById(R.id.spinner_cat);

        CatDAO catDAO = new CatDAO(ProductActivity.this);
        List<CatDTO> listCat = catDAO.getAll();
        CatAdapter catAdapter = new CatAdapter(listCat, ProductActivity.this);
        spinner_cat.setAdapter(catAdapter);
        spinner_cat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                productDTO = listProduct.get(i);
                edtName2.setText(productDTO.getName2());
                edtPrice.setText((int) productDTO.getPrice());
            }
        });

        btnDeleteDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int kq = productDAO.DeleteRow(productDTO);
                if(kq>0){
                    listProduct.clear();
                    listProduct.addAll(productDAO.getAll());
                    productAdapter.notifyDataSetChanged();
                    Toast.makeText(ProductActivity.this, "Đã xóa", Toast.LENGTH_SHORT).show();
                    edtName2.setText("");
                    productDTO = null;
                }else {
                    Toast.makeText(ProductActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}