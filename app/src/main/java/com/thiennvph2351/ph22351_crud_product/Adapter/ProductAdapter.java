package com.thiennvph2351.ph22351_crud_product.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thiennvph2351.ph22351_crud_product.DTO.ProductDTO;
import com.thiennvph2351.ph22351_crud_product.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolderProduct> {

    Context context;
    List<ProductDTO> list;

    public ProductAdapter(Context context, List<ProductDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolderProduct onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_row_product, parent, false);
        ViewHolderProduct vh = new ViewHolderProduct(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProduct holder, int position) {
        ProductDTO objProduct = list.get(position);
        holder.tv_id_product.setText(objProduct.getId() + "");
        holder.tv_tenCat.setText(objProduct.getName());
        holder.tv_tenProduct.setText(objProduct.getName2());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolderProduct extends RecyclerView.ViewHolder{
        TextView tv_id_product, tv_tenProduct, tv_tenCat;
        Button btn_Update, btn_Delete;

        public ViewHolderProduct(@NonNull View itemView) {
            super(itemView);
            tv_id_product = itemView.findViewById(R.id.tv_id_product);
            tv_tenCat = itemView.findViewById(R.id.tv_tenCat);
            tv_tenProduct = itemView.findViewById(R.id.tv_tenProduct);
            btn_Delete = itemView.findViewById(R.id.btn_Delete);
            btn_Update = itemView.findViewById(R.id.btn_Update);
        }
    }
}
