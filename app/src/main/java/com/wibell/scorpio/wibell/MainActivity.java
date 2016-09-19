package com.wibell.scorpio.wibell;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.wibell.scorpio.wibell.adapter.WibellItemAdapter;
import com.wibell.scorpio.wibell.bean.WibellItemBean;
import com.wibell.scorpio.wibell.db.NoteDatabase;
import com.wibell.scorpio.wibell.ui.ImActivity;
import com.wibell.scorpio.wibell.widget.TitleActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends TitleActivity {

    public static List<WibellItemBean> itemList = new ArrayList<WibellItemBean>();
    private ListView listView;

    private WibellItemAdapter wibellItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("标题");
//        showBackwardView(R.string.app_back,true);
        showForwardView(R.string.app_act, true);

        initData();

        wibellItemAdapter = new WibellItemAdapter(MainActivity.this, getApplicationContext(), this.itemList);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(wibellItemAdapter);

//        SpeexUtils speexUtils = new SpeexUtils();
//        Toast.makeText(getApplicationContext(), speexUtils.getStringFormC(), Toast.LENGTH_SHORT).show();
    }

    public void initData() {
        NoteDatabase db = new NoteDatabase(getApplicationContext());
        List<Map<String, String>> wibellArr = new ArrayList<Map<String, String>>();
        itemList.clear();
        wibellArr = (ArrayList) db.findAll();
        if (wibellArr != null) {
            int i = 1;
            for (Object obj : wibellArr) {
                Map<String, String> map = (Map<String, String>) obj;
                itemList.add(new WibellItemBean(i, map.get("id").toString(), map.get("ip").toString(), map.get("alias").toString(), map.get("token").toString()));
                i++;
            }
        }
    }

    public void btnClick(int position) {
        Log.d("按钮测试", itemList.get(position).getAlias() + " 我被点击了.");
        Intent intent = new Intent(MainActivity.this, ImActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("IP", itemList.get(position).getIp());
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
