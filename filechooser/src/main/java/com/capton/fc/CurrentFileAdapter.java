package com.capton.fc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by capton on 2018/1/6.
 */

public class CurrentFileAdapter extends CommonAdapter<File> {
    public CurrentFileAdapter(Context context, ArrayList<File> dataList, int resId) {
        super(context, dataList, resId);
    }

    @Override
    public void bindView(RecyclerView.ViewHolder holder, File data, int position) {
      TextView textView = holder.itemView.findViewById(R.id.fileName);
      textView.setText(data.getName());
      if(position == dataList.size() -1){
          holder.itemView.findViewById(R.id.icon).setVisibility(View.GONE);
      } else {
          holder.itemView.findViewById(R.id.icon).setVisibility(View.VISIBLE);
      }
    }
}
