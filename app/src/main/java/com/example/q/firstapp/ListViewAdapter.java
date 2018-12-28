package com.example.q.firstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>();

    public ListViewAdapter(){

    }
    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return listViewItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        final int pos = i;
        final Context context =  parent.getContext();
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview1_item, parent, false);
        }
        TextView nameView = (TextView) view.findViewById(R.id.textView1) ;
        TextView phoneView = (TextView) view.findViewById(R.id.textView2) ;
        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem listViewItem = listViewItemList.get(i);
        nameView.setText(listViewItem.getName());
        phoneView.setText(listViewItem.getPhone());

        return view;
    }
    public void addItem(String name, String phone) {
        ListViewItem item = new ListViewItem();

        item.setName(name);
        item.setPhone(phone);

        listViewItemList.add(item);
    }
}
