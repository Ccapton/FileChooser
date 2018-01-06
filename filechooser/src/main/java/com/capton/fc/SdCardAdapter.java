package com.capton.fc;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;

import java.util.ArrayList;

/**
 * Created by capton on 2018/1/6.
 */

public class SdCardAdapter extends BaseAdapter {

    ArrayList<String> arrayList = new ArrayList<>();
    Context context;

    public SdCardAdapter(Context context,ArrayList<String> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public int itemViewWidth;
    public int getItemViewWidth(){
        System.out.println(itemViewWidth);
        if(itemViewWidth == 0)
            itemViewWidth = 460;
        return itemViewWidth;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context,R.layout.item_file,null);

        TextView textView = convertView.findViewById(R.id.fileName);
        textView.setTextSize(16);
        ImageView imageView = convertView.findViewById(R.id.fileIcon);
        imageView.getLayoutParams().width = ConvertUtils.dp2px(26);
        imageView.getLayoutParams().height = ConvertUtils.dp2px(30);

        if(position == 0){
            imageView.setImageResource(R.drawable.phone);
        }else {
            imageView.setImageResource(R.drawable.sdcard);
        }
        textView.setText(arrayList.get(position));

        convertView.measure(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        itemViewWidth = convertView.getMeasuredWidth();
            return convertView;
    }
}
