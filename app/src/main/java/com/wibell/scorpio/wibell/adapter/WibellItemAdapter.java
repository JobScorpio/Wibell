package com.wibell.scorpio.wibell.adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wibell.scorpio.wibell.MainActivity;
import com.wibell.scorpio.wibell.R;
import com.wibell.scorpio.wibell.bean.WibellItemBean;
import com.wibell.scorpio.wibell.holder.WibellItemHolder;
import com.wibell.scorpio.wibell.ui.ImActivity;

import java.util.ArrayList;
import java.util.List;


public class WibellItemAdapter extends BaseAdapter {

    private MainActivity mainActivity;
    private List<WibellItemBean> itemList = new ArrayList<WibellItemBean>();//group列表
    private LayoutInflater inflater; //视图容器

    public WibellItemAdapter(MainActivity mainActivity, Context context, List<WibellItemBean> itemList) {
        inflater = LayoutInflater.from(context);
        this.mainActivity = mainActivity;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        WibellItemHolder holder;
        if (convertView == null) {
            holder = new WibellItemHolder();
            convertView = inflater.inflate(R.layout.wibell_item, null);
            holder.num = (TextView) convertView.findViewById(R.id.num);
            holder.alias = (TextView) convertView.findViewById(R.id.alias);
            holder.btn = (Button) convertView.findViewById(R.id.btn);
            convertView.setTag(holder);
        } else {
            holder = (WibellItemHolder) convertView.getTag();
        }

        holder.num.setText(itemList.get(position).getId());
        holder.alias.setText(itemList.get(position).getAlias());
        holder.btn.setText("CALL");

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity, ImActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ip", itemList.get(position).getIp());
                bundle.putString("alias", itemList.get(position).getAlias());
                intent.putExtras(bundle);

                mainActivity.startActivity(intent);
            }
        });

        return convertView;
    }

}
