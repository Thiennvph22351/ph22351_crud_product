package com.thiennvph2351.ph22351_crud_product.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thiennvph2351.ph22351_crud_product.DAO.CatDAO;
import com.thiennvph2351.ph22351_crud_product.DTO.CatDTO;
import com.thiennvph2351.ph22351_crud_product.R;

import java.util.List;

public class CatAdapter extends BaseAdapter {

    List<CatDTO> list_cat;
    Context context;

    public CatAdapter(List<CatDTO> list_cat, Context context) {
        this.list_cat = list_cat;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list_cat.size();
    }

    @Override
    public Object getItem(int position) {
        return list_cat.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list_cat.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        if (convertView == null){
            row = View.inflate(context, R.layout.layout_row_cat, null);

        }else {
            row = convertView;
        }

        CatDTO objCat = list_cat.get(position);

        TextView tvId = row.findViewById(R.id.tvId);
        TextView tvName = row.findViewById(R.id.tvName);
        tvId.setText(objCat.getId()+"");
        tvName.setText(objCat.getName());
        return row;
    }
}
